package Pastelaria;

public class SpongeCakeBuilder implements CakeBuilder {
    Cake cake;

    public void setCakeShape(Shape shape) {
        cake.setShape(shape);
    }

    public void addCakeLayer() {
        cake.setNumCakeLayers(cake.getNumCakeLayers() + 1);
    }

    public void addCreamLayer() {
        cake.setMidLayerCream(Cream.Red_Berries);
    }
    
    public void addTopLayer() {
        cake.setTopLayerCream(Cream.Whiped_Cream);
    }

    public void addTopping() {
        cake.setTopping(Topping.Fruit);
    }

    public void addMessage(String m) {
        cake.setMessage(m);
    }
    
    public void createCake() {
        cake = new Cake("Sponge");
    }

    public Cake getCake() {
        return cake;
    }
}
