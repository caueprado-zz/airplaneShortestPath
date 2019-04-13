package solution.strategy;

import solution.exception.AirportNotFoundException;
import solution.exception.RouteNotFoundException;

public interface Path<T> {

        T shortestPath(String source, String destiny) throws AirportNotFoundException, RouteNotFoundException;
}
