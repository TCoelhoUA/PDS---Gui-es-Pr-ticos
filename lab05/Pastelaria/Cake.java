package Pastelaria;

public class Cake {

    private Shape shape;
    private String cakeLayer;
    private int numCakeLayers;
    private Cream midLayerCream;
    private Cream topLayerCream;
    private Topping topping;
    private String message;

    public Cake(String cakeLayer) {
        this.cakeLayer = cakeLayer;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public String getCakeLayer() {
        return cakeLayer;
    }

    public void setCakeLayer(String cakeLayer) {
        this.cakeLayer = cakeLayer;
    }

    public int getNumCakeLayers() {
        return numCakeLayers;
    }

    public void setNumCakeLayers(int numCakeLayers) {
        this.numCakeLayers = numCakeLayers;
    }

    public Cream getMidLayerCream() {
        return midLayerCream;
    }

    public void setMidLayerCream(Cream midLayerCream) {
        this.midLayerCream = midLayerCream;
    }

    public Cream getTopLayerCream() {
        return topLayerCream;
    }

    public void setTopLayerCream(Cream topLayerCream) {
        this.topLayerCream = topLayerCream;
    }

    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        String result;

        if (numCakeLayers > 1) {
            result = this.getCakeLayer() + " cake with " + this.getNumCakeLayers() +
            " layers and " + this.getMidLayerCream() +
            " cream, topped with " + this.getTopLayerCream() +
            " cream and " + this.getTopping() +
            ". Message says: \"" + this.getMessage() + "\"";
        }
        else {
            result = this.getCakeLayer() + " cake with " + this.getNumCakeLayers() +
            " layers, topped with " + this.getTopLayerCream() +
            " cream and " + this.getTopping() +
            ". Message says: \"" + this.getMessage() + "\"";
        }

        return result;
    }
}