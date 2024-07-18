package RedBoxInventorySystem.RedBoxFunctions.Common;

import java.io.File;
import java.io.PrintWriter;

public class PrintWriterFactory {

    public static PrintWriter writeFile;

    // opens a printwriter
    public static PrintWriter getPrintWriter(File file) {

        try {
            writeFile = new PrintWriter(file);
            return writeFile;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        return null;
    }

    // close the printwriter
    public static void closePrintWriter() {
        writeFile.close();
    }
}
