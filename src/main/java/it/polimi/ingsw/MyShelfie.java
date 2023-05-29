package it.polimi.ingsw;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.view.cli.CLIConstants;
import it.polimi.ingsw.server.Server;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main class of the project.
 */
public class MyShelfie {

    /**
     * Scanner used to read the user input.
     */
    private static final Scanner input = new Scanner(System.in);

    /**
     * Main method of the project, starts the program as a server or as a client.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) throws IOException {
        String choice = "";
        showMenu();
        while (choice.isEmpty()) {
            choice = input.nextLine().strip().toLowerCase();
            switch (choice) {
                case "s" -> {
                    System.out.println("Starting as a server...");
                    Server.main(args);
                }
                case "c" -> {
                    System.out.println("Starting as a client...");
                    Client.main(args);
                }
                default -> {
                    System.out.printf("%s%sInvalid input%s, please try again: ", CLIConstants.CONSOLE_ARROW, CLIConstants.RED_BRIGHT, CLIConstants.RESET);
                    choice = "";
                }
            }
        }
    }


    /**
     * This method show the menu
     */
    public static void showMenu(){
        System.out.println(CLIConstants.CYAN_BRIGHT + "======================" + CLIConstants.RESET);
        System.out.println(CLIConstants.CYAN_BRIGHT + "||       MENU       ||" + CLIConstants.RESET);
        System.out.println(CLIConstants.CYAN_BRIGHT + "======================" + CLIConstants.RESET);
        System.out.printf("Start as:%n");
        System.out.printf("1. Server [%ss%s]\n", CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
        System.out.printf("2. Client [%sc%s]\n", CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
        System.out.print(CLIConstants.CONSOLE_ARROW + "Please enter your choice: ");
    }
}