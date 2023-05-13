package it.polimi.ingsw.chat;

import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.cli.CLIConstants;

import java.util.Scanner;

public class Chat {

    private static final Scanner input = new Scanner(System.in);
    private final String username;
    private final View view;

    public Chat(String username, View view) {
        this.username = username;
        this.view = view;
    }

    public static void main(String[] args) {
        System.out.println("Chat started...");
        System.out.println("Type 'exit' to close the chat terminal.");
        String newInput;
        while (true) {
            System.out.print(CLIConstants.CONSOLE_ARROW);
            newInput = input.nextLine().strip().toLowerCase();

            if (newInput.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("Message sent: " + newInput);
        }

        System.out.println("Chat terminal closed.");
        input.close();
    }
}
