package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    private String userInput;
    private Integer cow;
    private Integer bull;
    private Integer numAttempts;

    @Override
    public String toString() {
        return "'" + ", Коров - " + cow + ", Быков - " + bull + ", Попытка: '" + (numAttempts + 1) + "'}";
    }
}
