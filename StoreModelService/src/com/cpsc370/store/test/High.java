package src.com.cpsc370.store.test;

public class High implements ShelfLevel {
    private static High instance = null;

    private High() {

    }

    public static synchronized High getInstance() {
        if (instance == null) {
            instance = new High();
        }
        return instance;
    }

    public String toString() {
        return "high";
    }
}
