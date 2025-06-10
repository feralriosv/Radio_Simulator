package logic;

public class Playlist extends SongCollection {
    private final History history;

    public Playlist() {
        history = new History();
    }

    public void addSong(Song newSong) {
        expandIfNecessary();
        int index = totalSongs;
        while (index > 0 && newSong.priorityValue() < songs[index - 1].priorityValue()) {
            songs[index] = songs[index - 1];
            index--;
        }
        songs[index] = newSong;
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

    public void playFor(int time) {
        int playTime = time;
        while (!isEmpty() && playTime > 0) {
            Song current = songs[0];
            if (current.getRemainingTime() <= playTime) {
                playTime -= current.getRemainingTime();
                history.addSong(current);
                removeFirstSong();
            } else {
                current.play(playTime);
                playTime = 0;
            }
        }
    }

    private void removeFirstSong() {
        if (totalSongs == 0) {
            return;
        }
        for (int i = 0; i < totalSongs - 1; i++) {
            songs[i] = songs[i + 1];
        }
        songs[totalSongs - 1] = null;
        totalSongs--;
    }
}
