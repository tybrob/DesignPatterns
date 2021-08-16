import java.util.Random;

public class StateDesignPattern {
    //allows an object to alter its behavior when its internal state changes.
    //The object will appear to change its class.

    void run() {
        GumballMachine gumballMachine = new GumballMachine(4);

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();

    }

    public class GumballMachine {

        State soldOutState;
        State noQuarterState;
        State hasQuarterState;
        State soldState;
        State winnerState;

        State state;
        int count;

        public GumballMachine(int numberGumballs) {
            soldOutState = new SoldOutState(this);
            noQuarterState = new NoQuarterState(this);
            hasQuarterState = new HasQuarterState(this);
            soldState = new SoldState(this);
            winnerState = new WinnerState(this);

            this.count = numberGumballs;

            if (numberGumballs > 0) {
                state = noQuarterState;
            } else {
                state = soldOutState;
            }
        }

        public void insertQuarter() {
            state.insertQuarter();
        }

        public void ejectQuarter() {
            state.ejectQuarter();
        }

        public void turnCrank() {
            state.turnCrank();
            state.dispense();
        }

        void setState(State state) {
            this.state = state;
        }

        void releaseBall() {
            System.out.println("A gumball comes rolling out the slot...");
            if (count > 0) {
                count --;
            }
        }

        int getCount() {
            return count;
        }

        public State getState() {
            return state;
        }

        public State getSoldOutState() {
            return soldOutState;
        }

        public State getNoQuarterState() {
            return noQuarterState;
        }

        public State getHasQuarterState() {
            return hasQuarterState;
        }

        public State getSoldState() {
            return soldState;
        }

        public State getWinnerState() {
            return winnerState;
        }
    }

    public interface State {

        void insertQuarter();
        void ejectQuarter();
        void turnCrank();
        void dispense();
    }

    public class HasQuarterState implements State {
        Random randomWinner = new Random(System.currentTimeMillis());
        GumballMachine gumballMachine;

        public HasQuarterState(GumballMachine gumballMachine) {
            this.gumballMachine = gumballMachine;
        }

        public void insertQuarter() {
            System.out.println("You can't insert another quarter");
        }

        public void ejectQuarter() {
            System.out.println("Quarter returned");
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        }

        public void turnCrank() {
            System.out.println("You turned...");
            int winner = randomWinner.nextInt(10);
            if ((winner == 0) && (gumballMachine.getCount() > 1)) {
                gumballMachine.setState(gumballMachine.getWinnerState());
            } else {
                gumballMachine.setState(gumballMachine.getSoldState());
            }
        }

        public void dispense() {
            System.out.println("No gumball dispensed");
        }
    }

    public class NoQuarterState implements State {
        GumballMachine gumballMachine;

        public NoQuarterState(GumballMachine gumballMachine) {
            this.gumballMachine = gumballMachine;
        }

        public void insertQuarter() {
            System.out.println("You inserted a quarter");
            gumballMachine.setState(gumballMachine.getHasQuarterState());
        }

        public void ejectQuarter() {
            System.out.println("You haven't inserted a quarter");
        }

        public void turnCrank() {
            System.out.println("You turned, but there's no quarter");
        }

        public void dispense() {
            System.out.println("You need to pay first");
        }
    }

    public class SoldOutState implements State {
        GumballMachine gumballMachine;

        public SoldOutState(GumballMachine gumballMachine) {
            this.gumballMachine = gumballMachine;
        }

        public void insertQuarter() {
            System.out.println("You can't insert a quarter, the machine is sold out");
        }

        public void ejectQuarter() {
            System.out.println("You can't eject, you haven't inserted a quarter yet");
        }

        public void turnCrank() {
            System.out.println("You turned, but there are no gumballs");
        }

        public void dispense() {
            System.out.println("No gumball dispensed");
        }
    }

    public class WinnerState implements State {
        GumballMachine gumballMachine;

        public WinnerState(GumballMachine gumballMachine) {
            this.gumballMachine = gumballMachine;
        }

        public void insertQuarter() {
            System.out.println("Please wait, we're already giving you a Gumball");
        }

        public void ejectQuarter() {
            System.out.println("Please wait, we're already giving you a Gumball");
        }

        public void turnCrank() {
            System.out.println("Turning again doesn't get you another gumball!");
        }

        public void dispense() {
            gumballMachine.releaseBall();
            if (gumballMachine.getCount() == 0) {
                gumballMachine.setState(gumballMachine.getSoldOutState());
            } else {
                gumballMachine.releaseBall();
                System.out.println("YOU'RE A WINNER! You got two gumballs for your quarter");
                if (gumballMachine.getCount() > 0) {
                    gumballMachine.setState(gumballMachine.getNoQuarterState());
                } else {
                    System.out.println("Oops, out of gumballs!");
                    gumballMachine.setState(gumballMachine.getSoldOutState());
                }
            }
        }
    }

    public class SoldState implements State {
        GumballMachine gumballMachine;

        public SoldState(GumballMachine gumballMachine) {
            this.gumballMachine = gumballMachine;
        }

        public void insertQuarter() {
            System.out.println("Please wait, we're already giving you a gumball");
        }

        public void ejectQuarter() {
            System.out.println("Sorry, you already turned the crank");
        }

        public void turnCrank() {
            System.out.println("Turning twice doesn't get you another gumball!");
        }

        public void dispense() {
            gumballMachine.releaseBall();
            if (gumballMachine.getCount() > 0) {
                gumballMachine.setState(gumballMachine.getNoQuarterState());
            } else {
                System.out.println("Oops, out of gumballs!");
                gumballMachine.setState(gumballMachine.getSoldOutState());
            }
        }
    }
}