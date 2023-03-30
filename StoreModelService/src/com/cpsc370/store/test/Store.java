package src.com.cpsc370.store.test;

import java.util.HashMap;

import src.com.cpsc370.store.test.util.CommandException;

public class Store {
    private HashMap<String, Aisle> aisles = new HashMap<>();
    private String id;
    private String name;
    private String address;

    public Store(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public void addAisle(Aisle aisle) throws CommandException {
        // check if aisle already exists
        if (aisles.get(aisle.getAisleID()) == null) {
            aisles.put(aisle.getAisleID(), aisle);
        } else {
            throw new CommandException("Store.addAisle", "Aisle " + aisle.getAisleID() + " Already Exists");
        }
    }

    public HashMap<String, Aisle> getAisles() {
        return this.aisles;
    }

    public String getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAisles(HashMap<String, Aisle> aisles) {
        this.aisles = aisles;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
