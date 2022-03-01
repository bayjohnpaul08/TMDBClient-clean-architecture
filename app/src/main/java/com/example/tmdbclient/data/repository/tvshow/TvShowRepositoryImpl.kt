package com.example.tmdbclient.data.repository.tvshow

import android.util.Log
import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.data.repository.tvshow.DataSource.TvShowCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.DataSource.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.DataSource.TvShowRemoteDataSource
import com.example.tmdbclient.domain.repository.TvShowRepository

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource
) : TvShowRepository {

    override suspend fun getTvShows(): List<TvShow>? {
        return getTvShowFromCache()
    }

    override suspend fun updateTvShows(): List<TvShow>? {
        val newListOfTvShow = getTvShowFromAPI()
        tvShowLocalDataSource.clearAll()
        tvShowLocalDataSource.saveTvShowToDB(newListOfTvShow)
        tvShowCacheDataSource.saveTvShowToCache(newListOfTvShow)
        return newListOfTvShow
    }

    suspend fun getTvShowFromAPI() : List<TvShow> {
       lateinit var tvShowList : List<TvShow>

       try {
           val response = tvShowRemoteDataSource.getTvShowToAPI()
           val body = response.body()
           if (body != null){
               tvShowList = body.tvShows
           }
       }catch (e:Exception){
           Log.v("jp", e.message.toString())
       }
       return tvShowList
    }

    suspend fun getTvShowFromDB() : List<TvShow> {
        lateinit var tvShowList: List<TvShow>
        try{
            tvShowList = tvShowLocalDataSource.getTvShowFromDB()
        }catch (e:Exception){
            Log.v("jp", e.message.toString())
        }
        if (tvShowList.isNotEmpty()){
            return tvShowList
        }else{
            tvShowList = getTvShowFromAPI()
            tvShowLocalDataSource.saveTvShowToDB(tvShowList)
        }
        return tvShowList
    }

    suspend fun getTvShowFromCache() : List<TvShow> {
        lateinit var tvShowList: List<TvShow>
        try{
            tvShowList = tvShowCacheDataSource.getTvShowToCache()
        }catch (e:Exception){
            Log.v("jp", e.message.toString())
        }
        if (tvShowList.isNotEmpty()){
            return tvShowList
        }else{
            tvShowList = getTvShowFromDB()
            tvShowCacheDataSource.saveTvShowToCache(tvShowList)
        }
        return tvShowList
    }

}