package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Archive {
    private Integer count;
    private String time;

    private String input;
    private Answer arch;

    @Override
    public String toString() {
        return count + ": { Время: '" + time + '\'' + ", данные: '" + input + '\'' + arch;
    }
}
