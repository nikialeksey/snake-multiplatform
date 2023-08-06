package pro.devdesign.snake

import android.content.res.Resources
import android.util.TypedValue

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun toDp(px: Int): Int {
    val displayMetrics = Resources.getSystem().displayMetrics
    return px * 160 / displayMetrics.densityDpi
}