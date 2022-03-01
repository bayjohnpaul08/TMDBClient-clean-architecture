package com.example.tmdbclient.data.repository.tvshow.DataSource

import com.example.tmdbclient.data.model.tvshow.TvShow

interface TvShowCacheDataSource {

    suspend fun getTvShowToCache() : List<TvShow>
    suspend fun saveTvShowToCache(tvShows : List<TvShow>)
}