package com.asya.practice

import java.awt.Graphics
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

// Never expose mutable state outside this class.
class Image(private val bufferedImage: BufferedImage) {
    val width = bufferedImage.width
    val height = bufferedImage.height

    fun getColor(x: Int, y: Int): Color =
        Color.fromHex(bufferedImage.getRGB(x, y))

    fun setColor(x: Int, y: Int, color: Color) =
        bufferedImage.setRGB(x, y, color.toInt())

    fun draw(g: Graphics) {
        g.drawImage(bufferedImage, 0, 0, null)
    }

    fun save(path: String) =
        ImageIO.write(bufferedImage, "JPG", File(path))

    fun saveResource(path: String) =
        save("src/main/resources/$path")

    /*
        1. Check dimensions - return null if any dimension is invalid.
        2. Create a black image of width x height.
        3. Iterate through coordinates startX ..< startX + w and startY ..< startY + h.
            - Use bufferedImage.getRGB to get a pixel from the original image.
            - Use resultImage.bufferedImage.setRGB to set a pixel in the result.
            - Calculate the coordinates.
        4. Return the resultImage.

     */
    fun crop(startX: Int, startY: Int, w: Int, h: Int): Image? {
        if (startX < 0 || startX >= width || startY < 0 || startY >= height) return null
        if (w < 0 || startX + w >= width || h < 0 || startY + h >= height) return null

        val result = Image.black(w, h)
        for (x in startX ..< (startX + w))
            for (y in startY ..< (startY + h)) {
                val originalPixel = bufferedImage.getRGB(x, y)
                result.bufferedImage.setRGB(x - startX, y - startY, originalPixel)
            }

        return result
    }

    fun window(xc: Int, yc: Int, width: Int, height: Int): Window {
        val offsetX = (width - 1) / 2
        val offsetY = (height - 1) / 2
        val horizontalCoordinates = ((xc - offsetX) .. (xc + offsetX))
            .map { x ->
                if (x <= 0) 0
                else if (x >= this.width) this.width - 1
                else x
            }
        val verticalCoordinates = ((yc - offsetY) .. (yc + offsetY))
            .map { y ->
                if (y <= 0) 0
                else if (y >= this.height) this.height - 1
                else y
            }
        val colors = verticalCoordinates
            .flatMap { y ->
                horizontalCoordinates.map { x -> getColor(x ,y) }
            }
        return Window(width, height, colors)
    }

    companion object {
        fun black(width: Int, height: Int): Image {
            val bufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
            val pixels = IntArray(width * height) { 0 }
            bufferedImage.setRGB(0, 0, width, height, pixels, 0, width)
            return Image(bufferedImage)
        }

        fun fromColors(width: Int, height: Int, colors: List<Color>): Image {
            val bufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
            val pixels = colors.map { it.toInt() }.toIntArray()
            bufferedImage.setRGB(0, 0, width, height, pixels, 0, width)
            return Image(bufferedImage)
        }

        fun load(path: String) =
            Image(ImageIO.read(File(path)))

        fun loadResource(path: String) =
            load("src/main/resources/$path")
    }
}

object ImagePlayground {
    @JvmStatic
    fun main(args: Array<String>) {
        Image.loadResource("asya.jpg")
            .crop(500, 1100, 1000, 500)?.saveResource("cropped.jpg")
    }
}