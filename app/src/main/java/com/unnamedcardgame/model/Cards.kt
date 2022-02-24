package com.unnamedcardgame.model

data class Cards(
    val success: Boolean?,
    val cards: List<Card>?,
    val deck_id: String?,
    val remaining: Int?
)