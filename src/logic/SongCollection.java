package logic;

public abstract class SongCollection {
    private Song[] songs;
    private int capacity;
    protected int totalSongs;

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

    public boolean isEmpty() {
        return totalSongs == 0;
    }

    public void addToCollection(Song newSong) {
        expandIfNecessary();
        songs[totalSongs] = newSong;
        totalSongs++;
    }

    public int size() {
        return totalSongs;
    }

    public Song songAt(int index) {
        if (0 <= index && index <= totalSongs) {
            return songs[index];
        }
        return null;
    }

    public void setSongAt(int index, Song song) {
        this.songs[index] = song;
    }

    public void removeAt(int songIndex) {
        for (int i = songIndex; i < totalSongs; i++) {
            setSongAt(i, songs[i + 1]);
        }
        totalSongs--;
        songs[totalSongs - 1] = null;
    }

    private void expandIfNecessary() {
        if (capacity == totalSongs) {
            int newCapacity = songs.length * 2;
            Song[] newSongs = new Song[newCapacity];
            System.arraycopy(songs, 0, newSongs, 0, newSongs.length);
            songs = newSongs;
            capacity = newCapacity;
        }
    }
}
