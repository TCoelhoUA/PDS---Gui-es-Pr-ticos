package Pastelaria;

public class ChocolateCakeBuilder implements CakeBuilder {
    Cake cake;

    public void setCakeShape(Shape shape) {
        cake.setShape(shape);
    }

    public void addCakeLayer() {
        cake.setNumCakeLayers(cake.getNumCakeLayers() + 1);
    }

    public void addCreamLayer() {
        cake.setMidLayerCream(Cream.Whiped_Cream);
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
        cake = new Cake("Soft chocolate");
    }

    public Cake getCake() {
        return cake;
    }
}
