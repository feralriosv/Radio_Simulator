package logic;

public enum Priority {
    HIGHEST(0),
    VERY_HIGH(1),
    HIGH(2),
    MEDIUM(3),
    LOW(4),
    LOWEST(5);

    private final int level;

    Priority(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public static Priority fromLevel(int level) {
        for (Priority priority : Priority.values()) {
            if (priority.level == level) {
                return priority;
            }
        }
        return null;
    }
}
