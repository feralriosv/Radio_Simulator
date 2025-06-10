package logic;

public class History {
    private Song[] played;
    private int capacity;
    private int totalPlayed;

    public History() {
        capacity = 3;
        played = new Song[capacity];
        totalPlayed = 0;
    }

}
