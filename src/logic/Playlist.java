package logic;

public class Playlist extends SongCollection {
    private static final int HEAD_SONG_INDEX = 0;
    private static final int AFTER_HEAD_SONG_INDEX = 1;

    private final History history;

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

    public void setNextSong(Song nextSong) {
        expandIfNecessary();
        if (headSongIsPlaying()) {
            shiftAndSetNext(nextSong, AFTER_HEAD_SONG_INDEX);
        } else {
            shiftAndSetNext(nextSong, HEAD_SONG_INDEX);
        }
    }

    public Song getHeadSong() {
        return songAt(HEAD_SONG_INDEX);
    }

    public void removeHeadSong() {
        if (isNotEmpty()) {
            removeSongAt(HEAD_SONG_INDEX);
        }
    }

    public History getHistory() {
        return history;
    }

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
