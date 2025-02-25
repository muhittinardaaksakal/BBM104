import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Inventory<T extends Item> {
    private List<T> itemsList = new ArrayList<>();
    private String outputPath;  // Instance variable for file output path

    public Inventory(String outputPath) {
        this.outputPath = outputPath;  // Initialize the output path
    }

    public void addItem(T item) {
        itemsList.add(item);
    }

    public void displayItems() {
        StringBuilder output = new StringBuilder("INVENTORY:\n");
        itemsList.stream()
                .filter(item -> item instanceof Book)
                .forEach(item -> output.append(item.display()).append("\n"));
        itemsList.stream()
                .filter(item -> item instanceof Toy)
                .forEach(item -> output.append(item.display()).append("\n"));
        itemsList.stream()
                .filter(item -> item instanceof Stationery)
                .forEach(item -> output.append(item.display()).append("\n"));
        output.append("------------------------------\n");
        FileOutput.writeToFile(this.outputPath, output.toString(), true, false);
    }

    public void removeItemByBarcode(String barcode) {
        StringBuilder output = new StringBuilder("REMOVE RESULTS:\n");
        Iterator<T> iterator = itemsList.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            T item = iterator.next();
            if (item.getBarcode().equals(barcode)) {
                iterator.remove();
                output.append("Item is removed.\n");
                found = true;
                break;
            }
        }
        if (!found) {
            output.append("Item is not found.\n");
        }
        output.append("------------------------------\n");
        FileOutput.writeToFile(this.outputPath, output.toString(), true, false);
    }

    public void searchByBarcode(String barcode) {
        StringBuilder output = new StringBuilder("SEARCH RESULTS:\n");
        boolean found = false;
        for (T item : itemsList) {
            if (item.getBarcode().equals(barcode)) {
                output.append(item.display()).append("\n");
                found = true;
                break;
            }
        }
        if (!found) {
            output.append("Item is not found.\n");
        }
        output.append("------------------------------\n");
        FileOutput.writeToFile(this.outputPath, output.toString(), true, false);
    }

    public void searchByName(String name) {
        StringBuilder output = new StringBuilder("SEARCH RESULTS:\n");
        boolean found = false;
        for (T item : itemsList) {
            if (item.getName().equalsIgnoreCase(name)) {
                output.append(item.display()).append("\n");
                found = true;
            }
        }
        if (!found) {
            output.append("Item is not found.\n");
        }
        output.append("------------------------------\n");
        FileOutput.writeToFile(this.outputPath, output.toString(), true, false);
    }
}
