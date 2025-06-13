package logic;

public class Song {
    private final int identifier;
    private final String artist;
    private final String title;
    private final int duration;
    private final Priority priority;
    private int remainingTime;
    private boolean playing;

    public Song(int identifier, String artist, String title, int duration, Priority priority) {
        this.identifier = identifier;
        this.artist = artist;
        this.title = title;
        this.duration = duration;
        this.priority = priority;
        remainingTime = duration;
        playing = false;
    }

    public Song(int identifier, String artist, String title, int duration) {
        this.identifier = identifier;
        this.artist = artist;
        this.title = title;
        this.duration = duration;
        this.priority = Priority.HIGHEST;
        remainingTime = duration;
        playing = false;
    }

    protected int priorityValue() {
        return priority.getLevel();
    }

    protected int getIdentifier() {
        return identifier;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    protected void play(int time) {
        remainingTime -= time;
        playing = true;
    }

    @Override
    public String toString() {
        return String.format("%05d", identifier) + ":" + artist + ":" + title + ":" + duration;
    }
}
