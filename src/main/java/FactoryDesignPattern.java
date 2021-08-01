
public class FactoryDesignPattern {
    //Abstract Factory: Provides an interface for creating families of related or dependent objects
    //without specifying their concrete classes.
    //Factory Method: Defines an interface for creating an object but let subclasses decide which class to instantiate
    //lets a class defer instantiation to the subclasses

    public void run() {
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();

        Pizza pizza;

        pizza = nyStore.orderPizza("cheese");
        pizza = chicagoStore.orderPizza("cheese");
    }

    //Abstract Factory
    public abstract class Pizza {

        String name;
        Dough dough;
        Sauce sauce;
        Cheese cheese;
        PizzaIngredientFactory ingredientFactory;

        void bake() {
            System.out.println("Bake for 25 minutes at 350");
        }

        void cut() {
            System.out.println("Cutting the pizza into diagonal slices");
        }

        void box() {
            System.out.println("Place pizza in official PizzaStore box");
        }

        void prepare() {
            System.out.println("preparing " + this.name);
            cheese = this.ingredientFactory.createCheese();
            dough = this.ingredientFactory.createDough();
            sauce = this.ingredientFactory.createSauce();
        }
    }

    public class NYStyleCheesePizza extends Pizza {

        public NYStyleCheesePizza(PizzaIngredientFactory ingredientFactory) {
            this.name = "NY";
            this.ingredientFactory = ingredientFactory;
        }
    }

    public class ChicagoStylePizza extends Pizza {

        public ChicagoStylePizza(PizzaIngredientFactory ingredientFactory) {
            this.name = "Chicago";
            this.ingredientFactory = ingredientFactory;
        }

        @Override
        void cut() {
            System.out.println("Cutting the pizza into square slices");
        }
    }

    private class NYStyleVeggiePizza extends Pizza {
        public NYStyleVeggiePizza(PizzaIngredientFactory ingredientFactory) {
            this.ingredientFactory = ingredientFactory;
        }
    }

    private class NYStyleClamPizza extends Pizza {
        public NYStyleClamPizza(PizzaIngredientFactory ingredientFactory) {
            this.ingredientFactory = ingredientFactory;
        }
    }

    private class ChicagoStyleVeggiePizza extends Pizza {
        public ChicagoStyleVeggiePizza(PizzaIngredientFactory ingredientFactory) {
            this.ingredientFactory = ingredientFactory;
        }
    }

    private class ChicagoClamPizza extends Pizza {
        public ChicagoClamPizza(PizzaIngredientFactory ingredientFactory) {
            this.ingredientFactory = ingredientFactory;
        }
    }

    public abstract class PizzaStore {

        public Pizza orderPizza(String type) {

            Pizza pizza;

            pizza = createPizza(type);

            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();

            return pizza;
        }

        abstract Pizza createPizza(String type);
    }

    public class NYPizzaStore extends PizzaStore {

        Pizza createPizza(String item) {
            PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();
            if (item.equals("cheese")) {
                return new NYStyleCheesePizza(ingredientFactory);
            } else if (item.equals("veggie")) {
                return new NYStyleVeggiePizza(ingredientFactory);
            } else if (item.equals("clam")) {
                return new NYStyleClamPizza(ingredientFactory);
            } else return null;
        }
    }

    public class ChicagoPizzaStore extends PizzaStore {

        Pizza createPizza(String item) {
            PizzaIngredientFactory ingredientFactory = new ChicagoPizzaIngredientFactory();
            if (item.equals("cheese")) {
                return new ChicagoStylePizza(ingredientFactory);
            } else if (item.equals("veggie")) {
                return new ChicagoStyleVeggiePizza(ingredientFactory);
            } else if (item.equals("clam")) {
                return new ChicagoClamPizza(ingredientFactory);
            } else return null;
        }
    }

    //Factory Method
    public interface PizzaIngredientFactory {

        Dough createDough();
        Sauce createSauce();
        Cheese createCheese();
    }

    public interface Dough { }
    public interface Sauce { }
    public interface Cheese { }

    public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

        @Override
        public Dough createDough() {
            return new ThinCrustDough();
        }

        @Override
        public Sauce createSauce() {
            return new MarinaraSauce();
        }

        @Override
        public Cheese createCheese() {
            return new ReggianoCheese();
        }
    }

    public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {

        @Override
        public Dough createDough() {
            return new ThickCrustDough();
        }

        @Override
        public Sauce createSauce() {
            return new PulmTomatoSauce();
        }

        @Override
        public Cheese createCheese() {
            return new MozarellaCheese();
        }
    }

    public class ThinCrustDough implements Dough { }
    public class ThickCrustDough implements Dough { }
    public class MarinaraSauce implements Sauce { }
    public class PulmTomatoSauce implements Sauce { }
    public class ReggianoCheese implements Cheese { }
    public class MozarellaCheese implements Cheese { }
}