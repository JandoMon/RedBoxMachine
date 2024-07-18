package RedBoxInventorySystem.RedBoxFunctions.StorageOperations;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import RedBoxInventorySystem.RedBoxFunctions.Common.PrintWriterFactory;
import RedBoxInventorySystem.RedBoxFunctions.Common.ScannerFactory;

public class ActionOperations {

    public static Scanner readFile;
    public static PrintWriter writeFile;

    public final static String FILE_NAME = "RedBoxInventorySystem/RedBoxFunctions/LogTransactions/LogAction.log";
    public final static String TEMP_NAME = "RedBoxInventorySystem\\RedBoxFunctions\\LogTransactions\\temp.txt";

    // log the action the redbox
    public static boolean LogAction(String action) {
        File currFile = getScanner();
        File temp = getPrintWriter();

        while (readFile.hasNextLine()) {
            writeFile.write(readFile.nextLine() + "\n");
        }
        writeFile.write(ErrorOperations.getTime() + action + "\n");
        renameFile(temp, currFile);
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
