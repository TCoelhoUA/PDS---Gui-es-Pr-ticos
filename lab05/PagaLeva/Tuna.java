public class Tuna implements Portion {
    private State state;
    private Temperature temperature;

    public Tuna() {
        super();
        this.state = State.Solid;
        this.temperature = Temperature.COLD;
    }

    public State getState() {
        return state;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return  "Tuna: Temperature " + this.getTemperature() + ", State " + this.getState();
    }
}
