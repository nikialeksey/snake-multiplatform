package pro.devdesign.snake

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

val DIRECTION_UP = 0 to -1
val DIRECTION_LEFT = -1 to 0
val DIRECTION_DOWN = 0 to 1
val DIRECTION_RIGHT = 1 to 0

private const val CELL_SIZE = 10
private var FIELD_WIDTH = 0
private var FIELD_HEIGHT = 0

private val RANDOM = Random(0)

private fun generatePosition(): Pair<Int, Int> {
    return if (FIELD_WIDTH == 0 || FIELD_HEIGHT == 0) {
        -1 to -1
    } else {
        (RANDOM.nextInt(FIELD_WIDTH / CELL_SIZE) * CELL_SIZE) to (RANDOM.nextInt(FIELD_HEIGHT / CELL_SIZE) * CELL_SIZE)
    }
}

private fun generateNewSnackPosition(snakePosition: List<Pair<Int, Int>>): Pair<Int, Int> {
    var newPosition = generatePosition()
    while (snakePosition.contains(newPosition)) newPosition = generatePosition()
    return newPosition
}

private fun adjustCoord(c: Int, cell: Int, max: Int): Int {
    return if (c < 0) {
        (max / cell) * cell - cell
    } else if (c > max - cell) {
        0
    } else {
        c
    }
}

private fun isValidPosition(xy: Pair<Int, Int>, width: Int, height: Int): Boolean {
    val (x, y) = xy
    return x in 0 until width && y in 0 until height
}

private fun getNextPosition(
    headPosition: Pair<Int, Int>,
    direction: Pair<Int, Int>
): Pair<Int, Int> {
    val newX = headPosition.first + CELL_SIZE * direction.first
    val newY = headPosition.second + CELL_SIZE * direction.second
    val x = adjustCoord(newX, CELL_SIZE, FIELD_WIDTH)
    val y = adjustCoord(newY, CELL_SIZE, FIELD_HEIGHT)
    return x to y
}

@Composable
fun Game(direction: Pair<Int, Int>) {
    var snakePosition by remember { mutableStateOf(listOf(0 to 0)) }
    var snackPosition by remember { mutableStateOf(generateNewSnackPosition(snakePosition)) }

    LaunchedEffect(snakePosition) {
        delay(0.2.seconds)
        val tailPosition = snakePosition.last()
        val headPosition = snakePosition.first()
        val nextHeadPosition = getNextPosition(headPosition, direction)
        snakePosition = listOf(nextHeadPosition) + snakePosition.dropLast(1)

        if (!isValidPosition(snackPosition, FIELD_WIDTH, FIELD_HEIGHT)) {
            snackPosition = generateNewSnackPosition(snakePosition)
        }
        if (nextHeadPosition == snackPosition) {
            snakePosition = snakePosition + tailPosition
            snackPosition = generateNewSnackPosition(snakePosition)
        }
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .onSizeChanged {
                FIELD_WIDTH = toDp(it.width)
                FIELD_HEIGHT = toDp(it.height)
            }
            .background(Color.LightGray)
    ) {
        drawRoundRect(
            color = Color.Green,
            size = Size(CELL_SIZE.dp.toPx(), CELL_SIZE.dp.toPx()),
            topLeft = Offset(snackPosition.first.dp.toPx(), snackPosition.second.dp.toPx())
        )

        snakePosition.withIndex().reversed().forEach { (index, position) ->
            drawRoundRect(
                color = if (index == 0) Color.Red else Color.Black,
                size = Size(CELL_SIZE.dp.toPx(), CELL_SIZE.dp.toPx()),
                topLeft = Offset(position.first.dp.toPx(), position.second.dp.toPx())
            )
        }
    }
}
