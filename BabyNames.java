/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.babynames;

/**
 *
 * @author Deema Hamidah, Shumokh Abdullah, Mawaddah Eyad
 * @HW2 BabyNameRanking
 * @date 2/17/2023
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BabyNames {

    public static void main(String[] args) throws FileNotFoundException 
    {

        // Create arrays to hold 1000 names for each year
        String[][] boyNames = new String[10][1000];
        String[][] girlNames = new String[10][1000];

        System.out.println("----------------------------BabyNamesRanking-----"
                + "----------------------");
                        
        //prompt the user to enter 1 or 2 based on his preference
        Scanner scanner = new Scanner(System.in);
        
        do 
        {

            System.out.print("""
                             Write 1 to check the name's ranking in the specified year
                              Write 2 to check the name's ranking in all the years:""");
            int search = scanner.nextInt();
            // Keep track of the current index in each array
            int boyIndex = 0;
            int girlIndex = 0;

            // Loop over each year's data file
            for (int i = 0; i < 10; i++) 
            {
                // Open the data file for the current year
                Scanner input = new Scanner(new File("/Users/shomokh"
                        + "/NetBeansProjects/BabyNames/src/main/java/com/"
                        + "mycompany/babynames/babynamesranking"
                        + (i + 2001) + ".txt"));

                // Read each line of the data file
                while (input.hasNextLine()) 
                {
                    
                    // Split the line into its components
                    String line = input.nextLine();
                    String[] components = line.split("\\s+");
                    String ranking = components[0]; // Rank 
                    String boyName = components[1]; // Boy name
                    String boyNum = components[2];  // Number of boys with this name 
                    String girlName = components[3]; // Girl name
                    String girlNum = components[4];  // Number of girls with this name 

                    // Add the current boy and girl name to their respective arrays
                    boyNames[i][boyIndex] = boyName;
                    girlNames[i][girlIndex] = girlName;

                    // Increment the current index in each array
                    boyIndex++;
                    girlIndex++;
                }

                // Reset the current index for each array for the next year's data
                boyIndex = 0;
                girlIndex = 0;
            }

            
            // Ask the user for the name, year, and gender they're interested in
            // Get the gender input from the user
            String gender = "";
            boolean validGenderInput = false;
            while (!validGenderInput) 
            {
                System.out.print("Enter the gender (M/F): ");
                gender = scanner.next();
                if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F")) 
                {
                    validGenderInput = true;
                } 
                else 
                {
                    System.out.println("Invalid gender input. Please enter either M or F.");
                }
            }

            // Get the name input from the user
            System.out.print("Enter the name: ");
            String name = scanner.next();
            
            
            if (search == 1) 
            {
                // Get the year input from the user if the user chose option 1
                int year = 0;
                boolean validYearInput = false;
                while (!validYearInput) //while true, it has a valid year input
                {
                    System.out.print("Enter the year: ");
                    if (scanner.hasNextInt()) //if the input was int
                    {
                        year = scanner.nextInt();
                        if (year >= 2001 && year <= 2010) 
                        {
                            validYearInput = true;
                        } 
                        else 
                        {
                            System.out.println("Invalid year input."
                                    + " Please enter a year between 2001 and 2010.");
                        }
                    } 
                    else 
                    {
                        System.out.println("Invalid year input."
                                + " Please enter a valid year.");
                        scanner.next();
                    }
                }
                
                /* Search for the name in the appropriate array for the given
                year and gender */
                int index = -1;
                if (gender.equalsIgnoreCase("M")) 
                {
                    for (int i = 0; i < 1000; i++) 
                    {
                        if (boyNames[year - 2001][i].equalsIgnoreCase(name)) 
                        {
                            index = i + 1; //if name found then break the loop
                            break;
                        }
                    }
                } 
                else if (gender.equalsIgnoreCase("F")) 
                {
                    for (int i = 0; i < 1000; i++) 
                    {
                        if (girlNames[year - 2001][i].equalsIgnoreCase(name)) 
                        {
                            index = i + 1;
                            break;
                        }
                    }
                }

                // Display the result of the search
                if (index == -1) 
                {
                    System.out.println("Name not found.");
                } 
                else 
                {
                    System.out.println(name.substring(0, 1).toUpperCase()
                            + name.substring(1) + " was ranked #" + index + " in "
                            + year + ".");
                }
                System.out.println();
            } 
            
            else if (search == 2) 
            {
                /* Search for the name in the appropriate array for the given
                   gender */
                for (int i = 0; i < 10; i++) 
                {
                    int index = -1;
                    if (gender.equalsIgnoreCase("M")) 
                    {
                        for (int j = 0; j < 1000; j++) 
                        {
                            if (boyNames[i][j].equalsIgnoreCase(name)) 
                            {
                                index = j + 1; //if name found then break the loop
                                break;
                            }
                        }
                    } 
                    else if (gender.equalsIgnoreCase("F")) 
                    {
                        for (int j = 0; j < 1000; j++) 
                        {
                            if (girlNames[i][j].equalsIgnoreCase(name)) 
                            {
                                index = j + 1;
                                break;
                            }
                        }
                    }

                    // Display the result of the search
                    if (index == -1) 
                    {
                        System.out.println("Name not found.");
                    } 
                    else 
                    {
                        System.out.println(name.substring(0, 1).toUpperCase()
                                + name.substring(1) + " was ranked #" + index + " in "
                                + "year" + (2001 + i));
                    }
                }
                System.out.println();
            }

            System.out.print("Do you want to search about another name(Yes/No)? ");
        } 
        while (scanner.next().equalsIgnoreCase("Yes"));
        System.out.print("------------------------------Thank you"
                + "---------------------------------");

        scanner.close();
    }
}
