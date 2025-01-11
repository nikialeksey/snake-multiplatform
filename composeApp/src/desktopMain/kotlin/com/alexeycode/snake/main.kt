package com.alexeycode.snake

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

private fun getDirection(event: KeyEvent): Pair<Int, Int>? {
    return when (event.key) {
        Key.W, Key.DirectionUp -> DIRECTION_UP
        Key.A, Key.DirectionLeft -> DIRECTION_LEFT
        Key.S, Key.DirectionDown -> DIRECTION_DOWN
        Key.D, Key.DirectionRight -> DIRECTION_RIGHT
        else -> null
    }
}

fun main() = application {
    var direction by remember { mutableStateOf(DIRECTION_RIGHT) }

    Window(
        state = WindowState(size = DpSize(400.dp, 300.dp)),
        onKeyEvent = { event ->
            if (event.type == KeyEventType.KeyDown) {
                val next = getDirection(event)
                next?.let { direction = it }
                next != null
            } else {
                false
            }
        },
        onCloseRequest = ::exitApplication,
        title = "Snake",
    ) {
        App(direction)
    }
}