@file:OptIn(ExperimentalComposeUiApi::class)

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import pro.devdesign.snake.DIRECTION_DOWN
import pro.devdesign.snake.DIRECTION_LEFT
import pro.devdesign.snake.DIRECTION_RIGHT
import pro.devdesign.snake.DIRECTION_UP
import pro.devdesign.snake.Game

private fun getDirection(event: KeyEvent): Pair<Int, Int>? {
    return when (event.key) {
        Key.W, Key.DirectionUp -> DIRECTION_UP
        Key.A, Key.DirectionLeft -> DIRECTION_LEFT
        Key.S, Key.DirectionDown -> DIRECTION_DOWN
        Key.D, Key.DirectionRight -> DIRECTION_RIGHT
        else -> null
    }
}

fun main() {
    application {
        var direction by remember { mutableStateOf(DIRECTION_RIGHT) }

        Window(
            state = WindowState(size = DpSize(100.dp, 100.dp)),
            onKeyEvent = { event ->
                if (event.type == KeyEventType.KeyDown) {
                    val next = getDirection(event)
                    next?.let { direction = it }
                    next != null
                } else {
                    false
                }
            },
            onCloseRequest = ::exitApplication
        ) {
            Game(direction)
        }
    }
}