package com.unnamedcardgame.repo

import com.unnamedcardgame.model.Card
import com.unnamedcardgame.model.Cards
import com.unnamedcardgame.model.Deck
import com.unnamedcardgame.network.ApiService

class Repo_impl(
    private val apiService: ApiService
) : Repo {
    override suspend fun getDeck(deckCount: Int): Deck {
        return apiService.getDeck(deckCount).toDeck()
    }

    override suspend fun getCards(deckId: String, count: Int): Cards {
        return apiService.getCards(deckId, count).toCards()
    }

    override suspend fun getCard(deckId: String): Card? {
        return apiService.getCard(deckId, 1).cards?.get(0)?.toCard()
    }

    override suspend fun shuffleDeck(deckId: String)  : Deck {
        return apiService.shuffleDeck(deckId).toDeck()
    }

}