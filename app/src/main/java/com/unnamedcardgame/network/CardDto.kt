package com.unnamedcardgame.network

import com.unnamedcardgame.model.Card

data class CardDto(
    val image: String?,
    val value: String?,
    val suit: String?,
    val code: String?
)
{
    fun toCard() : Card{
        return Card(image, value, suit, code)
    }
}