package Pastelaria;

public class RedVelvetCakeBuilder implements CakeBuilder {
    Cake cake;

    public void setCakeShape(Shape shape) {
        cake.setShape(shape);
    }

    public void addCakeLayer() {
        cake.setNumCakeLayers(cake.getNumCakeLayers() + 1);
    }

    public void addCreamLayer() {
        cake.setMidLayerCream(Cream.Vanilla);
    }
    
    public void addTopLayer() {
        cake.setTopLayerCream(Cream.Whiped_Cream);
    }

    public void addTopping() {
        cake.setTopping(Topping.Gummies);
    }

    public void addMessage(String m) {
        cake.setMessage(m);
    }
    
    public void createCake() {
        cake = new Cake("Moist Red Velvet");
    }

    public Cake getCake() {
        return cake;
    }
}
