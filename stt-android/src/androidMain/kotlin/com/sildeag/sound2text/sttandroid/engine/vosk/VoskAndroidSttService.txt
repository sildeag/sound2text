package com.sildeag.sound2text.sttandroid.engine.vosk

    private fun extractText(json: String): String {
        val key = "\"text\""
        val index = json.indexOf(key)
        if (index == -1) return json
        val start = json.indexOf('"', index + key.length) + 1
        val end = json.indexOf('"', start)
        if (start == 0 || end == -1) return json
        return json.substring(start, end)
    }
}
