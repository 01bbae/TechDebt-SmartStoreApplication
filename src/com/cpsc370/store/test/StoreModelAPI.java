package src.com.cpsc370.store.test;

import java.util.HashMap;

import src.com.cpsc370.store.test.util.CommandException;

public class StoreModelAPI {
    private static StoreModelAPI instance = null;
    private HashMap<String, Store> Stores;

    private StoreModelAPI() {
        // constructor
        Stores = new HashMap<String, Store>();
    }

    public static synchronized StoreModelAPI getInstance() {
        if (instance == null)
            instance = new StoreModelAPI();

        return instance;
    }

    public Store provisionStore(String storeID, String name, String address, Object o) {
        Store store = new Store(storeID, name, address);
        Stores.put(storeID, store);
        return store;
    }

    public String showStore(String storeID, Object o) {
        Store store = Stores.get(storeID);
        return (store.getID() + " " + store.getName() + " " + store.getAddress());
    }

    public Aisle provisionAisle(String storeID, String aisleID, String name, String description,
            AisleLocation location,
            Object o) throws CommandException {
        Aisle aisle = new Aisle(aisleID, name, description, location);
        System.out.println("Just created Aisle " + aisle.getAisleID() + " with location " + location.toString());
        Stores.get(storeID).addAisle(aisle);
        return aisle;

    }

    public String showAisle(String storeID, String aisleID, Object o) {
        Store store = Stores.get(storeID);
        Aisle aisle = store.getAisles().get(aisleID);
        return (aisle.getAisleID() + " " + aisle.getName() + " " + aisle.getDescription() + " "
                + aisle.getLocationType());
    }

    public Shelf provisionShelf(String storeID, String aisleID, String shelfID, String name, ShelfLevel level,
            String description, Temperature temp, Object o) throws CommandException {
        Store store = Stores.get(storeID);
        Aisle aisle = store.getAisles().get(aisleID);
        Shelf shelf = new Shelf(shelfID, name, level, description, temp);
        aisle.addShelf(shelf);
        return shelf;
    }

    public String showShelf(String storeID, String aisleID, String shelfID, Object o) {
        Store store = Stores.get(storeID);
        Aisle aisle = store.getAisles().get(aisleID);
        Shelf shelf = aisle.getShelves().get(shelfID);
        return (shelf.getShelfID() + " " + shelf.getName() + " " + shelf.getLevel() + " " + shelf.getDescription() + " "
                + shelf.getTemp());
    }

    public Product provisionProduct(String productID, String name, String description, String size, String category,
            Double unitPrice, Temperature temp, Object o) {
        Product product = new Product(productID, name, description, size, category, unitPrice, temp);
        return product;
    }

    public String showProduct()

}
