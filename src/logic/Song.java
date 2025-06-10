package logic;

public class Song {
    private int identifier;
    private String artist;
    private String title;
    private int duration;
    private int remainingTime;
    private Priority priority;
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
}
