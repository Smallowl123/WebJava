package RMI;

import model.Day;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private final static int PORT = 8000;

    public static void main(String[] args) throws IOException, NotBoundException, ParserConfigurationException, SAXException {
        Registry registry = LocateRegistry.getRegistry(PORT);
        Days stub = (Days) registry.lookup("day");
        for(Day day: stub.getArrayList()){
            System.out.println(day.toString());
        }
    }
}
