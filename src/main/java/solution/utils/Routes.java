package solution.utils;

import org.springframework.stereotype.Component;
import solution.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Routes {

    private static ConnectionRoute route;
    private static List<Connection> connections;
    private static List<Airport> airports;

    private Routes() {
        connections = new ArrayList<>();
        airports = new ArrayList<>();
        route = new ConnectionRoute();
    }

    public static void initializeRoutes(List<Connection> connections, List<Airport> airports) {
        List<Airport> airportsList = new ArrayList<>();
        connections.addAll(ConnectionRoute.bidirectionalRoutes(connections));
        for (Airport airport : airports) {
            Airport newAirport = new Airport();
            for (Connection connection : connections) {
                String airportName = airport.getLabel();
                if (airportName.equals(connection.getSource().getLabel())) {
                    newAirport = airport;
                    newAirport.addConnection(connection);
                }
            }
            airportsList.add(newAirport);
        }
        airports.clear();
        getAirports().addAll(airportsList);
        getConnections().addAll(connections);
        getRoute().setAirports(airports);
    }

    public static void updateRoute(Connection newConnection) {
        Airport destiny = getByLabel(newConnection.getDestiny().getLabel());
        Airport source = getByLabel(newConnection.getSource().getLabel());

        if (destiny == null) {
            destiny = new Airport(newConnection.getDestiny().getLabel());
        } else {
            airports.remove(destiny);
        }

        if (source == null) {
            source = new Airport(newConnection.getSource().getLabel());
        } else {
            airports.remove(source);
        }

        source.addConnection(newConnection);
        destiny.addConnection(new Connection(newConnection.getDestiny()
                , newConnection.getSource()
                , newConnection.getCost()));
        airports.add(source);
        airports.add(destiny);
    }

    public static Airport getByLabel(String label) {
        Optional<Airport> getAirport = airports.stream()
                .filter(airport -> airport.getLabel().equals(label))
                .findFirst();

        return getAirport.isPresent() ? getAirport.get() : null;
    }

    public static ConnectionRoute getRoute() {
        return route;
    }

    public static List<Connection> getConnections() {
        return connections;
    }

    public static List<Airport> getAirports() {
        return airports;
    }
}
