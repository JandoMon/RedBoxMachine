package RedBoxInventorySystem.RedBoxFunctions.StorageOperations;

import java.io.File;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import RedBoxInventorySystem.RedBoxFunctions.Common.PrintWriterFactory;
import RedBoxInventorySystem.RedBoxFunctions.Common.ScannerFactory;

public class ErrorOperations extends StorageOperations {

    public final static String FILE_NAME = "RedBoxInventorySystem/RedBoxFunctions/LogTransactions/error.log";

    // log the action the redbox
    public static boolean LogError(String error) {
        File currFile = getScanner(FILE_NAME);
        File temp = getPrintWriter();

        while (readFile.hasNextLine()) {
            writeFile.write(readFile.nextLine() + "\n");
        }
        writeFile.write(getTime() + error + "\n");
        renameFile(temp, currFile, FILE_NAME);
        return false;
    }

    public static String getTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

}
