package src.com.cpsc370.store.test;

public class Warm implements Temperature {
    private static Warm instance = null;

    private Warm() {

    }

    public static synchronized Warm getInstance() {
        if (instance == null) {
            instance = new Warm();
        }
        return instance;
    }

    public String toString() {
        return "warm";
    }

}