package Pastelaria;

public class CakeMaster {
    private CakeBuilder cb;

    public void setCakeBuilder(CakeBuilder cb) {
        this.cb = cb;
    }
    
    // 1 parameter
    public void createCake(String message) {
        createCake(1, message);
    }
    
    // 2 parameter
    public void createCake(int numCakeLayers, String message) {
        createCake(Shape.Rectangle, numCakeLayers, message);
    }

    // 3 parameter
    public void createCake(Shape shape, int numCakeLayers, String message) {
        cb.createCake();
        cb.setCakeShape(shape);
        for (int i=0; i<numCakeLayers; i++) {
            cb.addCakeLayer();
        }
        cb.addCreamLayer();
        cb.addTopLayer();
        cb.addTopping();
        cb.addMessage(message);
    }
    
    public Cake getCake() {
        return cb.getCake();
    }
}
