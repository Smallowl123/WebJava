package model;


import java.io.Serializable;

public class Day implements Serializable {
    String date;
    double maxTemperatureC;
    double minTemperatureC;
    String w_Event;

    public Day (String date, double maxTemperatureC, double minTemperatureC, String w_Event) {
        this.date = date;
        this.maxTemperatureC = maxTemperatureC;
        this.minTemperatureC = minTemperatureC;
        this.w_Event = w_Event;
    }

    public String getDate() {
        return date;
    }

    public double getMaxTemperatureC() {
        return maxTemperatureC;
    }

    public double getMinTemperatureC() {
        return minTemperatureC;
    }

    public String getW_Event() {
        return w_Event;
    }

    @Override
    public String toString() {
        return "Day{" +
                "date=" + date +
                ", maxTemperatureC=" + maxTemperatureC +
                ", minTemperatureC=" + minTemperatureC +
                ", w_Event=" + w_Event +
                '}';
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMaxTemperatureC(double maxTemperatureC) {
        this.maxTemperatureC = maxTemperatureC;
    }

    public void setMinTemperatureC(double minTemperatureC) {
        this.minTemperatureC = minTemperatureC;
    }

    public void setW_Event(String w_Event) {
        this.w_Event = w_Event;
    }
}
