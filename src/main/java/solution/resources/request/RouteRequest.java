package solution.resources.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RouteRequest {

    private String source;
    private String destiny;
    private Integer cost;

    public RouteRequest(@JsonProperty("source") String source,
                        @JsonProperty("destiny") String destiny,
                        @JsonProperty("cost") Integer cost) {
        this.source = source;
        this.destiny = destiny;
        this.cost = cost;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
