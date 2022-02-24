package com.unnamedcardgame.util

object Constants {

    val BASE_URL = "https://deckofcardsapi.com/" //returns id of deck
    val DECK_COUNT = 1
    val CARDS_TO_START = 3
    val GROUP_COUNT = 3
    val DECK_ID = "2x4lk5cnzwhz"

    fun getWidth(index: Int) : Float
    {
        when(index)
        {
            0 -> return .333f
            1 -> return .5f
            2 -> return 1f
        }
        return 1f
    }

}