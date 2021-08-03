public class FacadeDesignPattern {
    //provides a unified interface to a set of interfaces in a subsystem.
    //Facade defines a higher-level interface that makes the subsystem easier to use.

    public void run() {
        Projector projector = new Projector();
        Screen screen = new Screen();
        PopcornPopper popper = new PopcornPopper();
        HomeTheaterFacade facade = new HomeTheaterFacade(projector,screen,popper);

        String movie = "Lord of the rings";
        facade.WatchMovie(movie);
        facade.endMovie();
    }

    public class HomeTheaterFacade {
        Projector projector;
        Screen screen;
        PopcornPopper popper;

        public HomeTheaterFacade(Projector projector, Screen screen, PopcornPopper popper) {
            this.projector = projector;
            this.screen = screen;
            this.popper = popper;
        }

        public void WatchMovie (String movie) {
            System.out.println("Get ready to watch "+ movie);
            screen.down();
            projector.on();
            projector.wideScreenMode();
            popper.on();
        }

        public void endMovie() {
            System.out.println("Shutting down movie theater ...");
            screen.up();
            projector.off();
            popper.off();
        }
    }

    public class Projector {
        public void on() {}
        public void wideScreenMode() {}
        public void off() {}
    }

    public class Screen {
        public void up() {}
        public void down() {}
    }

    public class PopcornPopper {
        public void on() {}
        public void off() {}
    }
}
