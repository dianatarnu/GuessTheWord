package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel(){
    // The current word
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>


    init {
        Log.i("GameViewModel", "GameViewModel Created")
        resetList()
        nextWord()
        _score.value = 0
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed")
    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord(): String? {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            //gameFinished()
        } else {
            _word.value = wordList.removeAt(0)
        }
        return _word.value
    }

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        _word.value = nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        _word.value = nextWord()
    }
}