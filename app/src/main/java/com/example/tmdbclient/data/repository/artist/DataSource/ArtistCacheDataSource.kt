package com.example.tmdbclient.data.repository.artist.DataSource

import com.example.tmdbclient.data.model.artist.Artist

interface ArtistCacheDataSource {

    suspend fun getArtistFromCache(): List<Artist>
    suspend fun saveArtistToCache(artists: List<Artist>)

}