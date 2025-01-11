package com.alexeycode.snake

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

@Composable
actual fun toDp(px: Int): Int {
    return (px / LocalDensity.current.density).toInt()
}