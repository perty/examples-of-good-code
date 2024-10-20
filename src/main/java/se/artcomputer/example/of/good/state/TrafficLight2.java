package se.artcomputer.example.of.good.state;

public class TrafficLight2 {
    private State state;

    public enum State {
        RED( true, false, false),
        RED_YELLOW( true, true, false),
        GREEN( false, false, true),
        YELLOW( false, true, false);

        final boolean red;
        final boolean yellow;
        final boolean green;

        State( boolean red, boolean yellow, boolean green) {
            this.red = red;
            this.yellow = yellow;
            this.green = green;
        }
    }

    public TrafficLight2(State initialState) {
        this.state = initialState;
    }

    public void setState(State state) {
       this.state = state;
    }

    public boolean isRed() {
        return state.red;
    }

    public boolean isYellow() {
        return state.yellow;
    }

    public boolean isGreen() {
        return state.green;
    }
}