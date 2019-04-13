package solution.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import solution.model.Airport;
import solution.model.Connection;
import solution.resources.request.RouteRequest;
import solution.utils.Routes;

import java.io.*;
import java.net.URL;

@Component
public class FileManagerWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileManagerReader.class);

    private File getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        File file = null;
        try {
            file = new File(classLoader.getResource(fileName).getFile());
        } catch (NullPointerException np) {
            LOGGER.error(np.getMessage());
        }

        return file;
    }

    public Connection writeToFile(String fileName, RouteRequest routeRequest) throws IOException {
        File file = getFile(fileName);
        FileWriter fileWriter = new FileWriter(file, true);

        Connection newConnection = itemProcessor(routeRequest);

        fileWriter.write(String.format("\n%s,%s,%s"
                , newConnection.getSource().getLabel()
                , newConnection.getDestiny().getLabel()
                , newConnection.getCost()));
        fileWriter.close();

        return newConnection;
    }

    private Connection itemProcessor(RouteRequest routeRequest) {
        return new Connection(
                new Airport(routeRequest.getSource())
                , new Airport(routeRequest.getDestiny())
                , routeRequest.getCost());
    }

}
