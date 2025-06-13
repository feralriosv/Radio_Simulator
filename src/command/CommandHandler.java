package command;

import controller.Moderator;
import logic.Song;
import userinterface.UserInterface;

public class CommandHandler {
    private UserInterface userInterface;
    private Moderator moderator;
    private String commandResult = null;

    public CommandHandler(UserInterface userInterface) {
        this.userInterface = userInterface;
        moderator = new Moderator();
    }
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

    private CommandKey extractCommandKey(String line) {
        String commandPart = line.split(" ")[0];
        return CommandKey.fromLine(commandPart);
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

    }

    private void processPeek() {
        commandResult = moderator.peekHeadSong();
    }

    private void processList() {
        commandResult = moderator.listPlaylist();
    }

    private void processHistory() {

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

    private String extractArgument(String line) {
        return line.split(" ")[1];
    }
}
