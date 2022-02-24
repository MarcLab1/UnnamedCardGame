package com.unnamedcardgame.network

import com.unnamedcardgame.model.Deck
import com.unnamedcardgame.model.DeckDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("api/deck/new/shuffle")
    suspend fun getDeck(@Query("deck_count") deckCount: Int): DeckDto

    @GET("api/deck/{deck_id}/draw")
    suspend fun getCards(@Path("deck_id") deckId: String,
                         @Query("count") count: Int): CardsDto

    @GET("api/deck/{deck_id}/draw")
    suspend fun getCard(@Path("deck_id") deckId: String,
                        @Query("count") count: Int): CardsDto

    @GET("api/deck/{deck_id}/shuffle")
    suspend fun shuffleDeck(@Path ("deck_id") deckId: String) : DeckDto


}