package com.unnamedcardgame.ui.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unnamedcardgame.model.Card
import com.unnamedcardgame.model.Cards
import com.unnamedcardgame.repo.Repo
import com.unnamedcardgame.util.Constants
import com.unnamedcardgame.util.GameStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repo: Repo
) : ViewModel() {

    var card: MutableState<Card?> = mutableStateOf(null)
    var cards: MutableState<Cards?> = mutableStateOf(null)
    var deckId: MutableState<String> = mutableStateOf("")

    var groups: MutableList<SnapshotStateList<Card>> = mutableListOf()
    var scores = mutableStateListOf<Int>()
    var gameStatus = mutableStateOf(GameStatus.ACTIVE)

    init {
        deckId.value = Constants.DECK_ID
        for(i in 0 until Constants.GROUP_COUNT)
        {
            groups.add(mutableStateListOf())
            scores.add(0)
        }
        newGame()

    }

    fun newGame() {

        card.value = null
        for (i in 0 until groups.size)
            groups.get(i).clear()

        for (i in 0 until scores.size)
            scores[i] = 0

        gameStatus.value = GameStatus.ACTIVE

        viewModelScope.launch {
            repo.shuffleDeck(deckId.value)
            loadStartCards(deckId.value)
            loadCard()
        }
    }

    suspend fun loadStartCards(deckId: String) {
        cards.value = repo.getCards(deckId, Constants.CARDS_TO_START)
        for (i in 0 until Constants.CARDS_TO_START)
            addCardToGroup(cards.value!!.cards!![i], i)
    }

    fun loadCard() {
        viewModelScope.launch {
            card.value = repo.getCard(deckId.value)
        }
    }

    fun addCardToGroup(card: Card, groupIndex: Int) {
        groups.get(groupIndex).add(card)
        this.card.value = null  //prevents double clicking/tapping on the same card
        updateScores()

        if (gameStatus.value != GameStatus.BUST)
            loadCard()
    }

    fun updateScores(): Unit {
        groups.forEachIndexed { index, group ->
            var score = 0
            var aceCount = 0
            group.forEach { card ->
                if (card.value!!.equals("ACE"))
                    aceCount++
                score += cardValueToInt(card.value!!)
            }
            while (score > 21 && aceCount != 0) {
                score -= 10
                aceCount--
            }
            if (score > 21)
                gameStatus.value = GameStatus.BUST
            scores[index] = score
        }

        scores.forEach {
            if (it != 21)
                return
        }
        gameStatus.value = GameStatus.WINNER
    }

    fun cardValueToInt(value: String): Int {
        when (value) {
            "ACE" -> return 11
            "2" -> return 2
            "3" -> return 3
            "4" -> return 4
            "5" -> return 5
            "6" -> return 6
            "7" -> return 7
            "8" -> return 8
            "9" -> return 9
            "10" -> return 10
            "JACK" -> return 10
            "QUEEN" -> return 10
            "KING" -> return 10
            else -> return -1
        }
    }
}