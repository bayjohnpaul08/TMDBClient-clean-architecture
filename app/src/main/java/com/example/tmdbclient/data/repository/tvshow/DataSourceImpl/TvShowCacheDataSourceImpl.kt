package com.example.tmdbclient.data.repository.tvshow.DataSourceImpl

import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.data.repository.tvshow.DataSource.TvShowCacheDataSource

class TvShowCacheDataSourceImpl : TvShowCacheDataSource{
    private var tvShowList = ArrayList<TvShow>()

    override suspend fun getTvShowToCache(): List<TvShow> {
        return tvShowList
    }

    override suspend fun saveTvShowToCache(tvShows: List<TvShow>) {
        tvShowList.clear()
        tvShowList = ArrayList(tvShows)
    }
}