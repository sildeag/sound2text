package com.sildeag.sound2text.core.grammar

interface GrammarService {
    suspend fun correctGrammar(text: String): String
}