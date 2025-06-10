package logic;

public abstract class SongCollection {
    protected Song[] songs;
    protected int capacity;
    protected int totalSongs;

    public SongCollection() {
        this.capacity = 6;
        songs = new Song[capacity];
        totalSongs = 0;
    }

    public abstract void addSong(Song newSong);

    @Override
    public String toString() {
        if (isEmpty()) return null;
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < totalSongs; index++) {
            if (index != totalSongs - 1) {
                sb.append(songs[index]).append("\n");
            } else {
                sb.append(songs[index]);
            }
        }
        return sb.toString();
    }

    public void expandIfNecessary() {
        if (capacity == totalSongs) {
            int newCapacity = songs.length * 2;
            Song[] newSongs = new Song[newCapacity];
            System.arraycopy(songs, 0, newSongs, 0, newSongs.length);
            songs = newSongs;
            capacity = newCapacity;
        }
    }

    public boolean isEmpty() {
        return totalSongs == 0;
    }
}
