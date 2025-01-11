package com.alexeycode.snake

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

@Composable
actual fun toDp(px: Int): Int {
    return (px / LocalDensity.current.density).toInt()
}