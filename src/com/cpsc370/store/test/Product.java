package src.com.cpsc370.store.test;

public class Product {
    // # define product <product_id> name <name> description <description> size
    // <size> category <category> unit_price <unit_price> [temperature (frozen |
    // refrigerated | ambient | warm | hot )]
    private String productID;
    private String name;
    private String description;
    private String size;
    private String category;
    private Double unitPrice;
    private Temperature temp;

    public Product(String productID, String name, String description, String size, String category, Double unitPrice,
            Temperature temp) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.size = size;
        this.category = category;
        this.unitPrice = unitPrice;
        this.temp = temp;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Temperature getTemp() {
        return temp;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

}
