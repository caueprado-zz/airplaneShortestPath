package solution.strategy;

import org.springframework.stereotype.Component;
import solution.exception.AirportNotFoundException;
import solution.exception.RouteNotFoundException;
import solution.model.Airport;
import solution.model.Connection;
import solution.model.ConnectionRoute;
import solution.utils.Routes;

import java.util.*;

@Component
public class ShortestPath implements Path<ConnectionRoute> {

    private ConnectionRoute routes;
    private List<Airport> airports;
    private List<String> unvisited;
    private List<String> visited;
    private Queue<Airport> airportQueue;

    private boolean found;

    public ShortestPath() {
        initializeSearch();
    }

    @Override
    public ConnectionRoute shortestPath(String source, String destiny)
            throws NullPointerException, AirportNotFoundException, RouteNotFoundException {
        initializeSearch();
        Integer cost = 999;

        airports.forEach(airport -> unvisited.add(airport.getLabel()));

        Airport sourceAirport = Routes.getByLabel(source);
        Airport destinyAirport = null;

        if (sourceAirport == null) throw new AirportNotFoundException();

        routes.addAirport(sourceAirport);
        visited.add(source);

        while (!found) {

            destinyAirport = new Airport();
            for (Connection connection : sourceAirport.getConnections()) {
                if ((cost > connection.getCost() && !found)
                        && !visited.contains(connection.getDestiny().getLabel())) {
                    isDestiny(connection.getDestiny().getLabel(), destiny);
                    destinyAirport = Routes.getByLabel(connection.getDestiny().getLabel());
                    cost = connection.getCost();
                }
            }
            routes.setRouteCost(routes.getRouteCost() + cost);
            routes.addAirport(destinyAirport);

            unvisited.remove(sourceAirport.getLabel());
            visited.add(sourceAirport.getLabel());
            sourceAirport = destinyAirport;

            if (unvisited.size() == 0) throw new RouteNotFoundException();
            if (found) hasArrived(cost);
            else cost = Integer.MAX_VALUE;

        }

        return routes;
    }

    private void hasArrived(Integer cost) {
        routes.getAirports().forEach(airport ->
                System.out.print(String.format(" %s - ", airport.getLabel())));
        System.out.println(String.format(" total cost : %s", cost));
    }

    private boolean isDestiny(String actual, String destiny) {
        if (actual.equals(destiny)) {
            found = true;
        }
        return found;
    }

    public List<String> getVisited() {
        return visited;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void initializeSearch() {
        this.airportQueue = new PriorityQueue<>();
        this.routes = new ConnectionRoute();
        this.airports = Routes.getAirports();
        this.found = false;
        this.visited = new ArrayList<>();
        this.unvisited = new ArrayList<>();
    }

}
