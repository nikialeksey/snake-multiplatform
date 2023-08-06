package pro.devdesign.snake

class DesktopPlatform : Platform {
    override val name: String = "Desktop"
}

actual fun getPlatform(): Platform = DesktopPlatform()

actual fun toDp(px: Int): Int {
    return px
}