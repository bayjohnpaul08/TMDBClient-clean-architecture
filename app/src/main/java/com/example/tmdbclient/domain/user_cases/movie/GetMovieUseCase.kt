package com.example.tmdbclient.domain.user_cases.movie

import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.domain.repository.MovieRepository

class GetMovieUseCase(private val repository : MovieRepository) {

    suspend fun execute() : List<Movie>? = repository.getMovies()
}