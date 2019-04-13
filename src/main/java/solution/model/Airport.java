package solution.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Airport {

    private String label;
    private List<Connection> connections;

    public Airport() {}

    public Airport(String label) {
        this.label = label;
        this.connections = new ArrayList<>();
    }

    public void addConnection(Connection connection) {
        connections.add(connection);
    }

    public String getLabel() {
        return label;
    }

    public List<Connection> getConnections() {
        return connections;
    }
}
