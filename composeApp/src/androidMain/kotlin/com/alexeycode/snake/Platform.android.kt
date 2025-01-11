package com.alexeycode.snake

import android.content.res.Resources
import android.os.Build
import androidx.compose.runtime.Composable

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

@Composable
actual fun toDp(px: Int): Int {
    val displayMetrics = Resources.getSystem().displayMetrics
    return px * 160 / displayMetrics.densityDpi
}