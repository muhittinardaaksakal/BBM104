public class ProcessCommands {
    private String outputFile;

    public ProcessCommands(String outputFile){
        this.outputFile = outputFile;
    }

    public void executeCommands(String[] inputLines) {
        Inventory<Item> inventory = new Inventory<>(outputFile);  // Pass the output file path to Inventory
        for (String line : inputLines) {
            String[] parts = line.split("\t");
            switch (parts[0]) {
                case "ADD":
                    double price = Double.parseDouble(parts[5]);
                    switch (parts[1]) {
                        case "Book":
                            inventory.addItem(new Book(parts[2], parts[3], parts[4], price));
                            break;
                        case "Toy":
                            inventory.addItem(new Toy(parts[2], parts[3], parts[4], price));
                            break;
                        case "Stationery":
                            inventory.addItem(new Stationery(parts[2], parts[3], parts[4], price));
                            break;
                    }
                    break;
                case "SEARCHBYBARCODE":
                    inventory.searchByBarcode(parts[1]);
                    break;
                case "SEARCHBYNAME":
                    inventory.searchByName(parts[1]);
                    break;
                case "REMOVE":
                    inventory.removeItemByBarcode(parts[1]);
                    break;
                case "DISPLAY":
                    inventory.displayItems();
                    break;
            }
        }
    }
}
