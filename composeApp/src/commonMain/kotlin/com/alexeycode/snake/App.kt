package com.alexeycode.snake

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(inDirection: Pair<Int, Int> = DIRECTION_RIGHT) {
    var direction by remember { mutableStateOf(DIRECTION_RIGHT) }

    LaunchedEffect(inDirection) {
        direction = inDirection
    }

    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Column {
                Row(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Game(direction)
                }
                Row {
                    Button(onClick = { direction = DIRECTION_LEFT }) {
                        Text(text = "Left")
                    }
                    Button(onClick = { direction = DIRECTION_UP }) {
                        Text(text = "Up")
                    }
                    Button(onClick = { direction = DIRECTION_DOWN }) {
                        Text(text = "Down")
                    }
                    Button(onClick = { direction = DIRECTION_RIGHT }) {
                        Text(text = "Right")
                    }
                }
            }
        }
    }
}