package RedBoxInventorySystem.RedBoxFunctions.StorageOperations;

import java.io.File;

public class ActionOperations extends StorageOperations {

    public final static String FILE_NAME = "RedBoxInventorySystem/RedBoxFunctions/LogTransactions/LogAction.log";

    // log the action the redbox
    public static boolean LogAction(String action) {
        File currFile = getScanner(FILE_NAME);
        File temp = getPrintWriter();

        while (readFile.hasNextLine()) {
            writeFile.write(readFile.nextLine() + "\n");
        }
        writeFile.write(ErrorOperations.getTime() + action + "\n");
        renameFile(temp, currFile, FILE_NAME);
        return false;
    }

}
