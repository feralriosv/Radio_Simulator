package logic;

public class Playlist {
    private Song[] songs;
    private int capacity;
    private int totalSongs;

    public Playlist() {
        this.capacity = 6;
        songs = new Song[capacity];
    }

    public void add(Song song) {
        if (capacity == totalSongs) {
            expandCapacity();
        }
        int index = totalSongs;
        while (index > 0 && song.priorityValue() < songs[index - 1].priorityValue()) {
            songs[index] = songs[index - 1];
            index--;
        }
        songs[index] = song;
        totalSongs++;
    }

    public int deleteByID(int identifier) {
        int originalTotal = totalSongs;
        int notRemoved = 0;
        for (int index = 0; index < originalTotal; index++) {
            if (songs[index].getIdentifier() != identifier) {
                songs[notRemoved++] = songs[index];
            }
        }

        for (int index = notRemoved; index < originalTotal; index++) {
            songs[index] = null;
        }
        totalSongs = notRemoved;
        return originalTotal - notRemoved;
    }

    private void expandCapacity() {
        int newCapacity = songs.length * 2;
        Song[] newSongs = new Song[newCapacity];
        System.arraycopy(songs, 0, newSongs, 0, newSongs.length);
        songs = newSongs;
        capacity = newCapacity;
    }
}
