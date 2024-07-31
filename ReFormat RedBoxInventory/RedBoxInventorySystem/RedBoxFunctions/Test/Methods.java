package RedBoxInventorySystem.RedBoxFunctions.Test;

import RedBoxInventorySystem.RedBoxFunctions.Movie.Transactions;

public class Methods {

    public Methods() {
        Transactions.getCatalog();
    }

    public boolean testFunctions() {
        boolean test = false;
        if (testAdd() && testMoreCopies() && testLessCopies() &&
                testRent() && testReturn() && testSetQuantity() &&
                testRemove()) {
            test = true;
        }
        if (test) {
            System.out.println("Everything is working!");
        }
        return test;
    }

    private boolean testRent() {
        boolean rent = Transactions.Rent("Test Movie");
        String result = ((rent) ? ("Rent is Working") : ("Rent is not Working"));
        System.out.println(result);
        return rent;

    }

    private boolean testReturn() {
        boolean returnMov = Transactions.Return("Test Movie");
        String result = ((returnMov) ? ("Return is Working") : ("Return is not Working"));
        System.out.println(result);
        return returnMov;
    }

    private boolean testAdd() {
        boolean add = Transactions.AddNewTitle("Test Movie", 1);
        String result = ((add) ? ("Add is Working") : ("Add is not Working"));
        System.out.println(result);
        return add;
    }

    private boolean testRemove() {
        boolean remove = Transactions.RemoveTitle("Test Movie");
        String result = ((remove) ? ("Remove is Working") : ("Remove is not Working"));
        System.out.println(result);
        return remove;
    }

    private boolean testMoreCopies() {
        boolean moreCopies = Transactions.AddCopies("Test Movie", 1);
        String result = ((moreCopies) ? ("AddCopies is Working")
                : ("AddCopies is not Working"));
        System.out.println(result);
        return moreCopies;
    }

    private boolean testLessCopies() {
        boolean lessCopies = Transactions.RemoveCopies("Test Movie", 1);
        String result = ((lessCopies) ? ("RemoveCopies is Working")
                : ("RemoveCopies is not Wokring"));
        System.out.println(result);
        return lessCopies;
    }

    private boolean testSetQuantity() {
        boolean setQuanity = Transactions.setQuantity("Test Movie", 10);
        String result = ((setQuanity) ? ("setQuantity is Working")
                : ("setQuantity is not Working"));
        System.out.println(result);
        return setQuanity;
    }

}