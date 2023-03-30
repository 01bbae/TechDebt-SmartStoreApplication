package src.com.cpsc370.store.test;

public class Refrigerated implements Temperature {
    private static Refrigerated instance = null;

    private Refrigerated() {

    }

    public static synchronized Refrigerated getInstance() {
        if (instance == null) {
            instance = new Refrigerated();
        }
        return instance;
    }

    public String toString() {
        return "refrigerated";
    }
}
