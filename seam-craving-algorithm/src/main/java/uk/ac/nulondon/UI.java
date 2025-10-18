package uk.ac.nulondon;

import java.awt.*;
import java.util.List;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    private ImageEditing imageHandler;
    private Image image;
    private int choose = -1;

    public UI() {
        imageHandler = new ImageEditing();
    }

    //print user interface menu to user
    private static void printMenu() {
        System.out.println("Please enter a command");
        System.out.println("1. - Show bluest seam");
        System.out.println("2. - Show seam with lowest energy");
        System.out.println("3. - Delete the seam and show image");
        System.out.println("4. - Undo previous edit");
        System.out.println("5. - Quit");
    }

    public static void main(String[] args) throws Exception {
        UI ui = new UI();
        ui.run();
    }

    /**
     * Runs the UI and handles user interaction.
     */
    public void run() throws IOException {
        imageHandler.resetCounter();
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to specify the file path to the image
        System.out.print("Enter the file path of the image: ");
        String filePath = scanner.nextLine();
        try {
            imageHandler.importImage(filePath);
            System.out.println("Image imported successfully.");
        } catch (IOException e) {
            System.out.println("Error importing image: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        boolean shouldQuit = false;

        while (!shouldQuit) {
            printMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    if (imageHandler.hasImage()) {
                        System.out.print("Are you sure you want to highlight the bluest seam? (y/n): ");
                        char confirmation = scanner.next().charAt(0);
                        if (confirmation == 'y' || confirmation == 'Y') {
                            imageHandler.highlightBluestSeam();
                            imageHandler.save();
                            imageHandler.incrementCounter();
                            choose = 1;
                        }
                    } else {
                        System.out.println("No image has been imported yet.");
                    }
                    break;
                case 2:
                    if (imageHandler.hasImage()) {
                        System.out.print("Are you sure you want to highlight the lowest energy seam? (y/n): ");
                        char confirmation = scanner.next().charAt(0);
                        if (confirmation == 'y' || confirmation == 'Y') {
                            imageHandler.highlightLowestEnergySeam();
                            imageHandler.save();
                            imageHandler.incrementCounter();
                            choose = 0;
                        }
                    } else {
                        System.out.println("No image has been imported yet.");
                    }
                    break;
                case 3:
                    if (imageHandler.hasImage()) {
                        System.out.print("Are you sure you want to delete this? (y/n): ");
                        char confirmation = scanner.next().charAt(0);
                        if (confirmation == 'y' || confirmation == 'Y') {
                            imageHandler.deleteSeam(choose);
                            imageHandler.save();
                            imageHandler.incrementCounter();
                            choose = -1;
                        }
                    } else {
                        System.out.println("No image has been imported yet.");
                    }
                    break;
                case 4:
                    if (imageHandler.stackEmpty()) {
                        System.out.println("All changes undone");
                    } else {
                        imageHandler.undo();
                        imageHandler.save();
                    }
                    break;
                case 5:
                    shouldQuit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    /**
     * Gets the user's choice from the menu.
     *
     * @param scanner The scanner object to get user input.
     * @return The user's choice.
     */
    private int getUserChoice(Scanner scanner) {
        int choice = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                scanner.nextLine();
            }
        }

        scanner.nextLine();
        return choice;
    }

}
