package solution.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solution.model.Airport;
import solution.resources.request.RouteRequest;
import solution.resources.response.RouteResponse;
import solution.services.RouteService;

import java.util.List;

@RequestMapping(path = "api/route")
@RestController
public class RouteAPI {

    @Autowired
    private RouteService routeService;

    @GetMapping(path = "/{source}/{destiny}")
    public ResponseEntity<RouteResponse> getBestRoute(@PathVariable("source") String source,
                                      @PathVariable("destiny") String destiny) throws Exception {
        RouteResponse RouteResponse = routeService.shortestPath(source,destiny);
        return new ResponseEntity<>(RouteResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/getAll")
    public List<Airport> getAllRoutes() throws Exception {
        return routeService.getAllRoutes();
    }

    @PostMapping
    public ResponseEntity<RouteRequest> addNewRoute(@RequestBody RouteRequest routeRequest) {
        RouteRequest route = routeService.insertNewRoute(routeRequest);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }
}
