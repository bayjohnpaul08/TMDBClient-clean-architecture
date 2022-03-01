package com.example.tmdbclient.data.repository.movie

import android.util.Log
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.movie.DataSource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.DataSource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.DataSource.MovieRemoteDataSource
import com.example.tmdbclient.domain.repository.MovieRepository
import java.lang.Exception

class MovieRepositoryImpl(
    private val movieRemoteDataSource : MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepository {

    override suspend fun getMovies(): List<Movie> {
        return getMoviesFromCache()
    }

    override suspend fun updateMovies(): List<Movie> {
        val newListOfMovies = getMoviesFromAPI()
        movieLocalDataSource.clearAll()
        movieLocalDataSource.saveMoviesToDB(newListOfMovies)
        movieCacheDataSource.saveMoviesToCache(newListOfMovies)
        return newListOfMovies
    }

    suspend fun getMoviesFromAPI() : List<Movie> {
        lateinit var moveList : List<Movie>
        try {
            val response = movieRemoteDataSource.getMoviesFromAPI()
            val body = response.body()
            if (body != null){
                moveList = body.movies
            }
        }catch (e:Exception) {
            Log.v("jp", e.message.toString())
        }

        return moveList
    }

    suspend fun getMoviesFromDB() : List<Movie> {
        lateinit var movieList : List<Movie>
        try {
           movieList = movieLocalDataSource.getMoviesFromDB()
        }catch (e:Exception) {
            Log.v("jp", e.message.toString())
        }
        if (movieList.isNotEmpty()){
            return movieList
        }else{
            movieList = getMoviesFromAPI()
            movieLocalDataSource.saveMoviesToDB(movieList)
        }
        return movieList
    }

    suspend fun getMoviesFromCache() : List<Movie> {
        lateinit var movieList : List<Movie>
        try {
            movieList = movieCacheDataSource.getMoviesToCache()
        }catch (e:Exception) {
            Log.v("jp", e.message.toString())
        }

        if (movieList.isNotEmpty()){
            return movieList
        }else{
            movieList = getMoviesFromDB()
            movieCacheDataSource.saveMoviesToCache(movieList)
        }
        return movieList
    }
}