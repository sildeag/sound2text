package com.sildeag.sound2text.service.grammar

import kotlinx.coroutines.delay

class MockGrammarService : GrammarService {
    override suspend fun correctGrammar(text: String): String {
        delay(500) // simulate network
        return text.replace("i", "I").replace("dont", "don't")
    }
}