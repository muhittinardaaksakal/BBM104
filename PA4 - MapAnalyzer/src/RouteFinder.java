import java.util.List;
import java.util.Locale;

/**
 * The RouteFinder class is responsible for managing the graph,
 * finding the shortest routes, constructing the Barely Connected Map,
 * and performing analysis.
 */
public class RouteFinder {
    private Graph graph;
    private String start;
    private String end;
    private String outputFilePath;

    /**
     * Constructs a RouteFinder object and initializes the graph using the given file path.
     *
     * @param filePath The path to the input file containing the graph data.
     * @param outputFilePath The path to the output file where results will be written.
     */
    public RouteFinder(String filePath, String outputFilePath) {
        this.outputFilePath = outputFilePath;
        String[] inputLines = FileInput.readFile(filePath, true, true);
        if (inputLines == null || inputLines.length < 2) {
            throw new IllegalArgumentException("Invalid input file or format.");
        }
        this.graph = new Graph();
        setupGraph(inputLines);
    }

    /**
     * Sets up the graph using the input lines from the file.
     *
     * @param inputLines The lines of input from the file.
     */
    private void setupGraph(String[] inputLines) {
        String[] startEnd = inputLines[0].split("\\t");
        if (startEnd.length != 2) {
            throw new IllegalArgumentException("First line must contain start and end points separated by a tab.");
        }
        this.start = startEnd[0];
        this.end = startEnd[1];

        for (int i = 1; i < inputLines.length; i++) {
            String[] parts = inputLines[i].split("\\t");
            if (parts.length == 4) {
                graph.addRoad(new Road(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3])));
            } else {
                System.out.println("Skipping invalid line: " + inputLines[i]);
            }
        }
    }

    /**
     * Finds and prints the shortest route from start to end using Dijkstra's algorithm.
     *
     * @return The total distance of the shortest route.
     */
    public int findAndPrintShortestRoute() {
        List<Road> shortestRoute = graph.findShortestRoute(start, end);
        StringBuilder sb = new StringBuilder();
        int totalDistance = 0;
        if (shortestRoute != null && !shortestRoute.isEmpty()) {
            totalDistance = shortestRoute.get(shortestRoute.size() - 1).getDistance();
            int previousDistance = 0;
            sb.append("Fastest Route from ").append(start).append(" to ").append(end).append(" (").append(totalDistance).append(" KM):\n");
            for (int i = 0; i < shortestRoute.size(); i++) {
                Road road = shortestRoute.get(i);  // Access the Road object at index i
                Road originalRoad = graph.getOriginalRoadById(road.getRoadId());
                // Calculate the difference in distance from the previous road, except for the first road
                int distanceDifference = (i == 0) ? road.getDistance() : road.getDistance() - previousDistance;

                sb.append(originalRoad.getStartPoint()).append("\t")
                        .append(originalRoad.getEndPoint()).append("\t")
                        .append(distanceDifference).append("\t")  // Use the distance difference here
                        .append(road.getRoadId()).append("\n");

                previousDistance = road.getDistance();  // Update the previousDistance to the current road's distance for the next iteration
            }

        } else {
            sb.append("No path found from ").append(start).append(" to ").append(end).append(".\n");
        }
        FileOutput.writeToFile(outputFilePath, sb.toString(), true, false);
        return totalDistance;
    }

    /**
     * Constructs the Barely Connected Map using Prim's algorithm.
     */
    public void constructBarelyConnectedMap() {
        List<Road> mst = graph.buildBarelyConnectedMap(start);  // Assume 'start' is the starting point for Prim's
        StringBuilder sb = new StringBuilder("Roads of Barely Connected Map is:\n");
        for (int i = 0; i < mst.size(); i++) {
            Road road = mst.get(i);  // Access the Road object at index i
            Road originalRoad = graph.getOriginalRoadById(road.getRoadId());
            sb.append(originalRoad.getStartPoint()).append("\t")
                    .append(originalRoad.getEndPoint()).append("\t")
                    .append(road.getDistance()).append("\t")
                    .append(road.getRoadId()).append("\n");
        }
        FileOutput.writeToFile(outputFilePath, sb.toString(), true, false);
        setupGraphFromMST(mst);
    }

    /**
     * Sets up the graph using the Minimum Spanning Tree (MST) roads.
     *
     * @param mst The list of roads in the MST.
     */
    public void setupGraphFromMST(List<Road> mst) {
        this.graph = new Graph();  // Reset the graph to only use MST roads
        for (Road road : mst) {
            graph.addRoad(road);
        }
    }

    /**
     * Finds and prints the shortest route on the Barely Connected Map.
     *
     * @return The total distance of the shortest route on the Barely Connected Map.
     */
    public int findAndPrintShortestRouteConnectedMap() {
        List<Road> shortestRoute = graph.findShortestRoute(start, end);
        StringBuilder sb = new StringBuilder();
        int totalDistance = 0;
        if (shortestRoute != null && !shortestRoute.isEmpty()) {
            totalDistance = shortestRoute.get(shortestRoute.size() - 1).getDistance();
            int previousDistance = 0;
            sb.append("Fastest Route from ").append(start).append(" to ").append(end).append(" on Barely Connected Map (").append(totalDistance).append(" KM):\n");
            for (int i = 0; i < shortestRoute.size(); i++) {
                Road road = shortestRoute.get(i);  // Access the Road object at index i
                Road originalRoad = graph.getOriginalRoadById(road.getRoadId());

                // Calculate the difference in distance from the previous road, except for the first road
                int distanceDifference = (i == 0) ? road.getDistance() : road.getDistance() - previousDistance;

                sb.append(originalRoad.getStartPoint()).append("\t")
                        .append(originalRoad.getEndPoint()).append("\t")
                        .append(distanceDifference).append("\t")  // Use the distance difference here
                        .append(road.getRoadId()).append("\n");

                previousDistance = road.getDistance();  // Update the previousDistance to the current road's distance for the next iteration
            }

        } else {
            sb.append("No path found from ").append(start).append(" to ").append(end).append(".\n");
        }
        FileOutput.writeToFile(outputFilePath, sb.toString(), true, false);
        return totalDistance;
    }

    /**
     * Performs the analysis by comparing the original map with the Barely Connected Map.
     */
    public void performAnalysis() {
        // Find the shortest route in the original graph
        int originalShortestDistance = findAndPrintShortestRoute();
        int totalOriginalDistance = graph.getTotalRoadDistance();
        constructBarelyConnectedMap();

        int mstShortestDistance = findAndPrintShortestRouteConnectedMap();
        int totalMSTDistance = graph.getTotalRoadDistance();
        double routeRatio = (double) mstShortestDistance / originalShortestDistance;
        double materialRatio = (double) totalMSTDistance / totalOriginalDistance;
        StringBuilder sb = new StringBuilder();
        sb.append("Analysis:\n")
                .append(String.format(Locale.US, "Ratio of Construction Material Usage Between Barely Connected and Original Map: %.2f\n", materialRatio))
                .append(String.format(Locale.US, "Ratio of Fastest Route Between Barely Connected and Original Map: %.2f", routeRatio));

        FileOutput.writeToFile(outputFilePath, sb.toString(), true, false);
    }
}
