public class DecoratorDesignPattern {
 //Attaches additional responsibilities to an object dynamically
 //Decorators provide a flexible alternative to subclassing for extending functionality

    public void run(){

        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription()+" $"+beverage.cost());

        Beverage beverage2 = new DarkRoast();
        //wrap beverage2 obj
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription()+" $"+beverage2.cost());

        Beverage beverage3 = new HouseBlend();
        //wrap beverage3 obj
        beverage3 = new Soy(beverage3);
        beverage3 = new Mocha(beverage3);
        beverage3 = new Whip(beverage3);
        System.out.println(beverage3.getDescription()+" $"+beverage3.cost());
    }

    public abstract class Beverage {
        protected String description = "Unknown Beverage";

        public String getDescription() {
            return description;
        }
        public abstract double cost();
    }

    public abstract class CondimentDecorator extends Beverage {
        protected Beverage beverage;
        public abstract String getDescription();
    }

    public class Espresso extends Beverage {

        public Espresso() {
            this.description = "Espresso";
        }
        @Override
        public double cost() {
            return 1.99;
        }
    }

    public class HouseBlend extends Beverage {

        public HouseBlend() {
            this.description = "House Blend Coffee";
        }
        @Override
        public double cost() {
            return .89;
        }
    }

    public class DarkRoast extends Beverage {

        public DarkRoast() {
            this.description = "Dark Roast Coffee";
        }
        @Override
        public double cost() {
            return .99;
        }
    }
    public class Mocha extends CondimentDecorator {

        public Mocha(Beverage beverage) {
            this.beverage = beverage;
        }
        public String getDescription() {
            return this.beverage.getDescription() + ", Mocha";
        }
        public double cost() {
            return this.beverage.cost() + .20;
        }
    }

    public class Soy extends CondimentDecorator {

        public Soy(Beverage beverage) {
            this.beverage = beverage;
        }
        public String getDescription() {
            return this.beverage.getDescription() + ", Soy";
        }
        public double cost() {
            return this.beverage.cost() + .15;
        }
    }

    public class Whip extends CondimentDecorator {

        public Whip(Beverage beverage) {
            this.beverage = beverage;
        }
        public String getDescription() {
            return this.beverage.getDescription() + ", Whip";
        }
        public double cost() {
            return this.beverage.cost() + .1;
        }
    }
}
