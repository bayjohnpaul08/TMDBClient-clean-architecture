package com.example.tmdbclient.data.repository.movie.DataSourceImpl

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.model.movie.MovieList
import com.example.tmdbclient.data.repository.movie.DataSource.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl(private val tmdbService : TMDBService,
                                private val apiKey : String) : MovieRemoteDataSource {

    override suspend fun getMoviesFromAPI(): Response<MovieList> = tmdbService.getPopularMovies(apiKey)

}