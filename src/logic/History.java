package logic;

public class History extends SongCollection {

    public History() {
    }

    @Override
    public void addSong(Song song) {
        expandIfNecessary();
        addToCollection(song, amountSongs());
    }
}
