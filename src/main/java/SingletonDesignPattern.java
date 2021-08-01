public class SingletonDesignPattern {
    //ensures a class has only one instance and provides a global point to access it

    private static SingletonDesignPattern uniqueInstance;

    private SingletonDesignPattern() {};

    public static synchronized SingletonDesignPattern getInstance() {
        if (uniqueInstance == null){
            uniqueInstance = new SingletonDesignPattern();
        }
        return uniqueInstance;
    }

    //singleton in easily implemented with enums
    enum Singleton {
        UNIQUE_INSTANCE;
    }
    Singleton s = Singleton.UNIQUE_INSTANCE;
}
