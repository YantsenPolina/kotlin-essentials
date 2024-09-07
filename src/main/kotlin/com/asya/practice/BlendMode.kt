package com.asya.practice

/**
 * 1. Create a BlendMode interface with a single method to combine 2 colors (foreground and background)
 * and return another (the signature up to you).
 * 2. Create the following BlendMode types:
 * - Transparency
 *  - Takes a factor f (Double between 0 and 1) as a constructor argument.
 *  - The combine method has the following formula:
 *      result = foreground * factor + background * (1 - factor)
 *    which applies on all channels (red, green, blue).
 * - Multiply
 *  - The combine method formula:
 *      result = foreground * background / 255 on all channels.
 * - Screen
 *  - The combine method formula is opposite to multiply:
 *      result = 255 - (255 - foreground) * (255 - background) / 255
 * - NoBlend
 *  - Combine just returns the foreground.
 * 3. Test the blends.
 *  - Transparency(0.5).combine(red,blue) should give you a dark magenta.
 *  - Create a Gray color with 128 on all channels.
 *  - Multiply(red, gray) should give you a dark red.
 *  - Multiply(red, blue) should give you exactly black.
 *  - Screen(red, gray) should give you a light red (pink-ish).
 * 4. Create a parse method in the companion of BlendMode that takes a String arg and returns a BlendMode.
 *  - If the string is "transparency" => Transparency with 0.5.
 *  - "multiply" => Multiply.
 *  - "screen" => Screen.
 *  - Anything else => NoBlend.
 */
interface BlendMode {
    fun combine(foreground: Color, background: Color): Color

    companion object {
        fun parse(string: String): BlendMode =
            when(string) {
                "transparency" -> Transparency(0.5)
                "multiply" -> Multiply
                "screen" -> Screen
                else -> NoBlend
            }
    }
}

class Transparency(f: Double): BlendMode {
    val factor: Double =
        if (f <= 0.0) 0.0
        else if (f >= 1.0) 1.0
        else f

    override fun combine(foreground: Color, background: Color): Color =
        Color(
            (foreground.red * factor + background.red * (1 - factor)).toInt(),
            (foreground.green * factor + background.green * (1 - factor)).toInt(),
            (foreground.blue * factor + background.blue * (1 - factor)).toInt(),
        )
}

object Multiply: BlendMode {
    override fun combine(foreground: Color, background: Color): Color =
        Color(
            (foreground.red * background.red / 255.0).toInt(),
            (foreground.green * background.green / 255.0).toInt(),
            (foreground.blue * background.blue / 255.0).toInt(),
        )
}

object Screen: BlendMode {
    override fun combine(foreground: Color, background: Color): Color =
        Color(
            (255 - (255 - foreground.red) * (255 - background.red) / 255.0).toInt(),
            (255 - (255 - foreground.green) * (255 - background.green) / 255.0).toInt(),
            (255 - (255 - foreground.blue) * (255 - background.blue) / 255.0).toInt(),
        )
}

object NoBlend: BlendMode {
    override fun combine(foreground: Color, background: Color): Color = foreground
}

object BlendPlayground {
    @JvmStatic
    fun main(args: Array<String>) {
        Transparency(0.5).combine(Color.RED, Color.BLUE).draw(100, 100, "src/main/resources/darkmagenta.jpg")
        Multiply.combine(Color.RED, Color.GRAY).draw(100, 100, "src/main/resources/darkred.jpg")
        Multiply.combine(Color.RED, Color.BLUE).draw(100, 100, "src/main/resources/black.jpg")
        Screen.combine(Color.RED, Color.GRAY).draw(100, 100, "src/main/resources/lightred1.jpg")
        BlendMode.parse("screen").combine(Color.RED, Color.GRAY).draw(100, 100, "src/main/resources/lightred2.jpg")
    }
}