package com.alexeycode.snake

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.w3c.dom.events.KeyboardEvent

private fun getDirection(event: KeyboardEvent): Pair<Int, Int>? {
    return when (event.code) {
        "KeyW" -> DIRECTION_UP
        "KeyA" -> DIRECTION_LEFT
        "KeyS" -> DIRECTION_DOWN
        "KeyD" -> DIRECTION_RIGHT
        else -> null
    }
}

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        var direction by remember { mutableStateOf(DIRECTION_RIGHT) }

        document.onkeypress = { event ->
            getDirection(event)?.let {
                direction = it
            }
        }

        App(direction)
    }
}