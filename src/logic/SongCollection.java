package logic;

public abstract class SongCollection {
    private static final int DEFAULT_COLLECTION_CAPACITY = 6;
    private static final int LAST_INDEX_OFFSET = 1;
    private static final int NO_SONG_STORED = 0;
    private static final int INITIAL_INDEX = 0;

    private Song[] songs;
    private int capacity;
    private int totalSongs;

    public SongCollection() {
        this.capacity = DEFAULT_COLLECTION_CAPACITY;
        songs = new Song[capacity];
        totalSongs = NO_SONG_STORED;
    }

    public abstract void addSong(Song newSong);

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

    protected void addToCollection(Song newSong, int atIndex) {
        songs[atIndex] = newSong;
        totalSongs++;
    }

    protected int amountSongs() {
        return totalSongs;
    }

    protected Song songAt(int index) {
        if (INITIAL_INDEX <= index && index < totalSongs) {
            return songs[index];
        }
        return null;
    }

    protected void setSongAt(int index, Song song) {
        this.songs[index] = song;
    }

    protected void removeSongAt(int songIndex) {
        for (int i = songIndex; i < totalSongs - LAST_INDEX_OFFSET; i++) {
            setSongAt(i, songs[i + LAST_INDEX_OFFSET]);
        }
        songs[totalSongs - LAST_INDEX_OFFSET] = null;
        totalSongs--;
    }

    protected void expandIfNecessary() {
        if (capacity == totalSongs) {
            int newCapacity = songs.length * 2;
            Song[] newSongs = new Song[newCapacity];
            System.arraycopy(songs, INITIAL_INDEX, newSongs, INITIAL_INDEX, newSongs.length);
            songs = newSongs;
            capacity = newCapacity;
        }
    }

    public boolean isNotEmpty() {
        return totalSongs > NO_SONG_STORED;
    }
}
