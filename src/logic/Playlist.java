package logic;

public class Playlist {
    private Song[] songs;
    private int capacity;

    public Playlist() {
        this.capacity = 6;
        songs = new Song[capacity];
    }
}
