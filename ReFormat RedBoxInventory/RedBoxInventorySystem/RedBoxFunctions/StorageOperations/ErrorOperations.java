package RedBoxInventorySystem.RedBoxFunctions.StorageOperations;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import RedBoxInventorySystem.RedBoxFunctions.Common.PrintWriterFactory;
import RedBoxInventorySystem.RedBoxFunctions.Common.ScannerFactory;

public class ErrorOperations {

    public static Scanner readFile;
    public static PrintWriter writeFile;

    public final static String FILE_NAME = "RedBoxInventorySystem/RedBoxFunctions/LogTransactions/error.log";
    public final static String TEMP_NAME = "RedBoxInventorySystem\\RedBoxFunctions\\LogTransactions\\temp.txt";

    // log the action the redbox
    public static boolean LogError(String error) {
        File currFile = getScanner();
        File temp = getPrintWriter();

        while (readFile.hasNextLine()) {
            writeFile.write(readFile.nextLine() + "\n");
        }
        writeFile.write(getTime() + error + "\n");
        renameFile(temp, currFile);
        return false;
    }

    public static String getTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
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
