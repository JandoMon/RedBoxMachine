package RedBoxInventorySystem.RedBoxFunctions.Common;

import java.io.File;
import java.util.Scanner;

public class ScannerFactory {

    public static Scanner scanner;

    // Opens the Scanner
    public static Scanner getScanner(File currFile) {

        try {
            scanner = new Scanner(currFile);
            return scanner;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        return null;
    }

    public static void useDelimiter(String delim) {
        scanner.useDelimiter(delim);
    }

    // Closes the Scanner
    public static void closeScanner() {
        scanner.close();
    }

}
