package com.example.tmdbclient.data.repository.artist.DataSourceImpl

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.repository.artist.DataSource.ArtistCacheDataSource

class ArtistCacheDataSourceImpl : ArtistCacheDataSource {
    private var artistList = ArrayList<Artist>()

    override suspend fun getArtistFromCache(): List<Artist> {
        return artistList
    }

    override suspend fun saveArtistToCache(artists: List<Artist>) {
        artistList.clear()
        artistList = ArrayList(artists)
    }
}