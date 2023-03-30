package src.com.cpsc370.store.test;

public class Frozen implements Temperature {
    private static Frozen instance = null;

    private Frozen() {

    }

    public static synchronized Frozen getInstance() {
        if (instance == null) {
            instance = new Frozen();
        }
        return instance;
    }

    public String toString() {
        return "frozen";
    }

}
