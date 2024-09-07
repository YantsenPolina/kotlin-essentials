package com.asya.practice

import java.io.IOException

/*
    1. Create an interface Transformation with a single process method taking an Image and returning another Image.
    2. Create 3 subclasses of Transformation.
        - Crop - x, y, w, h as constructor arguments.
        - Blend - fgImage, blendMode as constructor arguments.
        - Noop - does nothing.
       Stub the process method, for example, printing something.
    3. Add a parse method in the Transformation companion taking a String and returning a Transformation instance.
        - parse("crop 0 10 100 200") -> Crop(0, 10, 100, 200)
        - parse("blend paris.jpg" transparency) -> Blend(Image(...), Transparency)
        - parse("master yoda") -> Noop

       Hints:
        - string.split(" ") -> array of strings (words)
        - string.toInt() -> convert a String to a number
        - BlendMode.parse()
    4. Application main method.
    5. Parse transformations in the main app.
    6. Implement the transformations.
 */

interface Transformation {
    operator fun invoke(image: Image): Image

    companion object {
        fun parse(string: String): Transformation {
            val words = string.split(" ")
            val command = words[0]
            return when(command) {
                "crop" ->
                    try {
                        Crop(
                            words[1].toInt(),
                            words[2].toInt(),
                            words[3].toInt(),
                            words[4].toInt()
                        )
                    } catch (e: Exception) {
                        println("Invalid crop format. Usage: 'crop [x] [y] [w] [h]'")
                        Noop
                    }
                "blend" ->
                    try {
                        Blend(
                            Image.loadResource(words[1]),
                            BlendMode.parse(words[2])
                        )
                    } catch (e: IOException) {
                        println("Invalid image.")
                        Noop
                    } catch (e: Exception) {
                        println("Invalid blend format. Usage: 'blend [path] [mode]'")
                        Noop
                    }
                "invert" -> Invert
                "grayscale" -> Grayscale
                "sharpen" -> KernelFilter(Kernel.sharpen)
                "blur" -> KernelFilter(Kernel.blur)
                "edge" -> KernelFilter(Kernel.edge)
                "emboss" -> KernelFilter(Kernel.emboss)
                else -> Noop
            }
        }
    }
}

class Crop(val x: Int, val y: Int, val w: Int, val h: Int): Transformation {
    override fun invoke(image: Image): Image =
        try {
            image.crop(x, y, w, h)!!
        } catch (e: Exception) {
            println("Error: coordinates are out of bounds. Max coordinates: ${image.width} x ${image.height}.")
            image
        }
}

class Blend(val fgImage: Image, val mode: BlendMode): Transformation {
    override fun invoke(bgImage: Image): Image {
        if (fgImage.width != bgImage.width || fgImage.height != bgImage.height) {
            println("Error: images do not have the same sizes ${fgImage.width} x ${fgImage.height} vs ${bgImage.width} x ${bgImage.height}.")
            return bgImage
        }

        val width = fgImage.width
        val height = fgImage.height
        val result = Image.black(width, height)
        for (x in 0 ..< width)
            for (y in 0 ..< height)
                result.setColor(
                    x,
                    y,
                    mode.combine(
                        fgImage.getColor(x, y),
                        bgImage.getColor(x, y)
                    ))

        return result
    }
}

/*
    Create an Invert and a Grayscale transformation.
    Invert: for every pixel, return a new pixel where the r/g/b values are 255-r/g/b of the original pixel.
    Grayscale: for every pixel, return a new pixel with r=g=b = average of r/g/b of the original pixel.

    Add transformations in the Transformation.parse method.

    Test transformations out.

    Refactor transformations.
 */
abstract class PixelTransformation(val pixelFun: (Color) -> Color): Transformation {
    override fun invoke(image: Image): Image {
        val width = image.width
        val height = image.height
        val result = Image.black(width, height)
        for (x in 0 ..< width)
            for (y in 0 ..< height) {
                val originalColor = image.getColor(x, y)
                val newColor = pixelFun(originalColor)
                result.setColor(x, y, newColor)
            }

        return result
    }
}

object Invert: PixelTransformation({ color ->
    Color(
        255 - color.red,
        255 - color.green,
        255 - color.blue
    )
})

object Grayscale: PixelTransformation({ color ->
    val average = (color.red + color.green + color.blue) / 3
    Color(average, average, average) // Last expression is the value of the lambda.
})

object Noop: Transformation {
    override fun invoke(image: Image): Image = image
}

data class Window(val width: Int, val height: Int, val values: List<Color>)

data class Kernel(val width: Int, val height: Int, val values: List<Double>) {
    // All the values should sum up to 1.0.
    fun normalize(): Kernel {
        val sum = values.sum()
        if (sum == 0.0) return this
        return Kernel(width, height, values.map { it / sum })
    }

    // Window and Kernel must have the same width and height.
    // "Convolution".
    operator fun times(window: Window): Color {
        if (width != window.width || height != window.height)
            throw IllegalArgumentException("Kernel and Window must have the same dimensions.")

        val red = window.values
            .map { it.red }
            .zip(values) { a, b -> a * b }
            .sum()
            .toInt()
        val green = window.values
            .map { it.green }
            .zip(values) { a, b -> a * b }
            .sum()
            .toInt()
        val blue = window.values
            .map { it.blue }
            .zip(values) { a, b -> a * b }
            .sum()
            .toInt()
        return Color(red, green, blue)
    }

    companion object {
        val sharpen = Kernel(3, 3, listOf(
            0.0, -1.0, 0.0,
            -1.0, 5.0, -1.0,
            0.0, -1.0, 0.0
        )).normalize()

        val blur = Kernel(3, 3, listOf(
            1.0, 2.0, 1.0,
            2.0, 4.0, 2.0,
            1.0, 2.0, 1.0
        )).normalize()

        val edge = Kernel(3, 3, listOf(
            1.0, 0.0, -1.0,
            2.0, 0.0, -2.0,
            1.0, 0.0, -1.0
        ))

        val emboss = Kernel(3, 3, listOf(
            -2.0, -1.0, 0.0,
            -1.0, 1.0, 1.0,
            0.0, 1.0, 2.0
        ))
    }
}

data class KernelFilter(val kernel: Kernel): Transformation {
    override fun invoke(image: Image): Image =
        Image.fromColors(
            image.width,
            image.height,
            (0 ..< image.height).flatMap { y ->
                (0 ..< image.width).map { x ->
                    kernel * image.window(x ,y, kernel.width, kernel.height)
                }
            }
        )
}