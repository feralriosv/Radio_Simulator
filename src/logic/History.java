package logic;

/**
 * Represents the history of played songs in a playlist.
 *
 * @author Fernando Rios
 */
public class History extends SongCollection {

    /**
     * Constructs a new History instance linked to the specified playlist.
     */
    public History() {
    }

    @Override
    public void addSong(Song song) {
        expandIfNecessary();
        addToCollection(song, amountSongs());
    }
}
