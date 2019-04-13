package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import solution.file.FileManagerReader;
import solution.strategy.ShortestPath;

@RunWith(SpringJUnit4ClassRunner.class)
public class ShortestPathTest {

    private FileManagerReader fileManagerReader = new FileManagerReader();

    private ShortestPath shortestPath;

    private final String FILE = "routes-test.txt";


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void readFile() {
        fileManagerReader = fileManagerReader.openFile(FILE);
        Assert.assertEquals("Has 7 connections on file", 7, FileManagerReader.getConnections().size());
        Assert.assertEquals("Five airports on file", 5, FileManagerReader.getAirports().size());
    }

}
