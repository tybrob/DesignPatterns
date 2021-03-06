import java.util.ArrayList;
import java.util.List;

public class Main {
    //Identify the aspects that vary and separate them from what stays the same
    //Program to an interface not an implementation
    //Favor Composition over Inheritance
    //Strive for loosely coupled designs between objects that interact
    //Classes should be open for extension but closed for modification
    //Depend on abstractions, do not depend on concrete classes
    //Talk only to your immediate friends
    //Don't Call us, we'll call you (Template Design Pattern)
    //A class should have only one reason to change SRP

    public static void main(String[] args) {

        StrategyDesignPattern strategy = new StrategyDesignPattern();
        strategy.run();
        System.out.println("--------------------------------------");
        ObserverDesignPattern observer = new ObserverDesignPattern();
        observer.run();
        System.out.println("--------------------------------------");
        DecoratorDesignPattern decorator = new DecoratorDesignPattern();
        decorator.run();
        System.out.println("--------------------------------------");
        FactoryDesignPattern factory = new FactoryDesignPattern();
        factory.run();
        System.out.println("--------------------------------------");
        CommandDesignPattern command = new CommandDesignPattern();
        command.run();
        System.out.println("--------------------------------------");
        AdapterDesignPattern adapter = new AdapterDesignPattern();
        adapter.run();
        System.out.println("--------------------------------------");
        FacadeDesignPattern facade = new FacadeDesignPattern();
        facade.run();
        System.out.println("--------------------------------------");
        TemplateDesignPattern template = new TemplateDesignPattern();
        template.run();
        System.out.println("--------------------------------------");
        IteratorDesignPattern iterator = new IteratorDesignPattern();
        iterator.run();
        System.out.println("--------------------------------------");
        CompositeDesignPattern composite = new CompositeDesignPattern();
        composite.run();
        System.out.println("--------------------------------------");
        StateDesignPattern state = new StateDesignPattern();
        state.run();
        System.out.println("--------------------------------------");
        ProxyDesignPattern proxy = new ProxyDesignPattern();
        proxy.run();
    }
}
