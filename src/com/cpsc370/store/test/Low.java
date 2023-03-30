package src.com.cpsc370.store.test;

public class Low implements ShelfLevel {
    private static Low instance = null;

    private Low() {

    }

    public static synchronized Low getInstance() {
        if (instance == null) {
            instance = new Low();
        }
        return instance;
    }

    public String toString() {
        return "low";
    }
}
