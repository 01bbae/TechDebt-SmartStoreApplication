package src.com.cpsc370.store.test;

public class Floor implements AisleLocation {
    private static Floor instance = null;

    private Floor() {

    }

    public static synchronized Floor getInstance() {
        if (instance == null) {
            instance = new Floor();
        }
        return instance;
    }

    public String toString() {
        return "floor";
    }
}
