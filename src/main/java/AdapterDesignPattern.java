public class AdapterDesignPattern {
    //Converts the interface of a class into another interface the clients expect
    //Adapter let classes work together tha couldn't otherwise because of incompatible interfaces

    public void run() {
        Duck duck = new MallardDuck();
        Turkey turkey = new WildTurkey();
        duck.fly();
        duck.quack();
        turkey.fly();
        turkey.gobble();

        TurkeyAdapter adapter = new TurkeyAdapter(turkey);
        adapter.fly();
        adapter.quack();
    }

    public interface Duck {
        void quack();
        void fly();
    }

    public class MallardDuck implements Duck {

        @Override
        public void quack() {
            System.out.println("Quack");
        }

        @Override
        public void fly() {
            System.out.println("I'm flying");
        }
    }

    public interface Turkey {
        void gobble();
        void fly();
    }

    public class WildTurkey implements Turkey {

        @Override
        public void gobble() {
            System.out.println("Gobble gobble");
        }

        @Override
        public void fly() {
            for(int i = 0; i< 2; i++) {
                System.out.println("I am flying a short distance");
            }
        }
    }

    public class TurkeyAdapter implements Duck {

        Turkey turkey;

        public TurkeyAdapter (Turkey turkey) {
            this.turkey = turkey;
        }

        @Override
        public void quack() {
            turkey.gobble();
        }

        @Override
        public void fly() {
            turkey.fly();
        }
    }
}
