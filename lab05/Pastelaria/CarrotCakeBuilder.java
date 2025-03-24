package Pastelaria;

public class CarrotCakeBuilder implements CakeBuilder {
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
        cake.setTopLayerCream(Cream.Chocolate_Ganache);
    }

    public void addTopping() {
        cake.setTopping(Topping.Chocolate);
    }

    public void addMessage(String m) {
        cake.setMessage(m);
    }
    
    public void createCake() {
        cake = new Cake("Carrot");
    }

    public Cake getCake() {
        return cake;
    }
}
