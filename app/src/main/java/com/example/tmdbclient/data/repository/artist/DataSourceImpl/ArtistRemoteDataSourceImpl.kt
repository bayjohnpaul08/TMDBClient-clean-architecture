package com.example.tmdbclient.data.repository.artist.DataSourceImpl

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.model.artist.ArtistList
import com.example.tmdbclient.data.repository.artist.DataSource.ArtistRemoteDataSource
import retrofit2.Response

class ArtistRemoteDataSourceImpl(private val tmdbService: TMDBService, private val apiKey: String) : ArtistRemoteDataSource {

    override suspend fun getArtistFromAPI(): Response<ArtistList> = tmdbService.getPopularArtists(apiKey)
}