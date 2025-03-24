

public class FruitJuice implements Portion {
    private State state;
    private Temperature temperature;
    private String fruit;

    public FruitJuice(String fruit) {
        super();
        this.state = State.Liquid;
        this.temperature = Temperature.COLD;
        this.fruit = fruit;
    }

    public State getState() {
        return this.state;
    }

    public Temperature getTemperature() {
        return this.temperature;
    }

    public String getFruit(){
        return this.fruit;
    }

    @Override
    public String toString() {
        return  "FruitJuice: " + this.getFruit() + ", Temperature " + this.getTemperature() + ", State " + this.getState();
    }
}
