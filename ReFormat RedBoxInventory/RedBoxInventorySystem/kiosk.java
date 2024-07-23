package RedBoxInventorySystem;

import java.util.Scanner;

import RedBoxInventorySystem.RedBoxFunctions.Movie.Transactions;
import RedBoxInventorySystem.RedBoxFunctions.StorageOperations.StorageOperations;
import RedBoxInventorySystem.RedBoxFunctions.StorageOperations.UserOperations;

public class kiosk {

    public static void kioskActive() {
        try {
            System.out.println("NOTE: If you are an ADMIN enter SIGN IN!");
            Boolean Admin = false;
            Scanner scan = new Scanner(System.in);
            String command = "";
            Transactions.getCatalog();

            do {
                System.out.println();
                Transactions.printCatalog();
                if (Admin) {
                    getDialog(Admin);
                    command = scan.nextLine().trim();
                    if (command.toUpperCase().equals("SIGN OUT")) {
                        Admin = false;
                        System.out.println("Until next time!");

                    } else if (!(command.equals(""))) {
                        readCommand(command, Admin);
                    } else {
                        System.out.println("Enter Valid Command");
                    }
                } else {
                    getDialog(Admin);
                    command = scan.nextLine().trim();
                    if (command.toUpperCase().equals("SIGN IN")) {
                        System.out.print("Please Enter UserName: ");
                        String username = scan.nextLine();
                        System.out.print("Please Enter Password: ");
                        String password = scan.nextLine();
                        if (UserOperations.isValid(username, password)) {
                            System.out.println("Welcome Back Admin :)");
                            Admin = true;
                        } else {
                            System.out.println("Wrong Username or Password! >w<");
                        }
                    } else {
                        if (!(command.equals(""))) {
                            readCommand(command, Admin);
                        } else {
                            System.out.println("Enter Valid Choice");
                        }
                    }

                }

            } while (!command.equals("escape"));
            scan.close();
        } catch (Exception e) {
            System.out.println("Kiosk is not working");
            System.out.println(e);
        }
    }

    public static void readCommand(String line, boolean Admin) {
        String[] commandParts = breakUpCommand(line);
        String command = commandParts[0].toUpperCase();
        if (!(command.equals("RENT") || command.equals("RETURN")) && !Admin) {
            System.out.println("Please Enter A Valid Commmand!");
        } else {
            if (commandParts.length > 3 && commandParts[2].split(" ").length > 1) {
                inccorrectParamVal(commandParts);
            } else {
                switch (command) {
                    case "ADD":
                        try {
                            if (commandParts.length != 3) {
                                incorrectParam(commandParts);
                            } else if (Integer.parseInt(commandParts[2]) > 0) {
                                Transactions.AddNewTitle(commandParts[1], Integer.parseInt(commandParts[2]));
                            } else {
                                incorrectValPos(commandParts);
                            }
                        } catch (java.lang.NumberFormatException e) {
                            inccorrectParamVal(commandParts);
                        }
                        break;

                    case "REMOVE":
                        if (commandParts.length != 2) {
                            incorrectParam(commandParts);
                        } else {
                            Transactions.RemoveTitle(commandParts[1]);
                        }
                        break;
                    case "MORE COPIES":
                        try {
                            if (commandParts.length != 3) {
                                incorrectParam(commandParts);
                            } else if (Integer.parseInt(commandParts[2]) > 0) {
                                Transactions.AddCopies(commandParts[1], Integer.parseInt(commandParts[2]));
                            } else {
                                incorrectValPos(commandParts);
                            }

                        } catch (java.lang.NumberFormatException e) {
                            inccorrectParamVal(commandParts);
                        }
                        break;
                    case "LESS COPIES":
                        try {
                            if (commandParts.length != 3) {
                                incorrectParam(commandParts);
                            } else if (Integer.parseInt(commandParts[2]) > 0) {
                                Transactions.RemoveCopies(commandParts[1], Integer.parseInt(commandParts[2]));
                            } else {
                                incorrectValPos(commandParts);
                            }
                        } catch (java.lang.NumberFormatException e) {
                            inccorrectParamVal(commandParts);
                        }
                        break;
                    case "SET QUANTITY":
                        try {
                            if (commandParts.length != 3) {
                                incorrectParam(commandParts);
                            } else if (Integer.parseInt(commandParts[3]) >= 0) {
                                Transactions.setQuantity(commandParts[1], Integer.parseInt(commandParts[2]));
                            } else {
                                incorrectValPos(commandParts);
                            }
                        } catch (java.lang.NumberFormatException e) {
                            inccorrectParamVal(commandParts);
                        }
                        break;
                    case "RENT":
                        try {
                            if (commandParts.length != 2) {
                                incorrectParam(commandParts);
                            } else {
                                if (Transactions.Rent(commandParts[1])) {
                                    System.out.println("Movie was Rented! Thank you!");
                                }
                            }
                        } catch (java.lang.NumberFormatException e) {
                            inccorrectParamVal(commandParts);
                        }
                        break;
                    case "RETURN":
                        try {
                            if (commandParts.length != 2) {
                                incorrectParam(commandParts);
                            } else {
                                if (Transactions.Return(commandParts[1])) {
                                    System.out.println("Movie was Returned! Thank you!");
                                }
                            }
                        } catch (java.lang.NumberFormatException e) {
                            inccorrectParamVal(commandParts);
                        }
                        break;
                    default:
                        System.out.println("Please Enter A Valid Commmand!");
                        break;
                }
            }
        }
    }

    public static String[] breakUpCommand(String line) {
        String commandParts[] = line.trim().split("\"");
        for (int i = 0; i < commandParts.length; i++) {
            commandParts[i] = commandParts[i].trim();
        }
        return commandParts;

    }

    public static void incorrectParam(String[] commandParts) {
        StorageOperations
                .LogError(" Invalid Transaction: Incorrect Parameters -->"
                        + getCommandError(commandParts));
        System.out.println("Please Enter Correct Transaction Parameters");
    }

    public static void inccorrectParamVal(String[] commandParts) {
        StorageOperations.LogError(
                " Invalid Transaction: Incorrect Parameter Values -->" + getCommandError(commandParts));
        System.out.println("Please Enter Correct Transaction Parameters Values");
    }

    public static void incorrectValPos(String[] commandParts) {
        StorageOperations
                .LogError(" Invalid Transaction: Incorrect Value -->"
                        + getCommandError(commandParts));
        System.out.println("Please Enter Positive Integer");
    }

    public static String getCommandError(String[] commandParts) {
        String command = "";
        for (int i = 0; i < commandParts.length; i++) {
            command += (" " + commandParts[i]);
        }
        return command;
    }

    public static void getDialog(Boolean Admin) {
        if (Admin) {
            System.out.println("\nHello Admin!");
        } else {
            System.out.println("\nHello Valued Customer!");
            System.out.println("Please Follow the Format!");
            System.out.println("Format: Return/Rent \"movieTitle\"");
        }
        System.out.print("Enter you Choice: ");
    }
}
