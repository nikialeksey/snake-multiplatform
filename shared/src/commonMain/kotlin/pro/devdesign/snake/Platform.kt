package pro.devdesign.snake

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
expect fun toDp(px: Int): Int