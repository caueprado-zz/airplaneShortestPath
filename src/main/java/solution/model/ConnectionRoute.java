package solution.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConnectionRoute {

    private List<Airport> airports;
    private List<String> visited;
    private Integer routeCost;

    public ConnectionRoute() {
        this.airports = new ArrayList<>();
        this.visited = new ArrayList<>();
        this.routeCost = 0;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public Integer getRouteCost() {
        return routeCost;
    }

    public void addAirport(Airport airport) {
        airports.add(airport);
        visited.add(airport.getLabel());
    }

    public List<String> getVisited() {
        return visited;
    }

    public void setRouteCost(Integer cost) {
        routeCost = cost;
    }

    public void printRoutes() {
        System.out.println("Printing ConnectionRoute");
        getAirports().forEach(airport -> {
                    System.out.println(String.format("Airport : %s "
                            , airport.getLabel()));
                    airport.getConnections().forEach(connection -> {
                        System.out.println(
                                String.format("ConnectionRoute : %s -> %s - cost %s "
                                        , connection.getSource()
                                        , connection.getDestiny()
                                        , connection.getSource()));
                    });
                }
        );
    }

    public static List<Connection> bidirectionalRoutes(List<Connection> connections) {
        List<Connection> bidirectional = new ArrayList<>();
        connections.forEach(connection -> {
            bidirectional.add(new Connection(connection.getDestiny(),
                    connection.getSource(),
                    connection.getCost()));
        });
        return bidirectional;
    }

}
