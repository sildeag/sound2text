package com.sildeag.sound2text.service.grammar

import com.sildeag.sound2text.core.grammar.GrammarService

class OpenAiGrammarService(private val apiKey: String) : GrammarService {
    override suspend fun correctGrammar(text: String): String {
        // Use Ktor or Retrofit to call OpenAI API
        TODO("Implement actual API call")
    }
}
