package com.example.tmdbclient.data.repository.movie.DataSourceImpl

import com.example.tmdbclient.data.db.MovieDao
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.movie.DataSource.MovieLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {

    override suspend fun getMoviesFromDB(): List<Movie> {
        return movieDao.getAllMovies()
    }

    override suspend fun saveMoviesToDB(movies: List<Movie>) {
        CoroutineScope(IO).launch {
            movieDao.saveMovies(movies)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(IO).launch {
            movieDao.deleteAllMovies()
        }
    }
}