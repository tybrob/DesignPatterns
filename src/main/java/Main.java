import java.util.ArrayList;
import java.util.List;

public class Main {
    //Identify the aspects that vary and separate them from what stays the same
    //Program to an interface not an implementation
    //Favor Composition over Inheritance
    //Strive for loosely coupled designs between objects that interact
    //Classes should be open for extension but closed for modification
    //Depend on abstractions, do not depend on concrete classes

    public static void main(String[] args) {

        StrategyDesignPattern strategy = new StrategyDesignPattern();
        strategy.run();

        ObserverDesignPattern observer = new ObserverDesignPattern();
        observer.run();

        DecoratorDesignPattern decorator = new DecoratorDesignPattern();
        decorator.run();

        FactoryDesignPattern factory = new FactoryDesignPattern();
        factory.run();
    }

}
