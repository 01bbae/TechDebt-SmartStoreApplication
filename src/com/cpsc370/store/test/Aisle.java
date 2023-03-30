package src.com.cpsc370.store.test;

import java.util.HashMap;

import src.com.cpsc370.store.test.util.CommandException;

public class Aisle {
    private String aisleID;
    private String name;
    private String description;
    private AisleLocation location;
    private HashMap<String, Shelf> shelves = new HashMap<>();

    public Aisle(String aisleID, String name, String description, AisleLocation location) {
        this.aisleID = aisleID;
        this.name = name;
        this.description = description;
        this.location = location;
    }

    public void addShelf(Shelf shelf) throws CommandException {
        if (shelves.get(shelf.getShelfID()) == null) {
            shelves.put(shelf.getShelfID(), shelf);
        } else {
            throw new CommandException("Aisle.addShelf", "Shelf " + shelf.getShelfID() + " Already Exists");
        }
    }

    public String getLocationType() {
        return location.toString();
    }

    public String getAisleID() {
        return aisleID;
    }

    public void setAisleID(String aisleID) {
        this.aisleID = aisleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AisleLocation getLocation() {
        return location;
    }

    public void setLocation(AisleLocation location) {
        this.location = location;
    }

    public HashMap<String, Shelf> getShelves() {
        return shelves;
    }

    public void setShelves(HashMap<String, Shelf> shelves) {
        this.shelves = shelves;
    }

}
