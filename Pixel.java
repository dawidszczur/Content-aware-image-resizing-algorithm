package uk.ac.nulondon;

import java.awt.*;

public class Pixel {
    Color color;
    Pixel prev;
    Pixel next;
    double energy;

    /**
     * Constructor that initializes Pixel with color
     * @param color
     */
    public Pixel(Color color) {
        this.color = color;
        this.prev = prev;
        this.next = next;
    }

    /**
     * Constructor that initializes Pixel with default values
     */
    public Pixel() {
        this.color = color;
        this.prev = prev;
        this.next = next;
    }

    /**
     * Calculates the average RGB value of the pixel's color
     * @return average RGB value as a double
     */
    public double getAverageRGB() {
        return (color.getBlue() + color.getGreen() + color.getRed()) / 3.0;
    }


}
