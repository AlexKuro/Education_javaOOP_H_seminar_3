package org.example;

public interface Game {

    void start(Integer sizeBoard, Integer attempt);
    GameStatus end(Boolean fl);

    Answer inputValue(String value);

    GameStatus getGameStatus();
}
