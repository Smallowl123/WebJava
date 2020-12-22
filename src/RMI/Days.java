package RMI;

import model.Day;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;

public interface Days extends Remote {
    public ArrayList<Day> getArrayList() throws IOException, SAXException, ParserConfigurationException;
}
