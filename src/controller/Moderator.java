package controller;

import command.CommandHandler;
import logic.Playlist;

public class Moderator {
    private final Playlist playlist;
    private final CommandHandler commandHandler;

    public Moderator(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        playlist = new Playlist();
    }
}
