package pro.devdesign.snake.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import pro.devdesign.snake.DIRECTION_DOWN
import pro.devdesign.snake.DIRECTION_LEFT
import pro.devdesign.snake.DIRECTION_RIGHT
import pro.devdesign.snake.DIRECTION_UP
import pro.devdesign.snake.Game

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var direction by remember { mutableStateOf(DIRECTION_RIGHT) }

            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
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
    }
}
