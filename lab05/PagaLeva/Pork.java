

public class Pork implements Portion {
    private State state;
    private Temperature temperature;

    public Pork() {
        super();
        this.state = State.Solid;
        this.temperature = Temperature.WARM;
    }

    public State getState() {
        return state;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return  "Pork: Temperature " + this.getTemperature() + ", State " + this.getState();
    }
}
