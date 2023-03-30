package src.com.cpsc370.store.test;

public class Hot implements Temperature {
    private static Hot instance = null;

    private Hot() {

    }

    public static synchronized Hot getInstance() {
        if (instance == null) {
            instance = new Hot();
        }
        return instance;
    }

    public String toString() {
        return "hot";
    }
}