package RedBoxInventorySystem.RedBoxFunctions.StorageOperations;

import RedBoxInventorySystem.RedBoxFunctions.Common.*;
import RedBoxInventorySystem.RedBoxFunctions.Movie.*;

import java.io.File;
import java.io.PrintWriter;

import java.util.Scanner;

public class InventoryOperations {

    public static Scanner readFile;
    public static PrintWriter writeFile;

    public final static String FILE_NAME = "RedBoxInventorySystem\\RedBoxFunctions\\LogTransactions\\LogInventory.txt";
    public final static String TEMP_NAME = "RedBoxInventorySystem\\RedBoxFunctions\\LogTransactions\\temp.txt";

    // ADD MOVIE: Will add the new movie to the end of the file
    public static void AddMovie(Movie movie) {
        File currFile = getScanner();
        File temp = getPrintWriter();
        while (readFile.hasNextLine()) {

            writeFile.write(readFile.nextLine() + "\n");
        }
        writeFile.write("\"" + movie.getTitle() + "\"," + movie.getAvailable() + "," + movie.getRented() + "\n");
        renameFile(temp, currFile);
    }

    // UPDATE THE MOVIE: Will find the movie in the file and add it in that spot
    public static void LogMovie(Movie updateMovie) {
        Boolean isUpdate = false;
        File currFile = getScanner();
        File temp = getPrintWriter();
        while (readFile.hasNextLine()) {
            if (isUpdate) {
                writeFile.write(readFile.nextLine() + "\n");
            } else {
                String[] movieObj = readFile.nextLine().split(",");
                if (movieObj[0].replace("\"", "").equals(updateMovie.getTitle())) {
                    writeFile.write(
                            "\"" + updateMovie.getTitle() + "\"," + updateMovie.getQuantity() + ","
                                    + updateMovie.getRented()
                                    + "\n");
                    isUpdate = true;
                } else {
                    writeFile.write(
                            movieObj[0] + "," + movieObj[1] + "," + movieObj[2]
                                    + "\n");
                }
            }
        }
        renameFile(temp, currFile);
    }

    public static void removeMovie(Movie updateMovie) {
        File currFile = getScanner();
        File temp = getPrintWriter();
        boolean isRemoved = false;
        while (readFile.hasNextLine()) {

            if (isRemoved) {
                writeFile.write(readFile.nextLine());
            } else {
                String[] movieObj = readFile.nextLine().split(",");
                if (!movieObj[0].replace("\"", "").equals(updateMovie.getTitle())) {
                    writeFile.write(
                            "" + movieObj[0] + "," + movieObj[1] + "," + movieObj[2]
                                    + "\n");

                } else {
                    isRemoved = true;
                }
            }
        }
        renameFile(temp, currFile);
    }

    public static void printLog() {

        getScanner();
        while (readFile.hasNextLine()) {
            String[] movie = readFile.nextLine().split(",");
            for (int i = 0; i < movie.length; i++) {
                movie[i] = movie[i].trim();
            }
            movie[0] = movie[0].replace('"', ' ').trim();
            StringBuilder stringMovie = new StringBuilder();
            // Format the movie using left justification and spaces
            stringMovie.append(String.format("%-20s", movie[0]));
            if (movie[1].equals("0")) {
                stringMovie.append(String.format("%-24s", "NOT AVAILABLE"));
                System.out.println(stringMovie);
            } else {
                stringMovie.append(String.format("%-24s", "AVAILABLE"));
                System.out.println(stringMovie);
            }
        }
        ScannerFactory.closeScanner();
    }

    // CHECKS TO SEE IF THE MOIVE IS IN THE FILE
    public static Boolean isContains(String movieName) {
        getScanner();
        ScannerFactory.useDelimiter(",");
        while (readFile.hasNextLine()) {
            String name = readFile.next().replace("\"", "");
            if (movieName.equals(name)) {
                ScannerFactory.useDelimiter(" ");
                ScannerFactory.closeScanner();
                return true;
            }
            readFile.nextLine();
        }
        ScannerFactory.useDelimiter(" ");
        ScannerFactory.closeScanner();
        return false;
    }

    // Update Scanner to get instance
    public static File getScanner() {
        File LogFile = new File(FILE_NAME);
        readFile = ScannerFactory.getScanner(LogFile);
        return LogFile;

    }

    // Update PrintWrite to get instance
    public static File getPrintWriter() {
        File temp = new File(TEMP_NAME);
        writeFile = PrintWriterFactory.getPrintWriter(temp);
        return temp;
    }

    // RenameFile and close Files
    public static void renameFile(File temp, File currFile) {
        try {
            // Close any resources if necessary
            PrintWriterFactory.closePrintWriter();
            ScannerFactory.closeScanner();

            readFile.close();
            writeFile.close();

            // Delete the current file
            if (!currFile.delete()) {
                System.out.println("File did not delete");
                ErrorOperations.LogError("File did not delete");
            }

            // Rename the temporary file to the desired file name (will delete the old file
            // in the process)
            if (!temp.renameTo(new File(FILE_NAME))) {
                System.out.println("File could not be renamed");
            }

        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
        }
    }

}