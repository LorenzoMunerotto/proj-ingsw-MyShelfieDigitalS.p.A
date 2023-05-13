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
        System.out.printf(CLIConstants.CONSOLE_ARROW + "Choose if you want to start this as a server or as a client [%ss%s/%sc%s]: ", CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
        while (choice.isEmpty()) {
            choice = input.nextLine().strip().toLowerCase();
            if (choice.equals("s")) {
                System.out.println("Starting as a server...");
                Server.main(args);
            } else if (choice.equals("c")) {
                System.out.println("Starting as a client...");
                Client.main(args);
            } else {
                System.out.printf("%sInvalid input%s, select between server and client [%ss%s/%sc%s]: ", CLIConstants.RED_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
                choice = "";
            }
        }
    }
}