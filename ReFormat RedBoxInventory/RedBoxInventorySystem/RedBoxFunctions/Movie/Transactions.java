package RedBoxInventorySystem.RedBoxFunctions.Movie;

import RedBoxInventorySystem.RedBoxFunctions.Common.BinarySearchTree;
import RedBoxInventorySystem.RedBoxFunctions.StorageOperations.ActionOperations;
import RedBoxInventorySystem.RedBoxFunctions.StorageOperations.ErrorOperations;
import RedBoxInventorySystem.RedBoxFunctions.StorageOperations.InventoryOperations;

public class Transactions {

    public static BinarySearchTree<Movie> tree = new BinarySearchTree<>();

    // _________________________________
    // Consumer Transactions
    // _________________________________
    public static boolean Rent(String titleName) {
        Movie rentMovie = tree.findNode(new Movie(titleName));
        if (rentMovie != null) {
            if (!rentMovie.Rent()) {
                System.out.println("RentMovie Failed: Not enough copies");
                ErrorOperations
                        .LogError(" RentMovie Failed: " + titleName + " does not have enough copies");
                return false;
            }
            InventoryOperations.LogMovie(rentMovie);
            ActionOperations.LogAction((" Rent: " + titleName + " " + rentMovie.getAvailable()));
            return true;
        }
        System.out.println("Please Enter the Movie Name Correctly!(It is case-sensitive!)");
        ErrorOperations.LogError(" RentMovie Failed: " + titleName + " does not exist");
        return false;

    }

    public static boolean Return(String titleName) {
        Movie rentMovie = tree.findNode(new Movie(titleName));
        if (rentMovie != null) {
            rentMovie.Return();
            InventoryOperations.LogMovie(rentMovie);
            ActionOperations.LogAction((" Return: " + titleName + " " + rentMovie.getAvailable()));
            return true;
        }
        System.out.println("Please Enter the Movie Name Correctly!(It is case-sensitive!)");
        ErrorOperations.LogError(" ReturnMovie Failed: " + titleName + " does not exist");
        return false;
    }

    // _____________________________________
    // Admin Transactions
    // _____________________________________

    // add the title to the tree
    public static boolean AddNewTitle(String titleName, int quantity) {
        Movie newMovie = new Movie(titleName, quantity);
        if (InventoryOperations.isContains(titleName)) {
            System.out.println(titleName + " already exist");
            ErrorOperations.LogError(" " + titleName + " already exist");
            return false;
        }
        tree.insertNode(newMovie);
        ActionOperations.LogAction(" Add: " + titleName + " " + quantity);
        InventoryOperations.AddMovie(newMovie);
        return true;
    }

    // remove the title from the tree
    public static boolean RemoveTitle(String titleName) {
        Movie removeMovie = new Movie(titleName);
        boolean success = tree.remove(removeMovie);
        if (!success) {
            System.out.println(" RemoveTitle: " + titleName + " does not exist");
            ErrorOperations.LogError(" RemoveTitle: " + titleName + " does not exist");
        }
        ActionOperations.LogAction(" Remove:" + titleName);
        InventoryOperations.removeMovie(removeMovie);
        return success;
    }

    // add aditional copies to pre-existing amount
    public static boolean AddCopies(String titleName, int add) {
        Movie addCopies = tree.findNode(new Movie(titleName));
        if (addCopies != null) {
            addCopies.adjustCopies(add);
            ActionOperations
                    .LogAction(" AddCopies: " + titleName + " " + addCopies.getQuantity() + " " + add);
            InventoryOperations.LogMovie(addCopies);
            return true;
        }
        System.out.println(" AddCopies Failed: " + titleName + " does not exist");
        ErrorOperations.LogError(" AddCopies Failed: " + titleName + " does not exist");
        return false;
    }

    // add aditional copies to pre-exiting amount
    public static boolean RemoveCopies(String titleName, int remove) {
        Movie removeCopies = tree.findNode(new Movie(titleName));
        if (removeCopies != null) {
            if (!removeCopies.adjustCopies(-remove)) {
                System.out.println(" RemoveCopies Warning: " + titleName
                        + " does not have enough copies to remove. Set Quantity to ZERO!");
                ErrorOperations.LogError(" RemoveCopies Warning: " + titleName
                        + " does not have enough copies to remove. Set Quantity to ZERO!");

            }
            ActionOperations.LogAction(
                    " RemoveCopies: " + titleName + " " + removeCopies.getQuantity() + " " + remove);
            InventoryOperations.LogMovie(removeCopies);
            return true;
        }
        System.out.println(" RemoveCopies Failed: " + titleName + " does not exist");
        ErrorOperations.LogError(" RemoveCopies Failed: " + titleName + " does not exist");
        return false;
    }

    // set the quantity for a specific movie
    public static boolean setQuantity(String titleName, int quantity) {
        Movie setMovie = tree.findNode(new Movie(titleName));
        if (setMovie != null) {
            setMovie.setQuanity(quantity);
            ActionOperations.LogAction(" setQuantity: " + titleName + " " + setMovie.getQuantity());
            InventoryOperations.LogMovie(setMovie);
            return true;
        }
        System.out.println(" setQuantity Failed: " + titleName + " does not exist");
        ErrorOperations.LogError(" setQuantity Failed: " + titleName + " does not exist");
        return false;
    }

    // print the tree
    public static void printTree() {
        tree.printTree();
    }

    public static void printCatalog() {
        String input = "Movie List";
        System.out.println(String.format("%" + 20 + "s", input));
        System.out.println("---------------------------------");
        InventoryOperations.printLog();

    }

    // Get the Inventory on the tree
    public static void getCatalog() {
        InventoryOperations.getScanner();
        while (InventoryOperations.readFile.hasNextLine()) {
            String[] movieParts = InventoryOperations.readFile.nextLine().split(",");
            for (int i = 0; i < movieParts.length; i++) {
                movieParts[i] = movieParts[i].trim();
            }
            Movie currMovie = new Movie(movieParts[0].replace("\"", "").trim(), Integer.parseInt(movieParts[1]));
            currMovie.setRented(Integer.parseInt(movieParts[2]));
            tree.insertNode(currMovie);
        }
        InventoryOperations.readFile.close();

    }
}
