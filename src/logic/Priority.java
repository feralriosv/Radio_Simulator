package logic;

public enum Priority {
    HIGHEST(0),
    VERY_HIGH(1),
    HIGH(2),
    MEDIUM_HIGH(3),
    MEDIUM_LOW(4),
    LOW(5),
    LOWEST(6);

    private final int level;

    Priority(int level) {
        this.level = level;
    }
}
