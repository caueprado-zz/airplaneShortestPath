package solution.file;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import solution.model.Airport;
import solution.model.Connection;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileManagerReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileManagerReader.class);

    private static List<Connection> connections;
    private static List<Airport> airports;

    public FileManagerReader() {
        airports = new ArrayList<>();
        connections = new ArrayList<>();
    }

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

    public FileManagerReader openFile(String fileName) {
        LOGGER.info("Starting file reading");
        String line = "";
        try {
            FileReader fileReader = new FileReader(getFile(fileName));
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                if (!StringUtils.isEmpty(line)) addNewLine(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        return FileManagerReader.this;
    }

    private void addNewLine(String line) {
        Linha linha = new Linha(line);

        Airport source = new Airport(linha.getSource().getLabel());
        Airport dest = new Airport(linha.getDestiny().getLabel());

        airports.stream().filter(airport -> airport.getLabel().equals(source.getLabel()))
                .findFirst().ifPresentOrElse( airport -> {}, () -> airports.add(source));

        airports.stream().filter(airport -> airport.getLabel().equals(dest.getLabel()))
                .findFirst().ifPresentOrElse( airport -> {}, () -> airports.add(dest));

        connections.add(linha.getConnection());

    }

    public static List<Connection> getConnections() {
        return connections;
    }

    public static List<Airport> getAirports() {
        return airports;
    }

    class Linha {

        private Airport source;
        private Airport destiny;
        private Integer cost = 0;

        Linha(String linha) {
            super();
            String[] objs = linha.split(",");
            if (objs.length > 0) {
                this.source = new Airport(objs[0]);
                this.destiny = new Airport(objs[1]);
                this.cost = Integer.parseInt(objs[2]);
            }
        }

        public Airport getSource() {
            return source;
        }

        public Airport getDestiny() {
            return destiny;
        }

        Connection getConnection() {
            return new Connection(this.source, this.destiny, this.cost);
        }
    }

}

