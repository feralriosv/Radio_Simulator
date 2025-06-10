package controller;

import logic.Playlist;
import logic.Priority;
import logic.Song;

public class Moderator {
    private final Playlist playlist;

    public Moderator() {
        playlist = new Playlist();
    }

    public void addToPlaylist(Song newSong) {
        playlist.add(newSong);
    }

    public String removeFromPlaylist(int identifier) {
        int songsRemoved = playlist.deleteByID(identifier);
        if (songsRemoved != 0) {
            return String.format("Removed %d songs.", songsRemoved);
        }
        return null;
    }

    public Song parseSong(String songArgument) {
        String[] songFeatures = extractSongFeatures(songArgument);
        int identifier = Integer.parseInt(songFeatures[0]);
        String artist = songFeatures[1];
        String title = songFeatures[2];
        int duration = Integer.parseInt(songFeatures[3]);
        if (songFeatures.length > 4) {
            int priorityLevel = Integer.parseInt(songFeatures[5]);
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
