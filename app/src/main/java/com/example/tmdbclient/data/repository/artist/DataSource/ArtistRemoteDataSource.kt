package com.example.tmdbclient.data.repository.artist.DataSource

import com.example.tmdbclient.data.model.artist.ArtistList
import retrofit2.Response

interface ArtistRemoteDataSource {

    suspend fun getArtistFromAPI() : Response<ArtistList>
}