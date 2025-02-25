public abstract class Item {
    private String name;
    private String barcode;
    private double price;

    public Item(String name, String barcode, double price) {
        this.name = name;
        this.barcode = barcode;
        this.price = price;
    }

    // Getters and setters
    public String getName() { return name; }
    public String getBarcode() { return barcode; }
    public double getPrice() { return price; }
    abstract String display();
}