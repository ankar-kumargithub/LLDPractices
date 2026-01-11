package Decorator;

interface Pizza {
    public int calculateCost();
    public String getDescription();
}

class BasePizza implements Pizza {
    private String description;

    public BasePizza(String description) {
        this.description = description;

    }

    @Override
    public int calculateCost() {
        return 100;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}

class Margeritta implements Pizza {
    private String description;

    public Margeritta(String description) {
        this.description = description;

    }

    @Override
    public int calculateCost() {
        return 120;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}

abstract class PizzaDecorator implements Pizza{
    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
    
    @Override
    public int calculateCost() {
        return pizza.calculateCost();
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }
}

class CheeseTopping extends PizzaDecorator {

    public CheeseTopping(Pizza pizza) {
        super(pizza);
    }

    @Override
    public int calculateCost() {
        return super.calculateCost() + 20;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Cheese "; 
    }
}

class VeggieTopping extends PizzaDecorator {

    public VeggieTopping(Pizza pizza) {
        super(pizza);
    }

    @Override
    public int calculateCost() {
        return super.calculateCost() + 10;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Veggie "; 
    }
}

public class Main {
    public static void main(String[] args) {
        Pizza pizza1 = new CheeseTopping(new VeggieTopping(new BasePizza("Base Pizza")));
        Pizza pizza2 = new CheeseTopping(new Margeritta("Margeritta Pizza"));
        System.out.println(pizza1.getDescription());
        System.out.println(pizza1.calculateCost());
        System.out.println(pizza2.getDescription());
        System.out.println(pizza2.calculateCost());
    }
}
