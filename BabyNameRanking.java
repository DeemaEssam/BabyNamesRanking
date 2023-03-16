/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.babynameranking;

/**
 *
 * @author deema, shumokh, mawadda
 * @HW2 BabyNameRanking
 * @date 2/17/2023
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class BabyNameRanking {
    public static void main(String[] args) {
        // Create two-dimensional arrays to store names
        String[][] boyNames = new String[10][1000];
        String[][] girlNames = new String[10][1000];

        // Read data from files into arrays
        for (int i = 0; i < 10; i++) {
            String filename = "data/babynamesranking" + (2001 + i) + ".txt";

            try (Scanner input = new Scanner(new File(filename))) {
                while (input.hasNextLine()) {
                    String line = input.nextLine();
                    String[] tokens = line.split("\\s+");
                    int rank = Integer.parseInt(tokens[0]);
                    boyNames[i][rank - 1] = tokens[1];
                    girlNames[i][rank - 1] = tokens[2];
                }
            } catch (FileNotFoundException ex) {
                System.out.println("File not found: " + filename);
            }
        }

        try (Scanner scanner = new Scanner(System.in)) {
            // Prompt user for input
            System.out.print("Enter the year: ");
            int year = scanner.nextInt();
            System.out.print("Enter the gender (M/F): ");
            String gender = scanner.next().toUpperCase();
            System.out.print("Enter the name: ");
            String name = scanner.next().toUpperCase();

            // Find the index of the year and gender
            int yearIndex = year - 2001;
            int genderIndex = gender.equals("M") ? 0 : (gender.equals("F") ? 1 : -1);

            if (genderIndex == -1) {
                System.out.println("Invalid gender input");
                return;
            }

            // Search for the name in the appropriate array
            int rank = -1;
            String[][] names = genderIndex == 0 ? boyNames : girlNames;
            for (int i = 0; i < 1000; i++) {
                if (names[yearIndex][i] != null && names[yearIndex][i].equalsIgnoreCase(name)) {
                    rank = i + 1;
                    break;
                }
            }

            // Print the result
            if (rank != -1) {
                System.out.println(name + " is ranked #" + rank + " in year " + year);
            } else {
                System.out.println("The name " + name + " is not ranked in year " + year);
            }
        }
    }
}