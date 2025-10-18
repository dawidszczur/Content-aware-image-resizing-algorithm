package uk.ac.nulondon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Math.sqrt;

/**
 * Represents an image consisting of a grid of pixels. This class provides functionality
 * to manipulate and analyze the image data.
 */
public class Image {

    private ArrayList<Pixel> pixelGrid;
    private String lastImportedFilePath;
    int counter = 0;

    /**
     * Constructs a new Image with an empty pixel grid.
     */
    public Image() {
        this.pixelGrid = new ArrayList<>();
        lastImportedFilePath = "";
    }

    /**
     * Constructs a new Image with a specified pixel grid and calculates the energy grid.
     *
     * @param pixelGrid the initial grid of pixels
     */
    public Image(ArrayList<Pixel> pixelGrid) {
        this.pixelGrid = pixelGrid;
        energyGrid();
        lastImportedFilePath = "";
    }

    /**
     * Resets the counter used for image file naming.
     */
    public void resetCounter() {
        counter = 0;
    }

    /**
     * Sets a pixel at the specified index in the pixel grid.
     *
     * @param index the index at which the pixel should be set
     * @param pixel the pixel to set
     */
    public void setAt(int index, Pixel pixel) {
        pixelGrid.set(index, pixel);
    }

    /**
     * Checks if an image has been imported based on whether the file path is empty.
     *
     * @return true if an image has been imported, false otherwise
     */
    public boolean hasImage() {
        return !lastImportedFilePath.isEmpty();
    }

    /**
     * Adds an element to a collection and returns the new list.
     *
     * @param element the element to add to the collection
     * @param elements the collection of elements
     * @return a new list containing all elements with the added element
     */
    private static <T> List<T> concat(T element, Collection<? extends T> elements) {
        List<T> result = new ArrayList<>();
        result.add(element);
        result.addAll(elements);
        return result;
    }

    /**
     * Returns the height of the image based on the size of the pixel grid.
     *
     * @return the height of the image
     */
    public int getHeight() {
        return pixelGrid.size();
    }

    /**
     * Calculates and returns the width of the image by traversing the linked pixels.
     *
     * @return the width of the image
     */

    public int getWidth() {
        Pixel iter = pixelGrid.getFirst();
        int size = 0;
        while (iter != null) {
            size = size + 1;
            iter = iter.next;
        }
        return size;
    }

    /**
     * Initializes the pixel grid based on an image file located at the specified file path.
     * Reads the image and stores its pixels into the pixel grid.
     *
     * @param filePath the path to the image file to read
     * @throws Exception if the file cannot be read or the image initialization fails
     */
    public void pixelInitialize(String filePath) throws Exception {

        // Code to read image into an image buffer
        File originalFile = new File(filePath);
        BufferedImage oldImg = ImageIO.read(originalFile);

        Pixel currentPixel = null;
        pixelGrid = new ArrayList<>();
        // This loops through the image pixel by pixel
        // we will read in the original color then change
        // the color and store into new buffer
        for (int y = 0; y < oldImg.getHeight(); y++) {
            for (int x = 0; x < oldImg.getWidth(); x++) {
                Color newColor = new Color(oldImg.getRGB(x, y));
                Pixel newPixel = new Pixel(newColor);
                if (x == 0) {
                    pixelGrid.add(newPixel);
                } else {
                    currentPixel.next = newPixel;
                    newPixel.prev = currentPixel;
                }
                currentPixel = newPixel;
            }
        }

        energyGrid();

        lastImportedFilePath = filePath;
    }

    /**
     * Saves the current state of the image grid to a new image file.
     *
     * @throws IOException if there is an error writing the image to file
     */
    public void saveNew() throws IOException {
        // Assuming getWidth() and getHeight() return the correct dimensions
        BufferedImage newImg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < getHeight(); y++) {
            Pixel iter = pixelGrid.get(y);
            for (int x = 0; x < getWidth(); x++) {
                newImg.setRGB(x, y, iter.color.getRGB());
                iter = iter.next;
            }
        }

        // new file to store altered image
        File newFile = new File("newImg" + counter + ".png");
        ImageIO.write(newImg, "png", newFile); // Write the BufferedImage to the file
    }

    /**
     * Determines if the specified list of pixels contains any pixel from the pixel grid.
     *
     * @param pixelList the list of pixels to check against the grid
     * @return true if any pixel from pixelList is found in the pixel grid, false otherwise
     */
    public boolean contains(List<Pixel> pixelList) {
        Pixel thisPixel = pixelGrid.getFirst();
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (pixelList.contains(thisPixel)) {
                    return true;
                }
                thisPixel = pixelGrid.get(y).next;
            }
        }

        return false;
    }

    /**
     * Calculates the energy of a pixel based on its surrounding pixels.
     *
     * @param A the average RGB value of the top-left pixel
     * @param B the average RGB value of the top-center pixel
     * @param C the average RGB value of the top-right pixel
     * @param D the average RGB value of the middle-left pixel
     * @param F the average RGB value of the middle-right pixel
     * @param G the average RGB value of the bottom-left pixel
     * @param H the average RGB value of the bottom-center pixel
     * @param I the average RGB value of the bottom-right pixel
     * @return the calculated energy value
     */
    public double energyCalculation(double A, double B, double C, double D, double F, double G, double H, double I) {
        double horizontalEnergy = (A + 2 * D + G) - (C + 2 * F + I);
        double verticalEnergy = (A + 2 * B + C) - (G + 2 * H + I);
        return sqrt((horizontalEnergy * horizontalEnergy) + (verticalEnergy * verticalEnergy));
    }

    /**
     * Calculates the energy of a pixel given its top, middle, and bottom neighbors.
     *
     * @param topPixel the pixel directly above the current pixel
     * @param middlePixel the current pixel for which energy is being calculated
     * @param bottomPixel the pixel directly below the current pixel
     * @return the energy of the middle pixel
     */
    public double calculatePixelEnergy(Pixel topPixel, Pixel middlePixel, Pixel bottomPixel) {
        double A = ((topPixel != null) && (topPixel.prev != null)) ? topPixel.prev.getAverageRGB() : middlePixel.getAverageRGB();
        double B = topPixel != null ? topPixel.getAverageRGB() : middlePixel.getAverageRGB();
        double C = (topPixel != null && topPixel.next != null) ? topPixel.next.getAverageRGB() : middlePixel.getAverageRGB();
        double D = (middlePixel.prev != null) ? middlePixel.prev.getAverageRGB() : middlePixel.getAverageRGB();
        //double E = middlePixel.getAverageRGB();
        double F = (middlePixel.next != null) ? middlePixel.next.getAverageRGB() : middlePixel.getAverageRGB();
        double G = (bottomPixel != null && bottomPixel.prev != null) ? bottomPixel.prev.getAverageRGB() : middlePixel.getAverageRGB();
        double H = (bottomPixel != null) ? bottomPixel.getAverageRGB() : middlePixel.getAverageRGB();
        double I = (bottomPixel != null && bottomPixel.next != null) ? bottomPixel.next.getAverageRGB() : middlePixel.getAverageRGB();

        middlePixel.energy = energyCalculation(A, B, C, D, F, G, H, I);
        return energyCalculation(A, B, C, D, F, G, H, I);
    }

    /**
     * Calculates and updates the energy values for all pixels in the grid.
     */
    public void energyGrid() {
        Pixel middlePixel = pixelGrid.getFirst();
        Pixel topPixel = null;
        Pixel bottomPixel = pixelGrid.get(1);

        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (y == 0) {
                    middlePixel.energy = calculatePixelEnergy(topPixel, middlePixel, bottomPixel);
                } else if (y == getHeight() - 1) {
                    middlePixel.energy = calculatePixelEnergy(topPixel, middlePixel, bottomPixel);
                } else {
                    middlePixel.energy = calculatePixelEnergy(topPixel, middlePixel, bottomPixel);
                }
                middlePixel = middlePixel.next;
                if (topPixel == null) {
                    continue;
                } else {
                    topPixel = topPixel.next;
                }

                if (bottomPixel == null) {
                    continue;
                } else {
                    bottomPixel = bottomPixel.next;
                }

            }
            middlePixel = pixelGrid.get(y);
            if (y == getHeight() - 1) {
                bottomPixel = null;
            } else {
                bottomPixel = pixelGrid.get(y + 1);
            }

            if (y == 0) {
                topPixel = null;
            } else {
                topPixel = pixelGrid.get(y - 1);
            }

        }
    }

    /**
     * Calculates a grid representing the blue intensity of each pixel.
     *
     * @param pixelGrid The grid of pixels to analyze.
     * @return A 2D array where each entry represents the blue intensity of the corresponding pixel.
     */
    public double[][] bluestGrid(ArrayList<Pixel> pixelGrid) {
        double[][] myBlueGrid = new double[getHeight()][getWidth()];
        Pixel middlePixel = pixelGrid.getFirst();
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                myBlueGrid[y][x] = middlePixel.color.getBlue();
                middlePixel = middlePixel.next;
            }
            middlePixel = pixelGrid.get(y);
        }
        return myBlueGrid;
    }

    /**
     * Computes the seam with the lowest energy across the image grid.
     *
     * @return A list of pixels forming the lowest energy seam.
     */
    public List<Pixel> getSeamEnergy() {
        double[] previousValues = new double[getWidth()]; // the row above's values
        double[] currentValues = new double[getWidth()];  // current row's values
        List<List<Pixel>> previousSeams = new ArrayList<>();
        List<List<Pixel>> currentSeams = new ArrayList<>();
        Pixel currentPixel = pixelGrid.getFirst();
        energyGrid();

        int col = 0;

        // initializing for first row
        while (col < getWidth()) {
            previousValues[col] = currentPixel.energy;

            // one seam per column
            previousSeams.add(concat(currentPixel, List.of()));
            col++;
            currentPixel = currentPixel.next;

        }

        // compute values and paths for each row
        for (int row = 1; row < getHeight(); row++) {
            col = 0;
            currentPixel = pixelGrid.get(row);
            while (col < getWidth()) {
                double bestSoFar = previousValues[col];
                int ref = col;
                // check both adjacent pixels
                // if left exists and is better, update
                if (col > 0 && previousValues[col - 1] < bestSoFar) {
                    bestSoFar = previousValues[col - 1];
                    ref = col - 1;
                }
                // if right exists and is better, update
                if (col < getWidth() - 1 && previousValues[col + 1] < bestSoFar) {
                    bestSoFar = previousValues[col + 1];
                    ref = col + 1;
                }

                // update the value with the current pixel
                currentValues[col] = bestSoFar + currentPixel.energy;

                // append this new pixel to existing seams
                currentSeams.add(concat(currentPixel, previousSeams.get(ref)));

                col++;
                // move to neighbor
                currentPixel = currentPixel.next;
            }

            // update previous values/seams
            // and reset current values/seams
            previousValues = currentValues;
            currentValues = new double[getWidth()];
            previousSeams = currentSeams;
            currentSeams = new ArrayList<>();
        }

        // find the seam with the min sum
        double minValue = previousValues[0];
        int minIndex = 0;
        for (int i = 1; i < getWidth(); i++) {
            if (previousValues[i] < minValue) {
                minIndex = i;
                minValue = previousValues[i];
            }
        }

        return previousSeams.get(minIndex);
    }


    /**
     * Computes the seam with the highest blue intensity across the image grid.
     *
     * @return A list of pixels forming the bluest seam.
     */
    public List<Pixel> getSeamBluest() {
        double[] previousValues = new double[getWidth()]; // the row above's values
        double[] currentValues = new double[getWidth()];  // current row's values
        List<List<Pixel>> previousSeams = new ArrayList<>();
        List<List<Pixel>> currentSeams = new ArrayList<>();
        Pixel currentPixel = pixelGrid.getFirst();

        int col = 0;

        // initializing for first row
        while (col < getWidth()) {
            previousValues[col] = currentPixel.color.getBlue();

            // one seam per column
            previousSeams.add(concat(currentPixel, List.of()));
            col++;
            currentPixel = currentPixel.next;

        }

        // compute values and paths for each row
        for (int row = 1; row < getHeight(); row++) {
            col = 0;
            currentPixel = pixelGrid.get(row);
            while (col < getWidth()) {
                double bestSoFar = previousValues[col];
                int ref = col;
                // check both adjacent pixels
                // if left exists and is better, update
                if (col > 0 && previousValues[col - 1] > bestSoFar) {
                    bestSoFar = previousValues[col - 1];
                    ref = col - 1;
                }
                // if right exists and is better, update
                if (col < getWidth() - 1 && previousValues[col + 1] > bestSoFar) {
                    bestSoFar = previousValues[col + 1];
                    ref = col + 1;
                }

                // update the value with the current pixel
                currentValues[col] = bestSoFar + currentPixel.color.getBlue();

                // append this new pixel to existing seams
                currentSeams.add(concat(currentPixel, previousSeams.get(ref)));

                col++;
                // move to neighbor
                currentPixel = currentPixel.next;
            }

            // update previous values/seams
            // and reset current values/seams
            previousValues = currentValues;
            currentValues = new double[getWidth()];
            previousSeams = currentSeams;
            currentSeams = new ArrayList<>();
        }

        // find the seam with the min sum
        double minValue = previousValues[0];
        int minIndex = 0;
        for (int i = 1; i < getWidth(); i++) {
            if (previousValues[i] > minValue) {
                minIndex = i;
                minValue = previousValues[i];
            }
        }

        return previousSeams.get(minIndex);
    }

}