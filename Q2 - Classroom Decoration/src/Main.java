import java.util.ArrayList;
import java.util.List;

/**
 * Main class to handle classroom decoration operations.
 */
public class Main {

    private static List<Decoration> decorations = new ArrayList<>();
    private static List<Classroom> classrooms = new ArrayList<>();

    /**
     * Finds a classroom by its name.
     * @param name The name of the classroom to find.
     * @return The found Classroom object or null if not found.
     */
    private static Classroom findClassroomByName(String name) {
        for (Classroom classroom : classrooms) {
            if (classroom.getName().equals(name)) {
                return classroom;
            }
        }
        return null; // Returns null if no matching classroom is found
    }

    /**
     * Finds a decoration by its name.
     * @param name The name of the decoration to find.
     * @return The found Decoration object or null if not found.
     */
    private static Decoration findDecorationByName(String name) {
        for (Decoration decoration : decorations) {
            if (decoration.getName().equals(name)) {
                return decoration;
            }
        }
        return null; // Returns null if no matching decoration is found
    }

    /**
     * Prints the costs of decorating classrooms to a specified output path.
     * @param outputPath The file path where the cost summary will be written.
     */
    private static void printClassroomCosts(String outputPath) {
        StringBuilder sb = new StringBuilder();
        double totalCost = 0.0;

        for (Classroom classroom : classrooms) {
            double cost = classroom.calculateCost();
            totalCost += cost; // Update the total cost

            // Round the cost value up
            int roundedCost = (int) Math.ceil(cost);

            String wallDetails = getWallDetails(classroom);
            String floorDetails = getFloorDetails(classroom);

            sb.append(String.format("Classroom %s used %s and used %s, these costed %dTL.\n",
                    classroom.getName(), wallDetails, floorDetails, roundedCost));
        }

        // Round the total cost up and append it
        int roundedTotalCost = (int) Math.ceil(totalCost);
        sb.append(String.format("Total price is: %dTL.", roundedTotalCost));

        // Write the StringBuilder content to file
        FileOutput.writeToFile(outputPath, sb.toString(), false, false);
    }

    /**
     * Generates details about the wall decoration of a classroom.
     * @param classroom The Classroom object for which wall details are generated.
     * @return A string describing the wall decoration details.
     */
    private static String getWallDetails(Classroom classroom) {
        Decoration wallDecoration = classroom.getWallDecoration();
        double wallArea = classroom.getWallArea();

        if (wallDecoration instanceof Tile) {
            Tile tile = (Tile)wallDecoration; // Cast to Tile type
            int tileCount = tile.calculateTilesNeeded(wallArea);
            return tileCount + " Tiles for walls";
        } else if (wallDecoration instanceof Paint) {
            // For Paint type, perhaps a special message format
            return String.format("%.0fm2 of Paint for walls", Math.ceil(classroom.getWallArea()));
        } else if (wallDecoration instanceof Wallpaper) {
            // For Wallpaper type, perhaps another special message format
            return String.format("%.0fm2 of Wallpaper for walls", Math.ceil(classroom.getWallArea()));
        } else {
            // A general format for all other Decoration types
            // This can be used for any decoration type not previously defined.
            return String.format("%.0fm2 of %s for walls", wallArea, wallDecoration.getName());
        }
    }

    /**
     * Generates details about the floor decoration of a classroom.
     * @param classroom The Classroom object for which floor details are generated.
     * @return A string describing the floor decoration details.
     */
    private static String getFloorDetails(Classroom classroom) {
        Decoration floorDecoration = classroom.getFloorDecoration();
        double floorArea = classroom.getFloorArea();

        if (floorDecoration instanceof Tile) {
            Tile tile = (Tile) floorDecoration; // Cast to Tile type
            int tileCount = tile.calculateTilesNeeded(floorArea);
            return tileCount + " Tiles for flooring";
        } else if (floorDecoration instanceof Paint) {
            // For Paint type, rounding is not necessary, use area directly
            return String.format("%.0fm2 of Paint for flooring", floorArea);
        } else {
            // A general format for all other Decoration types
            // For example, Wallpaper or any special decoration type
            return String.format("%.0fm2 of %s for flooring", floorArea, floorDecoration.getName());
        }
    }

    /**
     * Main method to run the decoration cost calculation program.
     * @param args Command line arguments providing file paths for classroom and decoration details, and the output path for the cost summary.
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the file path as a command line argument.");
            return;
        }

        String items = args[0];
        String[] itemlines = FileInput.readFile(items, true, true);
        String decorate = args[1];
        String[] decoratelines = FileInput.readFile(decorate, true, true);
        String outputPath = args[2];

        if (itemlines == null) {
            System.out.println("Error reading file.");
            return;
        }

        for (String line : itemlines) {
            String[] parts = line.split("\t"); // Assumes tab (\t) is the delimiter

            try {
                if (parts[0].equals("CLASSROOM")) {
                    if(parts.length < 6) {
                        continue;
                    }
                    // Parse classroom details and create classroom objects
                    ClassroomBuilder builder = new ClassroomBuilder()
                            .setName(parts[1])
                            .setShape(parts[2])
                            .setWidth(Double.parseDouble(parts[3]))
                            .setLength(Double.parseDouble(parts[4]))
                            .setHeight(Double.parseDouble(parts[5]));
                    Classroom classroom = builder.build();
                    if(classroom == null) {
                        System.out.println("Classroom object not created due to invalid input: " + line);
                        continue;
                    }
                    classrooms.add(classroom);

                } else if (parts[0].equals("DECORATION")) {
                    if(parts.length < 4) {
                        continue;
                    }
                    // Parse decoration details and create decoration objects
                    Decoration decoration = null;
                    double price = Double.parseDouble(parts[3]);
                    switch (parts[2]) {
                        case "Paint":
                            decoration = new Paint(parts[1],price);
                            break;
                        case "Tile":
                            decoration = new Tile(parts[1],price, Double.parseDouble(parts[4])); // Example, assuming 1 square meter per tile
                            break;
                        case "Wallpaper":
                            decoration = new Wallpaper(parts[1],price); // Assuming cost is per square meter
                            break;
                        default:
                            System.out.println("Unsupported decoration type: " + parts[2]);
                            continue;
                    }
                    if(decoration == null) {
                        continue;
                    }
                    decorations.add(decoration);

                }
            } catch (Exception e) {
                e.printStackTrace(); // Print the stack trace for any exception
            }
        }
        for (String decorateline : decoratelines) {
            String[] parts = decorateline.split("\t");
            if (parts.length < 3) {
                System.out.println("Skipping line due to missing information: " + decorateline);
                continue; // Skip this line if missing information
            }
            String classroomName = parts[0];
            String wallDecorationName = parts[1];
            String floorDecorationName = parts[2];

            // Find the classroom and decorations by their names
            Classroom classroom = findClassroomByName(classroomName);
            Decoration wallDecoration = findDecorationByName(wallDecorationName);
            Decoration floorDecoration = findDecorationByName(floorDecorationName);

            // Assign the found decorations to the classroom
            if (classroom != null && wallDecoration != null && floorDecoration != null) {
                classroom.setWallDecoration(wallDecoration);
                classroom.setFloorDecoration(floorDecoration);

            } else {
                // Print an error message if any decoration or classroom is not found
                System.out.println("Failed to set decorations for classroom: " + classroomName);
            }
        }
        printClassroomCosts(outputPath);
    }
}
