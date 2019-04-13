package solution.resources.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName("Route")
public class RouteResponse {

    private List<String> connections;
    private Integer cost;

    public RouteResponse(
                         @JsonProperty("routes") List<String> connections,
                         @JsonProperty("cost") Integer cost) {
        this.connections = connections;
        this.cost = cost;
    }

    public List<String> getConnections() {
        return connections;
    }

    public void setConnections(List<String> connections) {
        this.connections = connections;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
