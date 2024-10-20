package se.artcomputer.example.of.good.state;

import static se.artcomputer.example.of.good.state.TrafficLight3.State.*;

public class TrafficLight3 {
    private State state;

    public enum State {
        RED,
        RED_YELLOW,
        GREEN,
        YELLOW
    }

    public void nextState() {
        state = switch (state) {
            case RED -> RED_YELLOW;
            case RED_YELLOW -> GREEN;
            case GREEN -> YELLOW;
            case YELLOW -> RED;
        };
    }

    public TrafficLight3(State initialState) {
        this.state = initialState;
    }

    public boolean isRed() {
        return state == RED;
    }

    public boolean isYellow() {
        return state == YELLOW || state == RED_YELLOW;
    }

    public boolean isGreen() {
        return state == GREEN;
    }
}