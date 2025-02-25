/**
 * The MapAnalyzer class is the entry point for analyzing the map.
 * It reads input and output file paths from the command line arguments
 * and performs the analysis on the provided map data.
 */
public class MapAnalyzer {
    /**
     * The main method that starts the analysis process.
     *
     * @param args Command line arguments where args[0] is the input file path
     *             and args[1] is the output file path.
     */
    public static void main(String[] args) {
        RouteFinder routeFinder = new RouteFinder(args[0], args[1]);
        routeFinder.performAnalysis();
    }
}
