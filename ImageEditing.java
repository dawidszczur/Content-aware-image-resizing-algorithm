package uk.ac.nulondon;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ImageEditing {
    Stack<List<Pixel>> undoStack = new Stack<>();
    private Image image;


    /**
     * Constructor for image editing
     * @param i
     */
    public ImageEditing(Image i) {
        this.image = i;
    }

    /**
     * Constructor for image editing
     */
    public ImageEditing() {
        this.image = new Image();
    }

    /**
     * Saves the current image
     * @throws IOException
     */
    public void save() throws IOException {
        image.saveNew();
    }

    /**
     * Imports an image from the given file path
     * @param filePath
     * @throws Exception
     */
    public void importImage(String filePath) throws Exception {
        image.pixelInitialize(filePath);
    }

    /**
     * Checks if the image exists
     * @return boolean
     */
    public boolean hasImage() {
        return image.hasImage();
    }

    /**
     * Checks if the image contains the given list of pixels
     * @param pixelList
     * @return boolean
     */
    public boolean contains(List<Pixel> pixelList) {
        return image.contains(pixelList);
    }

    /**
     * Resets the counter of the image
     */
    public void resetCounter() {
        image.resetCounter();
    }

    /**
     * Increments the counter of the image
     */
    public void incrementCounter() {
        image.counter++;
    }

    /**
     * Highlights the bluest seam in the image
     * @return ArrayList<Color>
     */
    public ArrayList<Color> highlightBluestSeam() {
        ArrayList<Color> highlight = new ArrayList<>();
        for (Pixel p : image.getSeamBluest()) {
            p.color = Color.blue;
            highlight.add(p.color);
        }

        undoStack.push(image.getSeamBluest());
        return highlight;
    }

    /**
     * Highlights the seam with the lowest energy in the image
     * @return ArrayList<Color>
     */
    public ArrayList<Color> highlightLowestEnergySeam() {
        ArrayList<Color> highlight = new ArrayList<>();
        for (Pixel p : image.getSeamEnergy()) {
            p.color = Color.red;
            highlight.add(p.color);
        }

        undoStack.push(image.getSeamEnergy());
        return highlight;
    }

    /**
     * Checks if the undo stack is empty
     * @return boolean
     */
    public boolean stackEmpty() {
        if (undoStack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Deletes a seam from the image based on the choice of bluest or lowest energy seam
     * @param choose
     */
    public void deleteSeam(int choose) {
        List<Pixel> seam = new ArrayList<>();

        if (choose == 0) {
            seam = image.getSeamEnergy();
        } else if (choose == 1) {
            seam = image.getSeamBluest();
        } else {
            return;
        }

        undoStack.push(new ArrayList<>(seam));
        int counter = image.getHeight() -1;
        for (Pixel p : seam) {
            if (p.prev != null) {
                p.prev.next = p.next;
            } else {
                image.setAt(counter, p.next);
            }

            if (p.next != null) {
                p.next.prev = p.prev;
            } else {
                if (p.prev != null) {
                    p.prev.next = null;
                }
            }
            counter--;
        }
    }

    /**
     * Undoes the last operation performed
     */
    public void undo() {
        if (undoStack == null) {
            return;
        }

        List<Pixel> seam = undoStack.pop();
        int counter = image.getHeight() -1;
        for (Pixel p : seam) {
            if (p.prev != null) {
                p.prev.next = p;
            } else {
                image.setAt(counter, p);
            }

            if (p.next != null) {
                p.next.prev = p;
            } else {

            }
            counter--;
        }
    }
}
