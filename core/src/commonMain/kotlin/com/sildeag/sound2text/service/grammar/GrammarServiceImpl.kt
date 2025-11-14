package com.sildeag.sound2text.service.grammar

interface GrammarService {
    suspend fun correctGrammar(text: String): String
}