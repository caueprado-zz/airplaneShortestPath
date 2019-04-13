package solution.job;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import solution.file.FileManagerReader;
import solution.utils.Routes;

import java.util.ArrayList;

@Component
public class JobRunner {

    FileManagerReader fileManager;

    @Value("${csv.filename}")
    private String fileName;

    //    @PostConstruct
    public void init() throws Exception {
        fileManager.openFile(fileName);
        Routes.initializeRoutes(FileManagerReader.getConnections()
                , new ArrayList<>(FileManagerReader.getAirports()));
    }
}
