package RedBoxInventorySystem.RedBoxFunctions.Movie;

//Creating Movie Object
public class Movie implements Comparable<Movie> {

    // local variables
    private String title;
    private int quantity;
    private int rented;

    // Constructor
    public Movie(String setTitle, int setQuantity) {
        title = setTitle;
        quantity = setQuantity;
    }

    public Movie(String setTitle) {
        title = setTitle;
    }

    public Movie() {
        title = "";
        quantity = 0;
        rented = 0;

    }

    // Setter Methods
    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public void setRented(int newRented) {
        rented = newRented;
    }

    public void setQuanity(int newQuantity) {
        quantity = newQuantity;
    }

    // Getter Methods
    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getRented() {
        return rented;
    }

    // Functions for Movies Object
    public int getAvailable() {
        return quantity - rented;
    }

    public boolean adjustCopies(int numOfCopiesAdj) {

        if ((quantity += numOfCopiesAdj) < 0) {
            quantity = 0;
            return false;
        }
        return true;
    }

    public boolean Rent() {
        if (getAvailable() > 0) {
            rented++;
            return true;
        }
        return false;
    }

    public void Return() {
        if (rented > 0) {
            rented--;
        }
    }

    @Override
    public int compareTo(Movie MovieObject) {
        /*
         * if(string1 > string2 val++)
         * if(string1 < string2 val--)
         */
        return title.compareTo(MovieObject.title);

    }

    @Override
    public String toString() {
        return title;
    }

}
