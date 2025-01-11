package com.alexeycode.snake

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()

@Composable
actual fun toDp(px: Int): Int {
    return (px / LocalDensity.current.density).toInt()
}