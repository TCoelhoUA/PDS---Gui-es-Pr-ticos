package Pastelaria;

public class Pastelaria {
    public static void main(String[] args) {
        CakeMaster cakeMaster = new CakeMaster();

        CakeBuilder chocolate = new ChocolateCakeBuilder();
        cakeMaster.setCakeBuilder(chocolate);
        cakeMaster.createCake("Congratulations");               // 1 cake layer
        Cake cake = cakeMaster.getCake();
        System.out.println("Your cake is ready: " + cake);

        CakeBuilder sponge = new SpongeCakeBuilder();
        cakeMaster.setCakeBuilder(sponge);
        cakeMaster.createCake(Shape.Square, 2, "Well done");    // squared, 2 layers
        cake = cakeMaster.getCake();
        System.out.println("Your cake is ready: " + cake);
        
        CakeBuilder yogurt = new YogurtCakeBuilder();
        cakeMaster.setCakeBuilder(yogurt);
        cakeMaster.createCake(3, "The best");                   // 3 cake layers
        cake = cakeMaster.getCake();
        System.out.println("Your cake is ready: " + cake);
        
        // you should add here other example(s) of CakeBuilder
        CakeBuilder red_velt = new RedVelvetCakeBuilder();
        cakeMaster.setCakeBuilder(red_velt);
        cakeMaster.createCake(Shape.Triangle, 4, "Happy Birthday Grandpa!");
        cake = cakeMaster.getCake();
        System.out.println("Your cake is ready: " + cake);

        CakeBuilder carrot = new CarrotCakeBuilder();
        cakeMaster.setCakeBuilder(carrot);
        cakeMaster.createCake(Shape.Circle, 3, "NNNNNNNÓOOOOO");
        cake = cakeMaster.getCake();
        System.out.println("Your cake is ready: " + cake);


    }
}
