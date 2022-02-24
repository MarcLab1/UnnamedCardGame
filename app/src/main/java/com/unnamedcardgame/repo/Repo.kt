package com.unnamedcardgame.repo

import com.unnamedcardgame.model.Card
import com.unnamedcardgame.model.Cards
import com.unnamedcardgame.model.Deck
import com.unnamedcardgame.model.DeckDto

interface Repo {

    suspend fun getDeck(deckCount:Int) : Deck
    suspend fun getCards(deckId: String, count: Int) : Cards
    suspend fun getCard(deckId: String) : Card?
    suspend fun shuffleDeck(deckId: String) : Deck
}