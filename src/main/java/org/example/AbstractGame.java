package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractGame implements Game {

    Integer sizeWord = 0;
    Integer attempts = 0;
    String word;
    GameStatus gameStatus;
    int bull;
    int cows;

    @Override
    public void start(Integer sizeBoard, Integer attempts) {
        this.sizeWord = sizeBoard;
        this.attempts = attempts;
        word = generateWorld(); // загаданое слово ПК
//        System.out.println("- " + word + " -");
        this.gameStatus = GameStatus.START;
    }

    @Override
    public GameStatus end(Boolean fl) {
        if (fl == true) {
            this.gameStatus = GameStatus.END;
        }
        else {
            this.gameStatus = GameStatus.INIT;
        }
        return gameStatus;
    }

    @Override
    public Answer inputValue(String value) {
        bull = 0;
        cows = 0;

        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == word.charAt(i)) {
                bull++;
            }
            char character = value.charAt(i);
            if (word.contains(Character.toString(character))) {
                cows++;
            }

        }
        attempts--;
        return new Answer(value, cows, bull, attempts);
    }

    @Override
    public GameStatus getGameStatus() {
        if (attempts == 0) {
            gameStatus = GameStatus.LOSE;
        }
        if (bull == sizeWord & cows == sizeWord) {
            gameStatus = GameStatus.WIN;
        }
        return gameStatus;
    }


    public String generateWorld() {
        Random random = new Random();
        List<String> charList = generateCharList();
        String resWorld = "";
        for (int i = 0; i < sizeWord; i++) {
            int randomIndex = random.nextInt(charList.size());
            resWorld = resWorld.concat(charList.get(randomIndex));
            charList.remove(randomIndex);
        }
        return resWorld;
    }

    abstract List<String> generateCharList();
}
