package solution.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import solution.exception.AirportNotFoundException;
import solution.exception.RouteNotFoundException;
import solution.file.FileManagerWriter;
import solution.model.Airport;
import solution.model.Connection;
import solution.model.ConnectionRoute;
import solution.resources.request.RouteRequest;
import solution.resources.response.RouteResponse;
import solution.strategy.ShortestPath;
import solution.utils.Routes;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Value("${csv.filename}")
    private static String FILE_NAME;
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteServiceImpl.class);

    @Autowired
    private FileManagerWriter fileManager;
    @Autowired
    private ShortestPath shortestPath;

    @Override
    public List<Airport> getAllRoutes() throws Exception, RouteNotFoundException {
        return Routes.getAirports();
    }

    @Override
    public RouteRequest insertNewRoute(RouteRequest routeRequest) {
        if (routeRequest.getSource() == null || routeRequest.getDestiny() == null ||
                routeRequest.getCost() == null) {
            throw new IllegalArgumentException();
        }
        Connection newConnection = null;
        try {
            newConnection = fileManager.writeToFile("input-file.txt", routeRequest);
            Routes.updateRoute(newConnection);
        } catch (Exception io) {
            LOGGER.error(String.format("%s", io.getMessage()));
        }
        return routeRequest;
    }

    @Override
    public RouteResponse shortestPath(String source, String destiny) throws AirportNotFoundException, RouteNotFoundException {
        ConnectionRoute shortestCon = shortestPath.shortestPath(source, destiny);
        return new RouteResponse(shortestCon.getVisited(),
                shortestCon.getRouteCost());
    }
}
