import java.util.*;

/**
 * The Graph class represents a graph structure with points (nodes) and roads (edges).
 * It supports adding roads, finding the shortest route using Dijkstra's algorithm, and
 * building a barely connected map using a minimum spanning tree approach.
 */
class Graph {
    private Map<String, Point> points = new HashMap<>();
    private Map<String, List<Road>> adjacencyList = new HashMap<>();
    public List<Road> originalRoadsShortestRoute = new ArrayList<>();
    private List<Road> mstOriginalRoads = new ArrayList<>();

    /**
     * Adds a road to the graph. This includes adding the road to the points and adjacency list.
     *
     * @param road The road to be added.
     */
    public void addRoad(Road road) {
        originalRoadsShortestRoute.add(road);
        String startPoint = road.getStartPoint();
        String endPoint = road.getEndPoint();
        points.putIfAbsent(startPoint, new Point(startPoint));
        points.putIfAbsent(endPoint, new Point(endPoint));

        points.get(startPoint).addRoad(road);
        Road reverseRoad = new Road(endPoint, startPoint, road.getDistance(), road.getRoadId());
        points.get(endPoint).addRoad(reverseRoad);

        adjacencyList.computeIfAbsent(startPoint, k -> new ArrayList<>()).add(road);
        adjacencyList.computeIfAbsent(endPoint, k -> new ArrayList<>()).add(reverseRoad);
    }

    /**
     * Builds a barely connected map (minimum spanning tree) starting from the specified point.
     * The resulting roads are sorted by distance and road ID.
     *
     * @param startingPoint The point to start building the MST from.
     * @return A list of original roads that form the MST.
     */
    public List<Road> buildBarelyConnectedMap(String startingPoint) {
        List<Road> result = new ArrayList<>();
        PriorityQueue<Road> edgeQueue = new PriorityQueue<>(
                Comparator.comparingInt(Road::getDistance).thenComparingInt(Road::getRoadId));
        Set<String> inTree = new HashSet<>();

        expandTree(startingPoint, edgeQueue, inTree);

        while (!edgeQueue.isEmpty() && result.size() < points.size() - 1) {
            Road edge = edgeQueue.poll();
            if (!inTree.contains(edge.getEndPoint())) {
                result.add(edge);
                inTree.add(edge.getEndPoint());
                expandTree(edge.getEndPoint(), edgeQueue, inTree);
                Road originalRoad = getOriginalRoadById(edge.getRoadId());
                if (originalRoad != null) {
                    mstOriginalRoads.add(originalRoad);
                }
            }
        }

        mstOriginalRoads.sort(Comparator.comparingInt(Road::getDistance).thenComparingInt(Road::getRoadId));

        return mstOriginalRoads;
    }

    /**
     * Expands the tree by adding edges from the specified point to the priority queue.
     *
     * @param point The point to expand from.
     * @param edgeQueue The priority queue of edges.
     * @param inTree The set of points that are already in the tree.
     */
    private void expandTree(String point, PriorityQueue<Road> edgeQueue, Set<String> inTree) {
        inTree.add(point);
        if (adjacencyList.containsKey(point)) {
            for (Road road : adjacencyList.get(point)) {
                if (!inTree.contains(road.getEndPoint())) {
                    edgeQueue.add(road);
                }
            }
        }
    }

    /**
     * Finds the shortest route from the start point to the end point using Dijkstra's algorithm.
     *
     * @param start The starting point.
     * @param end The ending point.
     * @return A list of roads representing the shortest route.
     */
    public List<Road> findShortestRoute(String start, String end) {
        PriorityQueue<Road> priorityQueue = new PriorityQueue<>(
                Comparator.comparingInt(Road::getDistance).thenComparingInt(Road::getRoadId));
        Map<String, Road> shortestPaths = new HashMap<>();
        Set<String> visitedPoints = new HashSet<>();

        priorityQueue.add(new Road(start, start, 0, -1));

        while (!priorityQueue.isEmpty()) {
            Road currentRoad = priorityQueue.poll();
            String currentPoint = currentRoad.getEndPoint();

            if (!visitedPoints.add(currentPoint)) {
                continue;
            }

            for (Road road : points.get(currentPoint).getConnectedRoads()) {
                if (visitedPoints.contains(road.getEndPoint())) {
                    continue;
                }
                int newDist = shortestPaths.getOrDefault(currentPoint, new Road("", "", 0, -1)).getDistance() + road.getDistance();
                Road prevRoad = shortestPaths.get(road.getEndPoint());
                if (prevRoad == null || newDist < prevRoad.getDistance() ) {
                    shortestPaths.put(road.getEndPoint(), new Road(currentPoint, road.getEndPoint(), newDist, road.getRoadId()));
                    priorityQueue.add(new Road(currentPoint, road.getEndPoint(), newDist, road.getRoadId()));
                }
            }
        }

        return renawPath(shortestPaths, start, end);
    }

    /**
     * Constructs the shortest path from the end point to the start point.
     *
     * @param shortestPaths The map of shortest paths.
     * @param start The starting point.
     * @param end The ending point.
     * @return A list of roads representing the shortest path.
     */
    private List<Road> renawPath(Map<String, Road> shortestPaths, String start, String end) {
        LinkedList<Road> path = new LinkedList<>();
        Road road = shortestPaths.get(end);

        while (road != null && !road.getStartPoint().equals(start)) {
            path.addFirst(road);
            road = shortestPaths.get(road.getStartPoint());
        }

        if (road != null && road.getStartPoint().equals(start)) {
            path.addFirst(road);
        }

        return path;
    }

    /**
     * Calculates the total distance of all roads in the graph.
     *
     * @return The total distance of all roads.
     */
    public int getTotalRoadDistance() {
        Set<String> visited = new HashSet<>();
        int totalDistance = 0;

        for (Point point : points.values()) {
            for (Road road : point.getConnectedRoads()) {
                if (!visited.contains(road.getStartPoint() + road.getEndPoint())) {
                    totalDistance += road.getDistance();
                    visited.add(road.getStartPoint() + road.getEndPoint());
                    visited.add(road.getEndPoint() + road.getStartPoint());
                }
            }
        }

        return totalDistance;
    }

    /**
     * Gets the original road by its ID.
     *
     * @param roadId The ID of the road.
     * @return The original road with the specified ID, or null if not found.
     */
    public Road getOriginalRoadById(int roadId) {
        for (Road road : originalRoadsShortestRoute) {
            if (road.getRoadId() == roadId) {
                return road;
            }
        }
        return null;
    }
}
