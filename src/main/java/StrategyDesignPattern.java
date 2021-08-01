public class StrategyDesignPattern {
//Defines a family of algorithms, encapsulates each one, and makes them interchangeable
//Strategy lets the algorithm vary independently from clients that use it

    public void run(){

        Duck mall = new MallardDuck();
        mall.performQuack();
        mall.performFly();
    }

    public abstract class Duck {

        FlyBehavior flyBehavior;
        QuackBehavior quackBehavior;

        public Duck() {}

        public abstract void display();

        public void performFly() {
            flyBehavior.fly();
        }

        public void performQuack() {
            quackBehavior.quack();
        }

        public void setFlyBehavior(FlyBehavior fb) {
            this.flyBehavior = fb;
        }

        public void setQuackBehavior(QuackBehavior qb) {
            this.quackBehavior = qb;
        }

        public void swim() {
            System.out.println("All ducks float");
        }
    }

    public interface FlyBehavior {
        public void fly();
    }

    public class FlyWithWings implements FlyBehavior {
        @Override
        public void fly() {
            System.out.println("I'm flying!!");
        }
    }

    public class FlyNoWay implements FlyBehavior {
        @Override
        public void fly() {
            System.out.println("I can't fly");
        }
    }

    public interface QuackBehavior {
        public void quack();
    }

    public class Quack implements QuackBehavior {
        @Override
        public void quack() {
            System.out.println("Quack");
        }
    }

    public class MuteQuack implements QuackBehavior {
        @Override
        public void quack() {
            System.out.println("Silence");
        }
    }

    public class Squeak implements QuackBehavior {
        @Override
        public void quack() {
            System.out.println("Squeak");
        }
    }

    public class MallardDuck extends Duck {
        public MallardDuck() {
            quackBehavior = new Quack();
            flyBehavior = new FlyWithWings();
        }
        public void display() {
            System.out.println("I'm a real Mallard duck");
        }
    }
}

