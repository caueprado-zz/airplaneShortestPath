package solution.utils;

import org.springframework.beans.factory.annotation.Autowired;
import solution.model.ConnectionRoute;

public class RouteInstance {

    @Autowired
    private Routes routes;

    private static ConnectionRoute connectionRoute;

    public RouteInstance(ConnectionRoute connectionRoute) {
        this.connectionRoute = connectionRoute;
    }

    public static ConnectionRoute getConnectionRoute() {
        return connectionRoute;
    }
}
