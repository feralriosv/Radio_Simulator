package logic;

public class Playlist extends SongCollection {
    private final History history;

    public Playlist() {
        history = new History();
    }

    @Override
    public void addSong(Song newSong) {
        int index = size();
        while (index > 0 && newSong.priorityValue() < songAt(index - 1).priorityValue()) {
            setSongAt(index, songAt(index - 1));
            index--;
        }
        addToCollection(newSong);
    }

    public int deleteByID(int identifier) {
        int songsRemoved = 0;
        for (int songIndex = 0; songIndex < size(); songIndex++) {
            if (songAt(songIndex).getIdentifier() == identifier) {
                removeAt(songIndex);
                songsRemoved++;
            }
        }
        return songsRemoved;
    }

    public void playFor(int time) {
        int playTime = time;
        while (!isEmpty() && playTime > 0) {
            Song played = songAt(0);
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

    private void removeHeadSong() {
        removeAt(0);
    }
}
