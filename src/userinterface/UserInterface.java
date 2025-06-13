package userinterface;

import command.CommandHandler;

import java.util.Scanner;

/**
 * Handles the user interaction and command processing for the program.
 *
 * @author Fernando Rios
 */
public class UserInterface {
    private final CommandHandler commandHandler;
    private boolean running;
    private final Scanner scanner;

    /**
     * Constructs a UserInterface.
     *
     */
    public UserInterface() {
        commandHandler = new CommandHandler(this);
        scanner = new Scanner(System.in);
        running = true;
    }

    /**
     * Starts the program and reads the user inputs in a loop.
     */
    public void startReading() {
        while (running) {
            String line = scanner.nextLine();
            commandHandler.processCommand(line);

            String output = commandHandler.getCommandResult();
            if (output != null) {
                printMessage(output);
            }

            commandHandler.setCommandResult(null);
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    private void printMessage(String message) {
        System.out.println(message);
    }
}
