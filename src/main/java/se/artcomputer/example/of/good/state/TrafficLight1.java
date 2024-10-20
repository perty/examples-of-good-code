package se.artcomputer.example.of.good.state;

public class TrafficLight1 {
    private boolean red;
    private boolean yellow;
    private boolean green;

    public TrafficLight1(boolean red, boolean yellow, boolean green) {
        this.red = red;
        this.yellow = yellow;
        this.green = green;
    }

    public void setState(boolean red, boolean yellow, boolean green) {
        this.red = red;
        this.yellow = yellow;
        this.green = green;
    }

    public boolean isRed() {
        return red;
    }

    public boolean isYellow() {
        return yellow;
    }

    public boolean isGreen() {
        return green;
    }
}
