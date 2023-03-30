package src.com.cpsc370.store.test;

public class Medium implements ShelfLevel {
    private static Medium instance = null;

    private Medium() {

    }

    public static synchronized Medium getInstance() {
        if (instance == null) {
            instance = new Medium();
        }
        return instance;
    }

    public String toString() {
        return "medium";
    }
}
