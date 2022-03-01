package com.example.tmdbclient.data.repository.movie.DataSource

import com.example.tmdbclient.data.model.movie.Movie

interface MovieCacheDataSource {

    suspend fun getMoviesToCache() : List<Movie>
    suspend fun saveMoviesToCache(movies : List<Movie>)
}