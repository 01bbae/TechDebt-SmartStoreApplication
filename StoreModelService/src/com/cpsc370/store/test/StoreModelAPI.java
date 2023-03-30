package src.com.cpsc370.store.test;

import java.util.HashMap;

import src.com.cpsc370.store.test.util.CommandException;

public class StoreModelAPI {
    private static StoreModelAPI instance = null;
    private HashMap<String, Store> Stores = new HashMap<>();
    private HashMap<String, Product> Products = new HashMap<>();

    private StoreModelAPI() {
        // constructor

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
        Products.put(productID, product);
        return product;
    }

    public Product showProduct(String productID, Object o){
        return Products.get(productID);
    }
    
    public Inventory provisionInventory(String inventoryID, String storeID, String aisleID, String shelfID, int capacity, int count, String productID, Object o) throws CommandException {
        Inventory inventory = new Inventory(inventoryID, capacity, count, productID);
        Store store = Stores.get(storeID);
        Aisle aisle = store.getAisles().get(aisleID);
        Shelf shelf = aisle.getShelves().get(shelfID);
        shelf.addInventory(inventory);

        return inventory;
    }

    // TODO: Not Completed/Figure out where Inventory data should be stored
    public Inventory showInventory(String inventoryID, Object o) throws CommandException {
        Inventory inventory = new Inventory(inventoryID, capacity, count, productID);
        Store store = Stores.get(storeID);
        Aisle aisle = store.getAisles().get(aisleID);
        Shelf shelf = aisle.getShelves().get(shelfID);
        shelf.addInventory(inventory);

        return inventory;
    }

    public void updateInventory(String inventoryID, int updateCount){
        Inventory inventory;
        
    }
    
    public Customer provisionCustomer(String customerID, String firstName, String lastName, AccountType account, String email, String accountAddress, Object o){
        
    }


}
