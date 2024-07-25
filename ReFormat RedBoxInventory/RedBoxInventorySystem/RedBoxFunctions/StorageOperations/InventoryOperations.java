package RedBoxInventorySystem.RedBoxFunctions.StorageOperations;

import RedBoxInventorySystem.RedBoxFunctions.Common.*;
import RedBoxInventorySystem.RedBoxFunctions.Movie.*;

import java.io.File;

public class InventoryOperations extends StorageOperations {

    public final static String FILE_NAME = "RedBoxInventorySystem\\RedBoxFunctions\\LogTransactions\\LogInventory.txt";

    // ADD MOVIE: Will add the new movie to the end of the file
    public static void AddMovie(Movie movie) {
        File currFile = getScanner(FILE_NAME);
        File temp = getPrintWriter();
        while (readFile.hasNextLine()) {

            writeFile.write(readFile.nextLine() + "\n");
        }
        writeFile.write("\"" + movie.getTitle() + "\"," + movie.getAvailable() + "," + movie.getRented() + "\n");
        renameFile(temp, currFile, FILE_NAME);
    }

    // UPDATE THE MOVIE: Will find the movie in the file and add it in that spot
    public static void LogMovie(Movie updateMovie) {
        Boolean isUpdate = false;
        File currFile = getScanner(FILE_NAME);
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
        renameFile(temp, currFile, FILE_NAME);
    }

    public static void removeMovie(Movie updateMovie) {
        File currFile = getScanner(FILE_NAME);
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
        renameFile(temp, currFile, FILE_NAME);
    }

    public static void printLog() {

        getScanner(FILE_NAME);
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
        getScanner(FILE_NAME);
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
}