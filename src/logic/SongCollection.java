package logic;

public abstract class SongCollection {
    private Song[] songs;
    private int capacity;
    private int totalSongs;

    public SongCollection() {
        this.capacity = 6;
        songs = new Song[capacity];
        totalSongs = 0;
    }

    public abstract void addSong(Song newSong);

    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();
        for (int index = 0; index < totalSongs; index++) {
            content.append(songs[index]);
            if (index < totalSongs - 1) {
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
        if (0 <= index && index < totalSongs) {
            return songs[index];
        }
        return null;
    }

    protected void setSongAt(int index, Song song) {
        this.songs[index] = song;
    }

    protected void removeAt(int songIndex) {
        for (int i = songIndex; i < totalSongs - 1; i++) {
            setSongAt(i, songs[i + 1]);
        }
        songs[totalSongs - 1] = null;
        totalSongs--;
    }

    protected void expandIfNecessary() {
        if (capacity == totalSongs) {
            int newCapacity = songs.length * 2;
            Song[] newSongs = new Song[newCapacity];
            System.arraycopy(songs, 0, newSongs, 0, newSongs.length);
            songs = newSongs;
            capacity = newCapacity;
        }
    }

    public boolean isNotEmpty() {
        return totalSongs > 0;
    }
}
