package contentbasedrecommedation;

import java.util.List;

public class Product {
    private int id;
    private String name;
    private String category;
    private String brand;
    private String demand;
    private float price;
    private float discount;
    private String rams[];
    private String roms[];
    
    public Product(int id, String name, String category, String brand, String demand, float price, float discount, String rams[], String roms[]) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.demand = demand;
        this.discount = discount;
        this.price = price;
        this.discount = discount;
        this.rams = rams;
        this.roms = roms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String[] getRams() {
        return rams;
    }

    public void setRams(String[] rams) {
        this.rams = rams;
    }

    public String[] getRoms() {
        return roms;
    }

    public void setRoms(String[] roms) {
        this.roms = roms;
    }

    

    
    
    
}
