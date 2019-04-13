package solution.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import solution.file.FileManagerReader;
import solution.utils.Routes;

import java.util.ArrayList;

@Order(1)
@Component
public class ArgFileRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArgFileRunner.class);

    @Autowired
    private FileManagerReader fileManagerReader;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("ArgFileRunner running");
        if (args.length > 0) {
            fileManagerReader.openFile(args[0]);
            Routes.initializeRoutes(FileManagerReader.getConnections()
                    , new ArrayList<>(FileManagerReader.getAirports()));
        }
    }
}
