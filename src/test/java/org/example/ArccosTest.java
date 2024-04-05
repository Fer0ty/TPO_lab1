package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.example.task1.Arccos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.concurrent.TimeUnit;

public class ArccosTest {


    @ParameterizedTest
    @CsvSource({"1", "0", "0.5", "-0.5", "-1"})
//    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    @DisplayName("Проверка ответа на корректных значениях")
    public void testArccosValidInput(double value) {
        assertEquals(Math.acos(value), Arccos.calc(value), 1e-7);
    }

    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    @ParameterizedTest
    @CsvSource({"1.5", "-1.5"})
    @DisplayName("Проверка на некорректных значениях")
    public void testArccosInvalidInput(double value) {
        assertThrows(IllegalArgumentException.class, () -> Arccos.calc(value));
    }


}
