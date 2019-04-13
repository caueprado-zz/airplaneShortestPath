package solution.services;

import solution.exception.AirportNotFoundException;
import solution.exception.RouteNotFoundException;
import solution.model.Airport;
import solution.resources.request.RouteRequest;
import solution.resources.response.RouteResponse;

import java.util.List;


public interface RouteService {

    List<Airport> getAllRoutes() throws Exception;

    RouteRequest insertNewRoute(RouteRequest routeRequest);

    RouteResponse shortestPath(String source, String destiny)
            throws AirportNotFoundException, RouteNotFoundException;
}
