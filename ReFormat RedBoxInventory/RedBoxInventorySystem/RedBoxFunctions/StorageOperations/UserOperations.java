package RedBoxInventorySystem.RedBoxFunctions.StorageOperations;

public class UserOperations extends StorageOperations {

    public final static String FILE_NAME = "RedBoxInventorySystem/RedBoxFunctions/LogTransactions/ValidUsers.txt";
    public final static String TEMP_NAME = "RedBoxInventorySystem\\RedBoxFunctions\\LogTransactions\\temp.txt";

    public static Boolean isValid(String userName, String password) {
        getScanner(FILE_NAME);
        while (readFile.hasNextLine()) {

            String[] userObj = readFile.nextLine().split(",");
            if (userObj[0].equals(userName) && userObj[1].equals(password)) {
                closeScanner();
                return true;
            }

        }
        closeScanner();
        return false;

    }

}
