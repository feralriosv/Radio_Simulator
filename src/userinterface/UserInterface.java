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

            String output = commandHandler.getCommandResult();
            if (output != null) {
                printMessage(output);
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    private void printMessage(String message) {
        System.out.println(message);
    }
}
