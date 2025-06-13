package command;

/**
 * Represents the available command keys of the program.
 *
 * @author Fernando Rios
 */
public enum CommandKey {
    /** Command to add a new song to the playlist. */
    ADD,
    /** Command to remove a set of songs from the playlist based on their ID. */
    REMOVE,
    /** Command to play songs for an introduced amount of time. */
    PLAY,
    /** Command to skip the next song in the playlist. */
    SKIP,
    /** Command to peek the next song in the playlist. */
    PEEK,
    /** Command to set a next song. */
    NEXT,
    /** Command to show the list of song to play. */
    LIST,
    /** Command to show the history of played songs. */
    HISTORY,
    /** Command to terminate the program. */
    QUIT;

    /**
     * Converts a string command into its corresponding CommandKey value.
     *
     * @param commandLine The command line to be converted.
     * @return The corresponding CommandKey if match is found, otherwise null.
     */
    public static CommandKey fromLine(String commandLine) {
        for (CommandKey commandKey : CommandKey.values()) {
            if (commandKey.toString().toLowerCase().equals(commandLine)) {
                return commandKey;
            }
        }
        return null;
    }
}
