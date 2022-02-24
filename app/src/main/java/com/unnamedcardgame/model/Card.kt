package com.unnamedcardgame.model

data class Card(
    val image: String?,
    val value: String?,
    val suit: String?,
    val code: String?
)

{
    override fun toString() : String
    {
        return "$suit $value"
    }
}