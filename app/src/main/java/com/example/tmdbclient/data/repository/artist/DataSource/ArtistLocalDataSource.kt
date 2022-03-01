package com.example.tmdbclient.data.repository.artist.DataSource

import com.example.tmdbclient.data.model.artist.Artist

interface ArtistLocalDataSource {

    suspend fun getArtistFromDB() : List<Artist>
    suspend fun saveArtistToDB(artists: List<Artist>)
    suspend fun clearAll()
}