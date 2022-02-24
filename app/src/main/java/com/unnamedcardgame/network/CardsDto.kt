package com.unnamedcardgame.network

import com.unnamedcardgame.model.Cards

data class CardsDto(
    val success: Boolean?,
    val cards: List<CardDto>?,
    val deck_id: String?,
    val remaining: Int?
)
{
    fun toCards() : Cards = Cards(success, cards?.map { it.toCard() }, deck_id, remaining)
}