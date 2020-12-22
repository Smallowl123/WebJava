package DOM;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import model.Day;
import org.xml.sax.SAXException;


public class DOM {
    private static ArrayList<Day> days = new ArrayList<>();
    private static String inputXML;
    private static String outputXML;

    public DOM(String inputXML, String outputXML){
        this.inputXML = inputXML;
        this.outputXML = outputXML;
    }

    public static void parse() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(inputXML));
        document.getDocumentElement().normalize();
        NodeList day = document.getDocumentElement().getElementsByTagName("day");
        for (int i = 0; i < day.getLength(); i++){
            Node d = day.item(i);
            if(d.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) d;
                days.add((new Day(element.getElementsByTagName("date").item(0).getTextContent(),
                                  Double.parseDouble(element.getElementsByTagName("maxTemperature").item(0).getTextContent()),
                                  Double.parseDouble(element.getElementsByTagName("minTemperature").item(0).getTextContent()),
                                  element.getElementsByTagName("w_Event").item(0).getTextContent())));
            }
        }
    }
    public static void print(){
        for(Day day : days){
            System.out.println(day.toString());
        }
    }

    public static void xmlOutput(){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document document = docBuilder.newDocument();

            Element rootElement = document.createElement("Day");

            for(int i = 0; i < days.size(); i++) {
                Element day = document.createElement("day");

                Element data = document.createElement("data");
                data.appendChild(document.createTextNode(days.get(i).getDate()));

                Element max_Temperature = document.createElement("max_Temperature");
                max_Temperature.appendChild(document.createTextNode(Double.toString(days.get(i).getMaxTemperatureC())));

                Element min_Temperature = document.createElement("min_Temperature");
                min_Temperature.appendChild(document.createTextNode(Double.toString(days.get(i).getMinTemperatureC())));

                Element w_Event = document.createElement("w_Event");
                w_Event.appendChild(document.createTextNode(days.get(i).getW_Event()));

                day.appendChild(data);
                day.appendChild(max_Temperature);
                day.appendChild(min_Temperature);
                day.appendChild(w_Event);
                rootElement.appendChild(day);
            }
            document.appendChild(rootElement);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(outputXML));

            transformer.transform(source,result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
    public static void temperaturePlusOne(){
        for(Day day: days){
            day.setMaxTemperatureC(day.getMaxTemperatureC() + 1);
            day.setMinTemperatureC(day.getMinTemperatureC() + 1);
        }
    }

    public static ArrayList<Day> getDays(){
        return days;
    }
}
