package Bridge;

abstract class Pizza {
    protected String sauce;
    protected String toppings;
    protected String crust;

    public abstract void assemble();

    public abstract void qualityCheck();

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

}

class PepperoniPizza extends Pizza {
    @Override
    public void assemble() {
        System.out.println("Adding Sauce: " + sauce);
        System.out.println("Adding Toppings: " + toppings);
        System.out.println("Adding Pepperoni");
    }

    @Override
    public void qualityCheck() {
        System.out.println("Crust is: " + crust);
    }
}

class VeggiePizza extends Pizza {
    @Override
    public void assemble() {
        System.out.println("Adding Sauce: " + sauce);
        System.out.println("Adding Toppings: " + toppings);
        System.out.println("Adding Pepperoni");
    }

    @Override
    public void qualityCheck() {
        System.out.println("Crust is: " + crust);
    }
}

abstract class Restaurant {
    protected Pizza pizza;

    protected Restaurant(Pizza pizza) {
        this.pizza = pizza;
    }
    abstract void addSauce();
    abstract void addToppings();
    abstract void makeCrust();

    public void deliver() {
        makeCrust();
        addSauce();
        addToppings();
        pizza.assemble();
        pizza.qualityCheck();
        System.out.println("Order in Progress!");
    }
}

class ItalianRestaurant extends Restaurant {
    public ItalianRestaurant(Pizza pizza) {
        super(pizza);
    }

    @Override
    public void addToppings() {
        pizza.setToppings(null);
    }

    @Override
    public void addSauce() {
        pizza.setSauce("Oil");
    }

    @Override
    public void makeCrust() {
        pizza.setCrust("Thin");
    }
}

class AmericanRestraunt extends Restaurant {
    public AmericanRestraunt(Pizza pizza) {
        super(pizza);
    }

    @Override
    public void addToppings() {
        pizza.setToppings("pepperoni");
    }

    @Override
    public void addSauce() {
        pizza.setSauce("Cheese");
    }

    @Override
    public void makeCrust() {
        pizza.setCrust("Thick");
    }
}

public class Main {
    public static void main(String[] args) {
        AmericanRestraunt restraunt = new AmericanRestraunt(new PepperoniPizza());
        restraunt.deliver();
    }
}
