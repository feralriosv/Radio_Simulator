package command;

import controller.Moderator;
import logic.Song;
import userinterface.UserInterface;

/**
 * Handles the user commands and processes the inputs.
 *
 * @author Fernando Rios
 */
public class CommandHandler {
    private static final String SEPARATOR = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int ARGUMENT_INDEX = 1;

    private final UserInterface userInterface;
    private final Moderator moderator;
    private String commandResult = null;

    /**
     * Constructs a CommandHandler with a reference to the UserInterface.
     * @param userInterface The UserInterface reference that will retrieve the inputs.
     */
    public CommandHandler(UserInterface userInterface) {
        this.userInterface = userInterface;
        moderator = new Moderator();
    }

    /**
     * Processes the given command and executes the corresponding action.
     * @param line The command line input introduced by the user.
     */
    public void processCommand(String line) {
        CommandKey command = extractCommandKey(line);
        switch (command) {
            case ADD -> processAdd(line);
            case REMOVE -> processRemove(line);
            case PLAY -> processPlay(line);
            case NEXT -> processNext(line);
            case SKIP -> processSkip();
            case PEEK -> processPeek();
            case LIST -> processList();
            case HISTORY -> processHistory();
            case QUIT -> processQuit();
            default -> {
                // No command
            }
        }
    }

    /**
     * Extracts the command key from a given input line.
     *
     * @param line the full input line containing a command and its parameters
     * @return the {@code CommandKey} corresponding to the command part of the line
     */
    private CommandKey extractCommandKey(String line) {
        String commandPart = line.split(SEPARATOR)[COMMAND_INDEX];
        return CommandKey.fromLine(commandPart);
    }

    /**
     * Extracts the argument part from the given input line.
     *
     * @param line the input line containing the command and its argument
     * @return the string representing the extracted argument
     */
    private String extractArgument(String line) {
        return line.split(SEPARATOR)[ARGUMENT_INDEX];
    }

    private void processAdd(String line) {
        String songArgument = extractArgument(line);
        Song newSong = moderator.parseSong(songArgument);
        moderator.addToPlaylist(newSong);
    }

    private void processRemove(String line) {
        int identifier = Integer.parseInt(extractArgument(line));
        commandResult = moderator.removeFromPlaylist(identifier);
    }

    private void processPlay(String line) {
        int seconds = Integer.parseInt(extractArgument(line));
        moderator.reproducePlaylist(seconds);
    }

    private void processNext(String line) {
        String argument = extractArgument(line);
        Song nextSong = moderator.parseSong(argument);
        moderator.insertNextSong(nextSong);
    }

    private void processSkip() {
        moderator.skipNextSong();
    }

    private void processPeek() {
        commandResult = moderator.peekHeadSong();
    }

    private void processList() {
        commandResult = moderator.listPlaylist();
    }

    private void processHistory() {
        commandResult = moderator.listPlaylistHistory();
    }

    private void processQuit() {
        userInterface.setRunning(false);
    }

    public String getCommandResult() {
        return commandResult;
    }

    public void setCommandResult(String commandResult) {
        this.commandResult = commandResult;
    }
}
