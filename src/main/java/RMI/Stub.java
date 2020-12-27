package RMI;

import DOM.DOM;
import model.Day;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Stub extends UnicastRemoteObject implements Days {

    protected Stub() throws RemoteException {
        super();
    }

    @Override
    public ArrayList<Day> getArrayList() throws IOException, SAXException, ParserConfigurationException {
        DOM dom = new DOM("src\\Days.xml", "");
        DOM.parse();
        DOM.temperaturePlusOne();
        return DOM.getDays();
    }
}
