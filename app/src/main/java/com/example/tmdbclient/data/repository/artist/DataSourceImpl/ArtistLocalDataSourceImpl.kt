package com.example.tmdbclient.data.repository.artist.DataSourceImpl

import com.example.tmdbclient.data.db.ArtistDao
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.repository.artist.DataSource.ArtistLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ArtistLocalDataSourceImpl(private val artistDao: ArtistDao) : ArtistLocalDataSource {
    override suspend fun getArtistFromDB(): List<Artist> {
        return artistDao.getAllArtists()
    }

    override suspend fun saveArtistToDB(artists: List<Artist>) {
        CoroutineScope(IO).launch {
            artistDao.saveAllArtists(artists)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(IO).launch {
            artistDao.deleteAllArtists()
        }
    }
}