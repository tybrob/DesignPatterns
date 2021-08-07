public class TemplateDesignPattern {
    //defines the skeleton of an algorithm in a method, deferring some steps to subclasses.
    // Template Method lets subclasses redefine certain steps of an algorithm without changing the algorithm’s structure.

    public void run() {

        Tea tea = new Tea();
        Coffee coffee = new Coffee();

        System.out.println("Preparing Tea..");
        tea.prepareRecipe();

        System.out.println("Preparing Coffee..");
        coffee.prepareRecipe();
    }

    public abstract class CaffeineBeverage {

        final void prepareRecipe() {
            boilWater();
            brew();
            pourInCup();
            addCondiments();
            hook(); //hook method has a default implementation and is optionally for subclasses to override
        }

        abstract void brew();
        abstract void addCondiments();

        void boilWater() {
            System.out.println("Boiling Water");
        }

        void pourInCup() {
            System.out.println("Pouring into cup");
        }

        void hook() {}
    }

    public class Tea extends CaffeineBeverage {

        @Override
        void brew() {
            System.out.println("Stepping the tea");
        }

        @Override
        void addCondiments() {
            System.out.println("Adding Lemon");
        }
    }

    public class Coffee extends CaffeineBeverage {
        @Override
        void brew() {
            System.out.println("Dripping Coffee through filter");
        }

        @Override
        void addCondiments() {
            System.out.println("Adding Sugar and Milk");
        }

        @Override
        void hook() {
            System.out.println("do sth for Coffee");
        }
    }
}
