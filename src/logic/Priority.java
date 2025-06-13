package logic;

/**
 * Represents the different priority levels assigned to songs in the playlist.
 *
 * @author Fernando Rios
 */
public enum Priority {
    /** Highest priority (0). */
    HIGHEST(0),
    /** Very high priority (1). */
    VERY_HIGH(1),
    /** High priority (2). */
    HIGH(2),
    /** MEDIUM priority (4). */
    MEDIUM(3),
    /** LOW priority (4). */
    LOW(4),
    /** Lowest priority (5). */
    LOWEST(5);

    private final int level;


    Priority(int level) {
        this.level = level;
    }

    /**
     * Gets the numeric value of the priority level.
     *
     * @return the numeric value representing the priority.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the priority corresponding to the given numeric value.
     *
     * @param level the numeric value representing the priority.
     * @return the corresponding priority, or {@code null} if the level is invalid.
     */
    public static Priority fromLevel(int level) {
        for (Priority priority : Priority.values()) {
            if (priority.level == level) {
                return priority;
            }
        }
        return null;
    }
}
