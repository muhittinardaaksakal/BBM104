public class Stationery extends Item {
    private String kind;

    public Stationery(String name, String kind, String barcode, double price) {
        super(name, barcode, price);
        this.kind = kind;
    }

    public String getKind() { return kind; }
    @Override
    public String display() {
        return "Kind of the " + getName() +" is " + getKind() + ". Its barcode is " + getBarcode() +" and its price is " + getPrice();

    }
}