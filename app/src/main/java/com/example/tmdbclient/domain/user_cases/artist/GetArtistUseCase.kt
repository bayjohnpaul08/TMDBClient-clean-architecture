package com.example.tmdbclient.domain.user_cases.artist

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.domain.repository.ArtistRepository

class GetArtistUseCase(private val repository: ArtistRepository) {

    suspend fun execute() : List<Artist>? = repository.getArtists()
}