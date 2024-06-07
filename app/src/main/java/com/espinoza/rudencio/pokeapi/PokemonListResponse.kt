package com.espinoza.rudencio.pokeapi

data class PokemonListResponse(

    val count: Int,
    val next: String,

    val results: List<PokemonResponse>
)
