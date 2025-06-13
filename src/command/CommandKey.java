package command;

public enum CommandKey {
    ADD,
    REMOVE,
    PLAY,
    SKIP,
    PEEK,
    NEXT,
    LIST,
    HISTORY,
    QUIT;

    public static CommandKey fromLine(String commandLine) {
        for (CommandKey commandKey : CommandKey.values()) {
            if (commandKey.toString().toLowerCase().equals(commandLine)) {
                return commandKey;
            }
        }
        return null;
    }
}
