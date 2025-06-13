package logic;

/**
 * Represents a single Song.
 *
 * @author Fernando Rios
 */
public class Song {
    private static final String DELIMITER = ":";
    private static final String ID_FORMAT = "%05d";

    private final int identifier;
    private final String artist;
    private final String title;
    private final int duration;
    private final Priority priority;
    private int remainingTime;
    private boolean playing;

    /**
     * Constructs a new Song.
     *
     * @param identifier the unique identifier of the song.
     * @param artist the artist of the song.
     * @param title the title of the song.
     * @param duration the duration of the song in seconds.
     * @param priority the priority of the song (lower values indicate higher priority).
     */
    public Song(int identifier, String artist, String title, int duration, Priority priority) {
        this.identifier = identifier;
        this.artist = artist;
        this.title = title;
        this.duration = duration;
        this.priority = priority;
        remainingTime = duration;
        playing = false;
    }

    /**
     * Constructs a new Song with the given identifier, artist, title, and duration,
     * assigning the highest priority by default.
     *
     * @param identifier the unique identifier of the song.
     * @param artist the artist of the song.
     * @param title the title of the song.
     * @param duration the duration of the song in seconds.
     */
    public Song(int identifier, String artist, String title, int duration) {
        this.identifier = identifier;
        this.artist = artist;
        this.title = title;
        this.duration = duration;
        this.priority = Priority.HIGHEST;
        remainingTime = duration;
        playing = false;
    }

    /**
     * Returns the priority of the song.
     *
     * @return the priority of the song.
     */
    protected int priorityValue() {
        return priority.getLevel();
    }

    /**
     * Returns the unique identifier of the song.
     *
     * @return the ID of the song.
     */
    protected int getIdentifier() {
        return identifier;
    }

    /**
     * Returns the remaining play time of the song.
     *
     * @return The remaining play time of the song.
     */
    public int getRemainingTime() {
        return remainingTime;
    }

    /**
     * Plays the song for a specified time.
     *
     * @param time The time the song has been played.
     */
    protected void play(int time) {
        remainingTime = Math.max(0, remainingTime - time);
        playing = true;
    }

    /**
     * Checks whether the song has finished playing.
     *
     * @return true if the remaining time is less than or equal to zero; false otherwise
     */
    protected boolean hasFinished() {
        return getRemainingTime() == 0;
    }

    /**
     * Indicates whether the song is currently playing.
     *
     * @return true if the song is playing; false otherwise.
     */
    protected boolean isPlaying() {
        return playing;
    }

    /**
     * Returns a string representation of the song with the format settled.
     *
     * @return a string representation of the song
     */
    @Override
    public String toString() {
        return String.format(ID_FORMAT, identifier) + DELIMITER + artist + DELIMITER + title + DELIMITER + duration;
    }
}
