package com.unnamedcardgame.model

data class DeckDto(
    val success: Boolean?,
    val deck_id: String?,
    val shuffled: Boolean?,
    val remaining: Int?
)
{

    fun toDeck() : Deck = Deck(success, deck_id, shuffled, remaining)
}