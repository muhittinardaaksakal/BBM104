public class Toy extends Item {
    private String color;

    public Toy(String name, String color, String barcode, double price) {
        super(name, barcode, price);
        this.color = color;
    }

    public String getColor() { return color; }
    @Override
    public String display() {
        return "Color of the " + getName() +" is " + color + ". Its barcode is " + getBarcode() +" and its price is " + getPrice();

    }
}