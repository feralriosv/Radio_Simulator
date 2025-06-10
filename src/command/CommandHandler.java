package command;

import controller.Moderator;
import logic.Song;
import userinterface.UserInterface;

public class CommandHandler {
    private UserInterface userInterface;
    private Moderator moderator;

    public CommandHandler(UserInterface userInterface) {
        this.userInterface = userInterface;
        moderator = new Moderator();
    }
    public void processCommand(String line) {
        CommandKey command = extractCommandKey(line);
        switch (command) {
            case ADD_COMMAND -> processAdd(line);
            case REMOVE_COMMAND -> processRemove(line);
            case PLAY_COMMAND -> processPlay(line);
            case NEXT_COMMAND -> processNext(line);
            case SKIP_COMMAND -> processSkip();
            case PEEK_COMMAND -> processPeek();
            case LIST_COMMAND -> processList();
            case HISTORY_COMMAND -> processHistory();
            case QUIT_COMMAND -> processQuit();
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
        String songArgument = extractSongArgument(line);
        Song newSong = moderator.parseSong(songArgument);
        moderator.addToPlaylist(newSong);
    }

    private void processRemove(String line) {
        String songArgument = extractSongArgument(line);

    }

    private void processPlay(String line) {
        String argument = extractSongArgument(line);

    }

    private void processNext(String line) {
        String argument = extractSongArgument(line);

    }

    private void processSkip() {

    }
    private void processPeek() {

    }

    private void processList() {

    }

    private void processHistory() {

    }

    private void processQuit() {

    }

    private String extractSongArgument(String line) {
        return line.split(" ")[1];
    }
}
