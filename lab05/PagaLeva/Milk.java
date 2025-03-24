

public class Milk implements Portion {
    private State state;
    private Temperature temperature;

    public Milk() {
        super();
        this.state = State.Liquid;
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
        return  "Milk: Temperature " + this.getTemperature() + ", State " + this.getState();
    }
}
