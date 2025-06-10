package logic;

public class Playlist {
    private Song[] songs;
    private int capacity;
    private int totalSongs;

    public Playlist() {
        this.capacity = 6;
        songs = new Song[capacity];
        totalSongs = 0;
    }

    public void add(Song newSong) {
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
//                history.addSong(current);
                current.setRemainingTime(0);
            } else {
                current.play(playTime);
                playTime = 0;
            }
        }
        updatePlaylist();
    }

    private void expandIfNecessary() {
        if (capacity == totalSongs) {
            int newCapacity = songs.length * 2;
            Song[] newSongs = new Song[newCapacity];
            System.arraycopy(songs, 0, newSongs, 0, newSongs.length);
            songs = newSongs;
            capacity = newCapacity;
        }
    }

    private boolean isEmpty() {
        return totalSongs == 0;
    }

    private void updatePlaylist() {
        int writeIndex = 0;
        for (int readIndex = 0; readIndex < totalSongs; readIndex++) {
            if (songs[readIndex].getRemainingTime() > 0) {
                songs[writeIndex++] = songs[readIndex];
            }
        }
        for (int i = writeIndex; i < totalSongs; i++) {
            songs[i] = null;
        }
        totalSongs = writeIndex;
    }
}
