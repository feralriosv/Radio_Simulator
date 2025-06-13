package logic;

public class Playlist extends SongCollection {
    private final History history;

    public Playlist() {
        history = new History();
    }

    @Override
    public void addSong(Song newSong) {
        expandIfNecessary();
        int index = amountSongs();
        while (index > 0 && newSong.priorityValue() < songAt(index - 1).priorityValue()) {
            setSongAt(index, songAt(index - 1));
            index--;
        }
        addToCollection(newSong, index);
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
        while (0 < amountSongs() && 0 < playTime) {
            Song played = getHeadSong();
            if (played.getRemainingTime() <= playTime) {
                playTime -= played.getRemainingTime();
                history.addSong(played);
                removeHeadSong();
            } else {
                played.play(playTime);
                playTime = 0;
            }
        }
    }

    public void setNextSong(Song nextSong) {
        expandIfNecessary();
        if (headSongIsPlaying()) {
            shiftAndSetNext(nextSong, 1);
        } else {
            shiftAndSetNext(nextSong, 0);
        }
    }

    public Song getHeadSong() {
        return songAt(0);
    }

    public void removeHeadSong() {
        if (amountSongs() > 0) {
            removeSongAt(0);
        }
    }

    public History getHistory() {
        return history;
    }

    private void shiftAndSetNext(Song nextSong, int shiftAt) {
        for (int i = shiftAt; i < amountSongs(); i++) {
            setSongAt(i, songAt(i + 1));
        }
        addToCollection(nextSong, shiftAt);
    }

    private boolean headSongIsPlaying() {
        return getHeadSong() != null && getHeadSong().isPlaying();
    }
}
