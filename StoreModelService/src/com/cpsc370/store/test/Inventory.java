package src.com.cpsc370.store.test;

public class Inventory {
    
    // # define  inventory  <inventory_id>  location  <store_id>:<aisle_number>:<shelf_id> capacity  <capacity>  count  <count>  product  <product_id>

    private String inventoryID;
    private int capacity;
    private int count;
    private String productID;

    public Inventory(String inventoryID, int capacity, int count, String productID){
        this.inventoryID = inventoryID;
        this.capacity = capacity;
        this.count = count;
        this.productID = productID;
    }

    public String getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(String inventoryID) {
        this.inventoryID = inventoryID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
    


}
