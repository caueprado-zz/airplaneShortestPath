package solution.model;

import org.springframework.stereotype.Component;

@Component
public class Connection {
    private Airport source;
    private Airport destiny;
    private Integer cost;

    public Connection() {}

    public Connection(Airport source, Airport destiny, Integer cost) {
        this.source = source;
        this.destiny = destiny;
        this.cost = cost;
    }

    public Airport getSource() {
        return this.source;
    }

    public void setSource(Airport source) {
        this.source = source;
    }

    public Airport getDestiny() {
        return destiny;
    }

    public void setDestiny(Airport destiny) {
        this.destiny = destiny;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
