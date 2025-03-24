

public class Container {
    protected Portion portion;

    protected Container(Portion portion) {
        this.portion = portion;
    }

    public Portion getPortion() {
        return portion;
    }

    public static Container create(Portion portion) {
        State state = portion.getState();
        Temperature temperature = portion.getTemperature();
        
        if (state == State.Liquid && temperature == Temperature.COLD)           return new PlasticBottle(portion);
        
        else if(state == State.Liquid && (temperature == Temperature.WARM ||
                                          temperature == Temperature.COLD))     return new TermicBottle(portion);

        else if (state == State.Solid && (temperature == Temperature.WARM ||
                                          temperature == Temperature.WARM))     return new Tupperware(portion);

        else if (state == State.Solid && temperature == Temperature.COLD)       return new PlasticBag(portion);

        else {
            throw new IllegalArgumentException("ERROR: Invalid portion! (" + portion + ")");
        }
    }
}
