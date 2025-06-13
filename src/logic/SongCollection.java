package logic;

/**
 * Abstract base class that represents a collection of songs.
 *
 * @author Fernando Rios
 */
public abstract class SongCollection {
    private static final int DEFAULT_COLLECTION_CAPACITY = 6;
    private static final int LAST_INDEX_OFFSET = 1;
    private static final int NO_SONG_STORED = 0;
    private static final int INITIAL_INDEX = 0;

    private Song[] songs;
    private int capacity;
    private int totalSongs;

    /**
     * Constructs a new SongCollection with the default initial capacity.
     */
    public SongCollection() {
        this.capacity = DEFAULT_COLLECTION_CAPACITY;
        songs = new Song[capacity];
        totalSongs = NO_SONG_STORED;
    }

    /**
     * Adds a song to the collection.
     *
     * @param newSong the song to add
     */
    public abstract void addSong(Song newSong);

    /**
     * Inserts a new song at the specified index in the collection.
     *
     * @param newSong the song to insert
     * @param atIndex the position at which the song should be inserted
     */
    protected void addToCollection(Song newSong, int atIndex) {
        songs[atIndex] = newSong;
        totalSongs++;
    }

    protected int amountSongs() {
        return totalSongs;
    }

    /**
     * Returns the song at the specified index if it is within bounds.
     *
     * @param index the index of the song to retrieve
     * @return the song at the given index, or {@code null} if the index is out of bounds
     */
    protected Song songAt(int index) {
        if (INITIAL_INDEX <= index && index < totalSongs) {
            return songs[index];
        }
        return null;
    }

    /**
     * Sets the song at the specified index in the collection.
     *
     * @param index the index where the song should be set
     * @param song  the song to assign at the given index
     */
    protected void setSongAt(int index, Song song) {
        this.songs[index] = song;
    }

    /**
     * Removes the song at the specified index from the collection.
     *
     * @param songIndex the index of the song to remove
     */
    protected void removeSongAt(int songIndex) {
        for (int i = songIndex; i < totalSongs - LAST_INDEX_OFFSET; i++) {
            setSongAt(i, songs[i + LAST_INDEX_OFFSET]);
        }
        songs[totalSongs - LAST_INDEX_OFFSET] = null;
        totalSongs--;
    }

    /**
     * Expands the capacity of the song array if the collection is currently full.
     */
    protected void expandIfNecessary() {
        if (capacity == totalSongs) {
            int newCapacity = songs.length * 2;
            Song[] newSongs = new Song[newCapacity];
            System.arraycopy(songs, INITIAL_INDEX, newSongs, INITIAL_INDEX, newSongs.length);
            songs = newSongs;
            capacity = newCapacity;
        }
    }

    /**
     * Checks whether the song collection contains at least one song.
     *
     * @return true if the collection is not empty; false otherwise
     */
    public boolean isNotEmpty() {
        return totalSongs > NO_SONG_STORED;
    }

    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();
        for (int index = INITIAL_INDEX; index < totalSongs; index++) {
            content.append(songs[index]);
            if (index < totalSongs - LAST_INDEX_OFFSET) {
                content.append(System.lineSeparator());
            }
        }
        return content.toString();
    }
}
