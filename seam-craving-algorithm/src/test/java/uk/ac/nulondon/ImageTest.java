package uk.ac.nulondon;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

public class ImageTest {
    Pixel pixel1;
    Pixel pixel2;
    Pixel pixel3;
    Pixel pixel4;
    Pixel pixel5;
    Pixel pixel6;
    Pixel pixel7;
    Pixel pixel8;
    Pixel pixel9;
    Pixel pixel10;
    Pixel pixel11;
    Pixel pixel12;
    Pixel pixel13;
    Pixel pixel14;
    Pixel pixel15;
    Pixel pixel16;

    public Image initializeGrid() {
        Color color1 = new Color(111, 222, 0);
        Color color2 = new Color(133, 200, 0);
        Color color3 = new Color(6, 75, 20);
        Color color4 = new Color(240, 240, 45);
        Color color5 = new Color(123, 240, 0);
        Color color6 = new Color(150, 120, 240);
        Color color7 = new Color(240, 120, 240);
        Color color8 = new Color(210, 180, 30);
        Color color9 = new Color(240, 120, 120);
        Color color10 = new Color(50, 150, 100);
        Color color11 = new Color(100, 100, 240);
        Color color12 = new Color(96, 74, 1);
        Color color13 = new Color(122, 1, 0);
        Color color14 = new Color(1, 1, 121);
        Color color15 = new Color(100, 100, 240);
        Color color16 = new Color(100, 50, 150);

        ArrayList<Pixel> pixelGrid = new ArrayList<>();

        Pixel pixel1 = new Pixel(color1);
        pixelGrid.add(0, pixel1);
        Pixel pixel2 = new Pixel(color2);
        //pixelGrid.set(pixel1.next);
        pixel1.next = pixel2;
        Pixel pixel3 = new Pixel(color3);
        pixel1.next.next = pixel3;
        Pixel pixel4 = new Pixel(color4);
        pixel1.next.next.next = pixel4;
        Pixel pixel5 = new Pixel(color5);
        pixelGrid.add(1, pixel5);
        Pixel pixel6 = new Pixel(color6);
        pixel5.next = pixel6;
        Pixel pixel7 = new Pixel(color7);
        pixel5.next.next = pixel7;
        Pixel pixel8 = new Pixel(color8);
        pixel5.next.next.next = pixel8;
        Pixel pixel9 = new Pixel(color9);
        pixelGrid.add(2, pixel9);
        Pixel pixel10 = new Pixel(color10);
        pixel9.next = pixel10;
        Pixel pixel11 = new Pixel(color11);
        pixel9.next.next = pixel11;
        Pixel pixel12 = new Pixel(color12);
        pixel9.next.next.next = pixel12;
        Pixel pixel13 = new Pixel(color13);
        pixelGrid.add(3, pixel13);
        Pixel pixel14 = new Pixel(color14);
        pixel13.next = pixel14;
        Pixel pixel15 = new Pixel(color15);
        pixel13.next.next = pixel15;
        Pixel pixel16 = new Pixel(color16);
        pixel13.next.next.next = pixel16;
        pixel1.prev = null;
        pixel2.prev = pixel1;
        pixel3.prev = pixel2;
        pixel4.prev = pixel3;
        pixel5.prev = null;
        pixel6.prev = pixel5;
        pixel7.prev = pixel6;
        pixel8.prev = pixel5;
        pixel9.prev = null;
        pixel10.prev = pixel9;
        pixel11.prev = pixel10;
        pixel12.prev = pixel11;
        pixel13.prev = null;
        pixel14.prev = pixel13;
        pixel15.prev = pixel14;
        pixel16.prev = pixel15;

        this.pixel1 = pixel1;
        this.pixel2 = pixel2;
        this.pixel3 = pixel3;
        this.pixel4 = pixel4;
        this.pixel5 = pixel5;
        this.pixel6 = pixel6;
        this.pixel7 = pixel7;
        this.pixel8 = pixel8;
        this.pixel9 = pixel9;
        this.pixel10 = pixel10;
        this.pixel11 = pixel11;
        this.pixel12 = pixel12;
        this.pixel13 = pixel13;
        this.pixel14 = pixel14;
        this.pixel15 = pixel15;
        this.pixel16 = pixel16;

        Image image = new Image(pixelGrid);

//        System.out.println(image.calculatePixelEnergy(null, pixel1, pixel5));
//        System.out.println(image.calculatePixelEnergy(null, pixel2, pixel6));
//        System.out.println(image.calculatePixelEnergy(null, pixel3, pixel7));
//        System.out.println(image.calculatePixelEnergy(null, pixel4, pixel8));
//        System.out.println(image.calculatePixelEnergy(pixel1, pixel5, pixel9));
//        System.out.println(image.calculatePixelEnergy(pixel2, pixel6, pixel10));
//        System.out.println(image.calculatePixelEnergy(pixel3, pixel7, pixel11));
//        System.out.println(image.calculatePixelEnergy(pixel4, pixel8, pixel12));
//        System.out.println(image.calculatePixelEnergy(pixel5, pixel9, pixel13));
//        System.out.println(image.calculatePixelEnergy(pixel6, pixel10, pixel14));
//        System.out.println(image.calculatePixelEnergy(pixel7, pixel11, pixel15));
//        System.out.println(image.calculatePixelEnergy(pixel8, pixel12, pixel16));
//        System.out.println(image.calculatePixelEnergy(pixel9, pixel13, null));
//        System.out.println(image.calculatePixelEnergy(pixel10, pixel14, null));
//        System.out.println(image.calculatePixelEnergy(pixel11, pixel15, null));
//        System.out.println(image.calculatePixelEnergy(pixel12, pixel16, null));
//
//
//        System.out.println(pixel1);
//        System.out.println(pixel2);
//        System.out.println(pixel3);
//        System.out.println(pixel4);
//        System.out.println(pixel5);
//        System.out.println(pixel6);
//        System.out.println(pixel7);
//        System.out.println(pixel8);
//        System.out.println(pixel9);
//        System.out.println(pixel10);
//        System.out.println(pixel11);
//        System.out.println(pixel12);
//        System.out.println(pixel13);
//        System.out.println(pixel14);
//        System.out.println(pixel15);
//        System.out.println(pixel16);
//
//        System.out.println(pixel1.energy);

        //System.out.println(image.toStringEnergyGrid());

//           System.out.println("A" + pixel1.getAverageRGB());
//           System.out.println("B" + pixel2.getAverageRGB());
//           System.out.println("C" + pixel3.getAverageRGB());
//           System.out.println("D" + pixel5.getAverageRGB());
//           System.out.println("F" + pixel7.getAverageRGB());
//           System.out.println("G" + pixel9.getAverageRGB());
//           System.out.println("H" + pixel10.getAverageRGB());
//           System.out.println("I" + pixel11.getAverageRGB());

        //image.calculatePixelEnergy(pixel2, pixel6, pixel10);

        //System.out.println(pixel6.energy);

        return image;
    }

    @Test
    void calculationEnergyTest() {
        Image myImage = initializeGrid();

        double energyPixel1 = myImage.calculatePixelEnergy(pixel2, pixel6, pixel10);
        double energyPixel11 = myImage.calculatePixelEnergy(pixel7, pixel10, pixel15);

        System.out.println(pixel6.energy);

        Assertions.assertThat(energyPixel1).isEqualTo(155.35049976674605);
        Assertions.assertThat(energyPixel11).isEqualTo(275.67654154018174);

    }

    @Test
    void findLowestEnergySeam() {
        Image myImage = initializeGrid();
        myImage.getSeamEnergy();

        Assertions.assertThat(myImage.getSeamEnergy().toString()).isEqualTo("[uk.ac.nulondon.Pixel@212b5695, uk.ac.nulondon.Pixel@446293d, uk.ac.nulondon.Pixel@69997e9d, uk.ac.nulondon.Pixel@793be5ca]");

    }

    @Test
    void bluestSeamTest() {
        Image myImage = initializeGrid();
        myImage.getSeamBluest();

        Assertions.assertThat(myImage.getSeamBluest().toString()).isEqualTo("[uk.ac.nulondon.Pixel@3059cbc, uk.ac.nulondon.Pixel@7ea9e1e2, uk.ac.nulondon.Pixel@24fcf36f, uk.ac.nulondon.Pixel@10feca44]");
    }

    @Test
    void highlightBluestSeamTest() {
        ImageEditing myImage = new ImageEditing(initializeGrid());

        ArrayList<Color> blueList = new ArrayList<>();
        blueList.add(Color.blue);
        blueList.add(Color.blue);
        blueList.add(Color.blue);
        blueList.add(Color.blue);

        Assertions.assertThat(myImage.highlightBluestSeam()).isEqualTo(blueList);
    }

    @Test
    void highlightLowestEnergySeamTest() {
        ImageEditing myImage = new ImageEditing(initializeGrid());

        ArrayList<Color> redList = new ArrayList<>();
        redList.add(Color.red);
        redList.add(Color.red);
        redList.add(Color.red);
        redList.add(Color.red);

        Assertions.assertThat(myImage.highlightLowestEnergySeam()).isEqualTo(redList);
    }

    @Test
    void deleteSeamTest(){
        ImageEditing myImage = new ImageEditing(initializeGrid());
        myImage.deleteSeam(1);

        Assertions.assertThat(myImage.contains(initializeGrid().getSeamBluest())).isEqualTo(false);
        Assertions.assertThat(myImage.contains(initializeGrid().getSeamEnergy())).isEqualTo(false);
    }

    @Test
    void undo() {
        ImageEditing myImage = new ImageEditing(initializeGrid());
        myImage.deleteSeam(1);
        myImage.undo();

        Assertions.assertThat(myImage.equals(initializeGrid())).isEqualTo(true);
    }



}


