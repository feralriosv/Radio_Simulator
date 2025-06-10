package userinterface;

import command.CommandHandler;

import java.util.Scanner;

public class UserInterface {
    private final CommandHandler commandHandler;
    private boolean running;
    private final Scanner scanner;

    public UserInterface() {
        commandHandler = new CommandHandler(this);
        scanner = new Scanner(System.in);
        running = true;
    }

    public void startReading() {
        while (running) {
            String line = scanner.nextLine();
            commandHandler.processCommand(line);
        }
    }
}
