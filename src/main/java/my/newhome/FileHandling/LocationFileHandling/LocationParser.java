package my.newhome.FileHandling.LocationFileHandling;

import my.newhome.FileHandling.XMLParser;
import my.newhome.Models.Coordinates;
import my.newhome.Models.Location;
import my.newhome.Models.LocationInterface;
import my.newhome.XMLFileHandling.XmlFileReader;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by Florian on 02.04.2016.
 */
public class LocationParser implements XMLParser<LocationInterface> {

    private static final String ROOT_ELMENET_NAME = "Locations";
    private static final String LOCATION_ELMENET_NAME = "Location";
    private static final String LOCATION_ELEMENT_ATTR_LAT = "LAT";
    private static final String LOCATION_ELEMENT_ATTR_LONG = "LNG";
    private static final String LOCATION_ELEMENT_ATTR_ADDR = "ADDR";
    private static final String LOCATION_ELEMENT_ATTR_NAME = "NAME";


    private XmlFileReader xmlFileReader = null;
    private Document doc = null;

    @Override
    public ArrayList<LocationInterface> parse() {
        ArrayList<LocationInterface> result = new ArrayList<>();
        NodeList locationNodeList = this.doc.getElementsByTagName(LOCATION_ELMENET_NAME);


        for (int node_i = 0; node_i < locationNodeList.getLength(); node_i++) {
            Node currentNode = locationNodeList.item(node_i);
            NamedNodeMap attr = currentNode.getAttributes();
            /**
             * LOCATION_ELEMENT_ATTR_LAT
             LOCATION_ELEMENT_ATTR_LONG
             LOCATION_ELEMENT_ATTR_ADDR
             LOCATION_ELEMENT_ATTR_NAME
             */
            String curName = attr.getNamedItem(LOCATION_ELEMENT_ATTR_NAME).getNodeValue();
            double curLong = Double.parseDouble(attr.getNamedItem(LOCATION_ELEMENT_ATTR_LONG).getNodeValue());
            double curLat = Double.parseDouble(attr.getNamedItem(LOCATION_ELEMENT_ATTR_LAT).getNodeValue());
            String curAddr = attr.getNamedItem(LOCATION_ELEMENT_ATTR_ADDR).getNodeValue();

            Location newLocation = new Location(curName);
            newLocation.setCoordinates(new Coordinates(curLong, curLat));
            newLocation.setFormattedAddress(curAddr);

            result.add(newLocation);
//            (curName);


        }

        return result;


    }

    @Override
    public XmlFileReader getXmlFileReader() {

        return xmlFileReader;
    }

    @Override
    public void setXmlFileReader(XmlFileReader reader) {
        this.xmlFileReader = reader;
    }

    @Override
    public void readFile(String fileName) {
        try
        {
            this.doc = this.getXmlFileReader().readAsDocument(fileName);

        }
        catch(Exception e)
        {
            System.out.println("Error reading file: " + fileName);
        }

    }

}
