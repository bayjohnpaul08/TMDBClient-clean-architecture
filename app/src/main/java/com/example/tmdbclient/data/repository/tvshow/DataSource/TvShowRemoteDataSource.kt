package com.example.tmdbclient.data.repository.tvshow.DataSource

import com.example.tmdbclient.data.model.tvshow.TvShowList
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun getTvShowToAPI() : Response<TvShowList>
}