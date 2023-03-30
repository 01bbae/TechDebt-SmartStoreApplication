package src.com.cpsc370.store.test;

public class StoreRoom implements AisleLocation {
    private static StoreRoom instance = null;

    private StoreRoom() {

    }

    public static synchronized StoreRoom getInstance() {
        if (instance == null) {
            instance = new StoreRoom();
        }
        return instance;
    }

    public String toString() {
        return "store_room";
    }
}
