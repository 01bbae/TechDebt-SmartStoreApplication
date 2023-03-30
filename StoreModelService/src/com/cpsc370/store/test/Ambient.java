package src.com.cpsc370.store.test;

public class Ambient implements Temperature {
    private static Ambient instance = null;

    private Ambient() {

    }

    public static synchronized Ambient getInstance() {
        if (instance == null) {
            instance = new Ambient();
        }
        return instance;
    }

    public String toString() {
        return "ambient";
    }
}
