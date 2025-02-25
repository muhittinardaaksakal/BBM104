public class Book extends Item {
    private String author;

    public Book(String name, String author, String barcode, double price) {
        super(name, barcode, price);
        this.author = author;
    }

    @Override
    public String display() {
        return "Author of the " + getName() +" is " +author + ". Its barcode is " + getBarcode() +" and its price is " + getPrice();

    }
}

// Similar changes for Toy and Stationery classes
