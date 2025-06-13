package controller;

import logic.History;
import logic.Playlist;
import logic.Priority;
import logic.Song;

/**
 * Represents a Moderator in the program.
 *
 * @author Fernando Rios
 */
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

    /**
     * Constructs a Moderator instance.
     */
    public Moderator() {
        playlist = new Playlist();
    }

    /**
     * Adds a new song to the playlist.
     *
     * @param newSong The new song to be added.
     */
    public void addToPlaylist(Song newSong) {
        playlist.addSong(newSong);
    }

    /**
     * Removes all songs from the playlist that have the specified ID.
     *
     * @param identifier the ID of the songs to remove.
     * @return a string message indicating the number of songs removed.
     */
    public String removeFromPlaylist(int identifier) {
        int songsRemoved = playlist.deleteById(identifier);
        return (songsRemoved != 0)
                ? String.format(PREFIX_REMOVED_SONGS, songsRemoved)
                : null;
    }

    /**
     * Reproduces the playlist for a certain time.
     *
     * @param playTime The time the playlist is going to be reproduced.
     */
    public void reproducePlaylist(int playTime) {
        playlist.playFor(playTime);
    }

    /**
     * Returns a string representation of the next song in the playlist.
     *
     * @return the next song as a string.
     */
    public String peekHeadSong() {
        Song headSong = playlist.getHeadSong();
        return (headSong != null)
                ? headSong + FEATURES_DELIMITER + headSong.getRemainingTime()
                : null;
    }

    /**
     * Returns a string value of the current Playlist.
     *
     * @return The string value of the current Playlist
     */
    public String listPlaylist() {
        return (playlist.isNotEmpty())
                ? playlist.toString()
                : null;
    }

    /**
     * Sets the specified song as the next song to be played in the playlist.
     *
     * @param nextSong the song to be set as the next song in the playlist
     */
    public void insertNextSong(Song nextSong) {
        playlist.setNextSong(nextSong);
    }

    /**
     * Skips the next song in the playlist.
     */
    public void skipNextSong() {
        playlist.removeHeadSong();
    }

    /**
     * Returns a string representation of the history of played songs in the playlist.
     *
     * @return a formatted string containing the played songs, or an empty string if the history is unavailable.
     */
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
