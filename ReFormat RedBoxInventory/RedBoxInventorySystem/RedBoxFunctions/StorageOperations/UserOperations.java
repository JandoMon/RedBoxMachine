package RedBoxInventorySystem.RedBoxFunctions.StorageOperations;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import RedBoxInventorySystem.RedBoxFunctions.Common.*;

public class UserOperations {

    public static Scanner readFile;
    public static PrintWriter writeFile;

    public final static String FILE_NAME = "RedBoxInventorySystem/RedBoxFunctions/LogTransactions/ValidUsers.txt";
    public final static String TEMP_NAME = "RedBoxInventorySystem\\RedBoxFunctions\\LogTransactions\\temp.txt";

    public static Boolean isValid(String userName, String password) {
        getScanner();
        while (readFile.hasNextLine()) {

            String[] userObj = readFile.nextLine().split(",");
            if (userObj[0].equals(userName) && userObj[1].equals(password)) {
                ScannerFactory.closeScanner();
                readFile.close();
                return true;
            }

        }
        ScannerFactory.closeScanner();
        readFile.close();
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
}
