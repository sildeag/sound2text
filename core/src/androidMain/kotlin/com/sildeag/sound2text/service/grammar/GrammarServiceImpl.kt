package com.sildeag.sound2text.core.service.grammar

interface GrammarService {
    suspend fun correctGrammar(text: String): String
}