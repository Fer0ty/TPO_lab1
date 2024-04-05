package org.example;


import org.example.task3.LightPoint;
import org.example.task3.Sun;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DomainModuleTest {
    static Sun sun;
    static LightPoint lightPoint;

    @DisplayName("LightPoint Position Changing")
    @ParameterizedTest
    @ValueSource(strings = {"В полной темноте", "Очень близко"})
    public void PointChangePosition(String position) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        lightPoint = new LightPoint("Далеко в космосе");
        lightPoint.setPosition(position);

        assertEquals("Точка света поменяла свою позицию с Далеко в космосе на " + position + "\n", outputStream.toString());
    }

    @DisplayName("LightPoint Action Changing")
    @ParameterizedTest
    @ValueSource(strings = {"Расползается", "Угасает"})
    public void pointChangeAction(String action) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        lightPoint = new LightPoint("Далеко в космосе");
        lightPoint.setAction(action);

        assertEquals("Точка света " + action + "\n", outputStream.toString());
    }

    @DisplayName("Sun Temperature Changing")
    @ParameterizedTest
    @ValueSource(ints = {3800, 5000, 6000, 7400, 10000, 30000, 31000})

    public void SunChangeTemperature(int temperature) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        sun = new Sun("Тестовое_Солнце");
        sun.setTemperature(temperature);

        assertEquals("Солнце Тестовое_Солнце перешло в " + sun.getFlameColour() + " спектральный класс.\n" + "Температура солнца Тестовое_Солнце стала " + temperature + "К.\n", outputStream.toString());
    }

    @DisplayName("Sun Name Changing")
    @ParameterizedTest
    @ValueSource(strings = {"Солнце", "Солнце2"})
    public void SunChangeName(String name) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        sun = new Sun("Тестовое_Солнце");
        sun.setSunName(name);
        assertEquals("Астрономы переименовали Тестовое_Солнце. Теперь этот космический объект называется " + name+"\n", outputStream.toString());
    }


}
