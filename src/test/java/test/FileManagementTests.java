package test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import solution.file.FileManagerReader;

@RunWith(SpringJUnit4ClassRunner.class)
public class FileManagementTests {

    private FileManagerReader fileManagerReader = new FileManagerReader();

    private final String FILE = "input-file.txt";

    @Test(expected = NullPointerException.class)
    public void fileNotFound() {
        fileManagerReader.openFile("teste.txt");
    }

    @Test(expected = NumberFormatException.class)
    public void invalidCostValue() {
        fileManagerReader.openFile("input-error-file.txt");
    }

    @Test
    public void readFile() {
        fileManagerReader = fileManagerReader.openFile(FILE);
        Assert.assertEquals("Has 7 connections on file",7,FileManagerReader.getConnections().size());
        Assert.assertEquals("Five airports on file",5,FileManagerReader.getAirports().size());
    }
}
