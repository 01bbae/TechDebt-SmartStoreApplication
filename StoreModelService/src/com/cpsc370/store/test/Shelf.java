package src.com.cpsc370.store.test;

import java.util.HashMap;

public class Shelf {
    // # define shelf <store_id>:<aisle_number>:<shelf_id> name <name> level (high |
    // medium | low) description <description> [temperature (frozen | refrigerated |
    // ambient | warm | hot )]
    private String shelfID;
    private String name;
    private ShelfLevel level;
    private String description;
    private Temperature temp;
    private HashMap<String, Integer> products = new HashMap<>();

    public Shelf(String shelfID, String name, ShelfLevel level, String description, Temperature temp) {
        this.shelfID = shelfID;
        this.name = name;
        this.level = level;
        this.description = description;
        this.temp = temp;
    }

    public String getShelfID() {
        return shelfID;
    }

    public void setShelfID(String shelfID) {
        this.shelfID = shelfID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShelfLevel getLevel() {
        return level;
    }

    public void setLevel(ShelfLevel level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Temperature getTemp() {
        return temp;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

}
