package solution.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import solution.services.RouteService;
import solution.utils.Routes;

import java.util.Scanner;

@Order(2)
@Component
public class ConsoleCommandRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleCommandRunner.class);

    @Autowired
    private RouteService routeService;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Running ConsoleCommandRunner");
        Scanner sc = new Scanner(System.in);
        String[] str;
        while (true) {
            System.out.println("Please enter the connection (SOURCE-DESTINY): ");

            if (sc.hasNext()) {
                str = sc.next().split("-");

                if (str.length < 2) {
                    System.out.println("Please enter in format SOURCE-DESTINY ");
                } else {
                    routeService.shortestPath(str[0],str[1]);
                }

                sc.nextLine();
            }
        }
    }

}
