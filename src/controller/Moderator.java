package controller;

import logic.History;
import logic.Playlist;
import logic.Priority;
import logic.Song;

public class Moderator {
    private final Playlist playlist;

    public Moderator() {
        playlist = new Playlist();
    }

    public void addToPlaylist(Song newSong) {
        playlist.addSong(newSong);
    }

    public String removeFromPlaylist(int identifier) {
        int songsRemoved = playlist.deleteByID(identifier);
        return (songsRemoved != 0)
                ? String.format("Removed %d songs.", songsRemoved)
                : null;
    }

    public void reproducePlaylist(int playTime) {
        playlist.playFor(playTime);
    }

    public String peekHeadSong() {
        Song headSong = playlist.getHeadSong();
        return (headSong != null)
                ? headSong + ":" + headSong.getRemainingTime()
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
        int identifier = Integer.parseInt(songFeatures[0]);
        String artist = songFeatures[1];
        String title = songFeatures[2];
        int duration = Integer.parseInt(songFeatures[3]);
        if (songFeatures.length > 4) {
            int priorityLevel = Integer.parseInt(songFeatures[4]);
            Priority songPriority = Priority.fromLevel(priorityLevel);
            return new Song(identifier, artist, title, duration, songPriority);
        }
        return new Song(identifier, artist, title, duration);
    }

    private String[] extractSongFeatures(String songArgument) {
        String[] features = songArgument.split(":");
        for (int i = 0; i < features.length; i++) {
            features[i] = features[i].trim();
        }
        return features;
    }
}
