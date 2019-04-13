package solution.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RouteNotFoundException extends Exception {
    public RouteNotFoundException() {
        super("Route not found");
    }
}
