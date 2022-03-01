package com.example.tmdbclient.data.repository.tvshow.DataSource

import com.example.tmdbclient.data.model.tvshow.TvShow

interface TvShowLocalDataSource {

    suspend fun getTvShowFromDB() : List<TvShow>
    suspend fun saveTvShowToDB(tvShows : List<TvShow>)
    suspend fun clearAll()
}