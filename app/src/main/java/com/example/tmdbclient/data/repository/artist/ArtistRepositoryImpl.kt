package com.example.tmdbclient.data.repository.artist

import android.util.Log
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.repository.artist.DataSource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.DataSource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.DataSource.ArtistRemoteDataSource
import com.example.tmdbclient.domain.repository.ArtistRepository
import java.lang.Exception

class ArtistRepositoryImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource
) : ArtistRepository {

    override suspend fun getArtists(): List<Artist>? {
        return getArtistFromCache()
    }

    override suspend fun updateArtists(): List<Artist>? {
        val newListOfArtist = getArtistFromAPI()
        artistLocalDataSource.clearAll()
        artistLocalDataSource.saveArtistToDB(newListOfArtist)
        artistCacheDataSource.saveArtistToCache(newListOfArtist)
        return newListOfArtist
    }

    private suspend fun getArtistFromAPI() : List<Artist> {
        lateinit var artistList : List<Artist>
        try {
            val response = artistRemoteDataSource.getArtistFromAPI()
            val body = response.body()
            if (body != null){
                artistList = body.artists
            }
        }catch (e:Exception){
            Log.v("jp", e.message.toString())
        }
        return artistList
    }

    private suspend fun getArtistFromDB() : List<Artist> {
        lateinit var artistList : List<Artist>
        try {
            artistList = artistLocalDataSource.getArtistFromDB()
        }catch (e:Exception){
            Log.v("jp", e.message.toString())
        }

        if (artistList.isNotEmpty()){
            return artistList
        }else{
            artistList = getArtistFromAPI()
            artistLocalDataSource.saveArtistToDB(artistList)
        }
        return artistList
    }

    private suspend fun getArtistFromCache() : List<Artist> {
        lateinit var artistList : List<Artist>
        try {
            artistList = artistCacheDataSource.getArtistFromCache()
        }catch (e:Exception){
            Log.v("jp", e.message.toString())
        }

        if (artistList.isNotEmpty()){
            return artistList
        }else{
            artistList = getArtistFromDB()
            artistCacheDataSource.saveArtistToCache(artistList)
        }
        return artistList
    }
}