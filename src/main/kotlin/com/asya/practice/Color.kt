package com.asya.practice

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

// Manipulating images.
// 24bit integer = Int.
// 00000000 rrrrrrrr gggggggg bbbbbbbb

/*
    1. Define a Color class that takes 3 integers as arguments (red, green, blue).
    2. Make sure that the properties of the Color (red, green, blue) are always in between 0 and 255.
    3. Add a method toInt() that returns a single integer with the representation above (00000000 rrrrrrrr gggggggg bbbbbbbb).
    4. Add a draw(width, height, path) that draws an image of width * height, all with the same color.
 */
class Color(r: Int, g: Int, b: Int) {
    val red: Int = clampColor(r) // 00000000 00000000 00000000 rrrrrrrr
    val green: Int = clampColor(g) // 00000000 00000000 00000000 gggggggg
    val blue: Int = clampColor(b) // 00000000 00000000 00000000 bbbbbbbb

    // 00000000 rrrrrrrr 00000000 00000000
    // 00000000 00000000 gggggggg 00000000
    // 00000000 00000000 00000000 bbbbbbbb
    // 00000000 rrrrrrrr gggggggg bbbbbbbb
    fun toInt(): Int =
        (red shl 16) or (green shl 8) or blue

    fun draw(width: Int, height: Int, path: String) {
        val pixelInt = toInt()
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val pixels = IntArray(width * height) { pixelInt }
        image.setRGB(0, 0, width, height, pixels, 0, width)
        ImageIO.write(image, "JPG", File(path))
    }

    fun clampColor(value: Int) =
        if (value <= 0) 0
        else if (value >= 255) 255
        else value

    operator fun plus(other: Color): Color =
        Color(
            clampColor(red + other.red),
            clampColor(green + other.green),
            clampColor(blue + other.blue)
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Color

        if (red != other.red) return false
        if (green != other.green) return false
        if (blue != other.blue) return false

        return true
    }

    override fun hashCode(): Int {
        var result = red
        result = 31 * result + green
        result = 31 * result + blue
        return result
    }

    companion object {
        val BLACK = Color(0, 0, 0)
        val RED = Color(255, 0, 0)
        val GREEN = Color(0, 255, 0)
        val BLUE = Color(0, 0, 255)
        val GRAY = Color(128, 128, 128)

        fun fromHex(arg: Int): Color {
            // 00000000111111110000000000000000 = 0xFF0000
            // 000000000000000000000000rrrrrrrr
            val red = (arg and 0xFF0000) shr 16

            // 00000000000000001111111100000000 = 0xFF00
            // 000000000000000000000000gggggggg
            val green = (arg and 0xFF00) shr 8

            // 00000000000000000000000011111111 = 0xFF
            // 000000000000000000000000bbbbbbbb
            val blue = arg and 0xFF

            return Color(red, green, blue)
        }
    }
}

fun drawColor(width: Int, height: Int, path: String) {
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    val pixels = IntArray(width * height) { 0xFF0000 }
    image.setRGB(0, 0, width, height, pixels, 0, width)
    ImageIO.write(image, "JPG", File(path))
}

/*
    Create a companion object for Color so that you can write
        Color.BLACK, Color.RED, ...
        Color.fromHex(int) => Color instance
 */

fun main() {
    // Color.fromHex(0xA108F46).draw(20, 20, "src/main/resources/color.jpg")
    Color.BLUE.draw(1728, 2341, "src/main/resources/blue.jpg")
}