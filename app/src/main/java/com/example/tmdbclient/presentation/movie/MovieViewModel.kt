package com.example.tmdbclient.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.domain.user_cases.movie.GetMovieUseCase
import com.example.tmdbclient.domain.user_cases.movie.UpdateMovieUseCase

class MovieViewModel(
    private val getMovieUseCase: GetMovieUseCase,
    private val updateMovieUseCase: UpdateMovieUseCase
) : ViewModel() {

    fun getMovies() = liveData {
        val movieList = getMovieUseCase.execute()
        emit(movieList)
    }

    fun updateMovies() = liveData {
        val movieList = updateMovieUseCase.execute()
        emit(movieList)
    }
}