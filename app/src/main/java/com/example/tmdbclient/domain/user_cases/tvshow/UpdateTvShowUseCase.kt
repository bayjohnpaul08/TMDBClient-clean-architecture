package com.example.tmdbclient.domain.user_cases.tvshow

import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.domain.repository.TvShowRepository

class UpdateTvShowUseCase(private val repository: TvShowRepository) {

    suspend fun execute() : List<TvShow>? = repository.getTvShows()
}