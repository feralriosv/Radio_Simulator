package controller;

import logic.History;
import logic.Playlist;
import logic.Priority;
import logic.Song;

public class Moderator {
    private static final String PREFIX_REMOVED_SONGS = "Removed %d songs.";
    private static final String FEATURES_DELIMITER = ":";
    private static final int ID_INDEX = 0;
    private static final int ARTIST_INDEX = 1;
    private static final int TITLE_INDEX = 2;
    private static final int DURATION_INDEX = 3;
    private static final int PRIORITY_INDEX = 4;
    private static final int ADD_ARGUMENTS_LENGTH = 5;

    private final Playlist playlist;

    public Moderator() {
        playlist = new Playlist();
    }

    public void addToPlaylist(Song newSong) {
        playlist.addSong(newSong);
    }

    public String removeFromPlaylist(int identifier) {
        int songsRemoved = playlist.deleteById(identifier);
        return (songsRemoved != 0)
                ? String.format(PREFIX_REMOVED_SONGS, songsRemoved)
                : null;
    }

    public void reproducePlaylist(int playTime) {
        playlist.playFor(playTime);
    }

    public String peekHeadSong() {
        Song headSong = playlist.getHeadSong();
        return (headSong != null)
                ? headSong + FEATURES_DELIMITER + headSong.getRemainingTime()
                : null;
    }

    public String listPlaylist() {
        return (playlist.isNotEmpty())
                ? playlist.toString()
                : null;
    }

    public void insertNextSong(Song nextSong) {
        playlist.setNextSong(nextSong);
    }

    public void skipNextSong() {
        playlist.removeHeadSong();
    }

    public String listPlaylistHistory() {
        History playlistHistory = playlist.getHistory();
        return (playlistHistory.isNotEmpty())
                ? playlistHistory.toString()
                : null;
    }

    public Song parseSong(String songArgument) {
        String[] songFeatures = extractSongFeatures(songArgument);
        int identifier = Integer.parseInt(songFeatures[ID_INDEX]);
        String artist = songFeatures[ARTIST_INDEX];
        String title = songFeatures[TITLE_INDEX];
        int duration = Integer.parseInt(songFeatures[DURATION_INDEX]);

        if (songFeatures.length == ADD_ARGUMENTS_LENGTH) {
            int priorityLevel = Integer.parseInt(songFeatures[PRIORITY_INDEX]);
            Priority songPriority = Priority.fromLevel(priorityLevel);
            return new Song(identifier, artist, title, duration, songPriority);
        }

        return new Song(identifier, artist, title, duration);
    }

    private String[] extractSongFeatures(String songArgument) {
        String[] features = songArgument.split(FEATURES_DELIMITER);
        for (int i = 0; i < features.length; i++) {
            features[i] = features[i].trim();
        }
        return features;
    }
}
