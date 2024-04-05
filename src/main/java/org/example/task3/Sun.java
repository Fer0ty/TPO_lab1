package org.example.task3;

public class Sun {
    private String flameColour;
    private int temperatureK;

    private String name;

    private String lastSunName;

    public Sun(String name) {
        this.name = name;
    }

    public void setTemperature(int temperatureK) {
        this.temperatureK = temperatureK;
        if (temperatureK > 30000) {
            setFlameColour("Голубой");
        } else if (temperatureK > 10000) {
            setFlameColour("Бело-голубой");
        } else if (temperatureK > 7400) {
            setFlameColour("Белый");
        } else if (temperatureK > 6000) {
            setFlameColour("Желто-белый");
        } else if (temperatureK > 5000) {
            setFlameColour("Желтый");
        } else if (temperatureK > 3800) {
            setFlameColour("Оранжевый");
        } else {
            setFlameColour("Красный");
        }
        System.out.println("Температура солнца " + getSunName() + " стала " + getTemperatureK() + "К.");
    }

    public int getTemperatureK() {
        return temperatureK;
    }

    public String getSunName() {
        return name;
    }

    public void setSunName(String name) {
        setLastSunName(this.name);
        this.name = name;
        System.out.println("Астрономы переименовали " + getLastSunName() + ". Теперь этот космический объект называется " + getSunName());
    }

    private void setLastSunName(String lastSunName) {
        this.lastSunName = lastSunName;
    }

    private void setFlameColour(String flameColour) {
        this.flameColour = flameColour;
        System.out.println("Солнце " + getSunName() + " перешло в " + getFlameColour() + " спектральный класс.");
    }

    public String getFlameColour() {
        return flameColour;
    }

    public String getLastSunName() {
        return lastSunName;
    }
}
