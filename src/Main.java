import DOM.DOM;
import model.Day;
import model.WeatherEvents;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.security.DomainCombiner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        String inputXML = "src\\Days.xml";
        String outputXML = "src\\Days2.xml";
        DOM xmlFile = new DOM(inputXML, outputXML);
        DOM.parse();
        DOM.print();
        DOM.temperaturePlusOne();
        DOM.print();
        DOM.xmlOutput();
    }
}
