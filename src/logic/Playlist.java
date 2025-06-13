package logic;

/**
 * Represents a single Playlist.
 *
 * @author Fernando Rios
 */
public class Playlist extends SongCollection {
    private static final int HEAD_SONG_INDEX = 0;
    private static final int AFTER_HEAD_SONG_INDEX = 1;

    private final History history;

    /**
     * Constructs a new Playlist.
     */
    public Playlist() {
        history = new History();
    }

    @Override
    public void addSong(Song newSong) {
        expandIfNecessary();
        int songIndex = amountSongs();
        while (songIndex > 0 && newSong.priorityValue() < songAt(songIndex - 1).priorityValue()) {
            setSongAt(songIndex, songAt(songIndex - 1));
            songIndex--;
        }
        addToCollection(newSong, songIndex);
    }

    /**
     * Removes a set of songs the playlist based on their ID.
     *
     * @param identifier The ID number of the songs to remove
     * @return The amount of elements removed.
     */
    public int deleteById(int identifier) {
        int songsRemoved = 0;
        int songIndex = 0;
        while (songIndex < amountSongs()) {
            if (songAt(songIndex).getIdentifier() == identifier) {
                removeSongAt(songIndex);
                songsRemoved++;
            } else {
                songIndex++;
            }
        }
        return songsRemoved;
    }

    /**
     * Plays the Playlist for a specified amount of time.
     *
     * @param time The play time.
     */
    public void playFor(int time) {
        int playTime = time;
        while (isNotEmpty() && playTime > 0) {
            Song current = getHeadSong();
            int timeToPlay = Math.min(playTime, current.getRemainingTime());

            current.play(timeToPlay);
            playTime -= timeToPlay;

            if (current.hasFinished()) {
                history.addSong(current);
                removeHeadSong();
            }
        }
    }

    /**
     * Inserts the given song as the next song to be played.
     *
     * @param nextSong the song to insert next
     */
    public void setNextSong(Song nextSong) {
        expandIfNecessary();
        if (headSongIsPlaying()) {
            shiftAndSetNext(nextSong, AFTER_HEAD_SONG_INDEX);
        } else {
            shiftAndSetNext(nextSong, HEAD_SONG_INDEX);
        }
    }

    /**
     * Returns the head song of the Playlist.
     *
     * @return The head song of the playlist.
     */
    public Song getHeadSong() {
        return songAt(HEAD_SONG_INDEX);
    }

    /**
     * Removes the head song from the playlist.
     */
    public void removeHeadSong() {
        if (isNotEmpty()) {
            removeSongAt(HEAD_SONG_INDEX);
        }
    }

    /**
     * Returns the history associated with this playlist.
     *
     * @return the history object for this playlist.
     */
    public History getHistory() {
        return history;
    }

    /**
     * Inserts the given song at the specified index by shifting all subsequent songs
     * one position to the right.
     *
     * @param nextSong the song to insert
     * @param shiftAt the index at which to insert the song
     */
    private void shiftAndSetNext(Song nextSong, int shiftAt) {
        for (int songIndex = amountSongs(); shiftAt < songIndex; songIndex--) {
            setSongAt(songIndex, songAt(songIndex - 1));
        }
        addToCollection(nextSong, shiftAt);
    }

    private boolean headSongIsPlaying() {
        return getHeadSong() != null && getHeadSong().isPlaying();
    }
}
