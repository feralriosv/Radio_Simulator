package command;

public enum CommandKey {
    ADD_COMMAND,
    REMOVE_COMMAND,
    PLAY_COMMAND,
    SKIP_COMMAND,
    PEEK_COMMAND,
    NEXT_COMMAND,
    LIST_COMMAND,
    HISTORY_COMMAND,
    QUIT_COMMAND;

    public static CommandKey fromLine(String commandLine) {
        for (CommandKey commandKey : CommandKey.values()) {
            if (commandKey.toString().toLowerCase().equals(commandLine)) {
                return commandKey;
            }
        }
        return null;
    }
}
