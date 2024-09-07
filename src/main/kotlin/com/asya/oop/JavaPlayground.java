package com.asya.oop;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JavaPlayground {
    public static void main(String[] args) {
        BufferedImage image = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);
        int[] pixels = new int[400];
        image.setRGB(0, 0, 20, 20, pixels, 0, 20);
        try {
            ImageIO.write(image, "JPG", new File("src/main/resources/black.jpg"));
        } catch (IOException e) {
            System.out.println("Something bad happened.");
        }
    }
}
