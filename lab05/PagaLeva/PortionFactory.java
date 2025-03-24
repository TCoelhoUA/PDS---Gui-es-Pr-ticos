

public class PortionFactory {
    public static Portion create(String pedido, Temperature temperature, String fruta){
        if(pedido.equalsIgnoreCase("Beverage") && temperature == Temperature.COLD) return new FruitJuice(fruta);
        else{
            throw new IllegalArgumentException("ERROR: request " + pedido + " doesn't exist!");
        }
    }

    public static Portion create(String pedido, Temperature temperature) {
        if (pedido.equalsIgnoreCase("Beverage") && temperature == Temperature.WARM)         return new Milk();
        else if (pedido.equalsIgnoreCase("Meat") && temperature == Temperature.WARM)        return new Pork();
        else if (pedido.equalsIgnoreCase("Meat") && temperature == Temperature.COLD)        return new Tuna();
        else {
            throw new IllegalArgumentException("ERROR: request " + pedido + " doesn't exist!");
        }
    }
}
