package ru.solomka.cross.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class IntPair<T extends Integer, T1 extends Integer> {
    T firstNumber;
    T1 secondNumber;
}
