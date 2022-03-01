package com.example.tmdbclient.data.repository.movie.DataSource

import com.example.tmdbclient.data.model.movie.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {

    suspend fun getMoviesFromAPI() : Response<MovieList>
}