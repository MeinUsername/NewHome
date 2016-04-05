package my.newhome.XMLFileWrapper;

import my.newhome.Models.Coordinates;
import my.newhome.Models.Location;
import my.newhome.XMLFileHandling.XmlFileReader;
import my.newhome.XMLFileHandling.XmlFileWriter;
import org.w3c.dom.*;

import java.util.ArrayList;

/**
 * Created by Florian on 13.03.2016.
 */
public class LocationXMLWrapper {


    private static final String ROOT_ELMENET_NAME = "Locations";
    private static final String LOCATION_ELMENET_NAME = "Location";
    private static final String LOCATION_ELEMENT_ATTR_LAT = "LAT";
    private static final String LOCATION_ELEMENT_ATTR_LONG = "LNG";
    private static final String LOCATION_ELEMENT_ATTR_ADDR = "ADDR";
    private static final String LOCATION_ELEMENT_ATTR_NAME = "NAME";
    private XmlFileWriter writer;
    private XmlFileReader reader;
    private String fileName;
    private Document doc;
    private XmlFileReader xmlFileReader = null;


    public ArrayList<Location> getLocationList() {
        return locationList;
    }

    public ArrayList<String> getLocationNameList() {
        return locationNameList;
    }

    private ArrayList<Location> locationList = null;
    private ArrayList<String> locationNameList = null;
    public XmlFileWriter getWriter() {
        return writer;
    }

    public LocationXMLWrapper setWriter(XmlFileWriter writer) {
        this.writer = writer;
        return this;
    }

    public XmlFileReader getReader() {
        return reader;
    }

    public LocationXMLWrapper setReader(XmlFileReader reader) {
        this.reader = reader;
        return this;
    }

    public LocationXMLWrapper(String fileName, XmlFileWriter xmlFileWriter, XmlFileReader xmlFileReader) {
        this.fileName = fileName;
        this.writer= xmlFileWriter;
        this.getWriter().setFileName(this.fileName);
        this.reader = xmlFileReader;
        this.getReader().setFileName(this.fileName);
        this.locationList = new ArrayList<Location>();
        this.locationNameList = new ArrayList<String>();

    }


    public LocationXMLWrapper parse() throws Exception {
        this.doc = this.reader.readAsDocument(fileName);
        if (this.doc == null) {
            throw new Exception("XML File " + fileName + " could not be successfully be transformed to DOMDocument");
        }


        NodeList locationNodeList = this.doc.getElementsByTagName(LOCATION_ELMENET_NAME);


        for ( int node_i = 0; node_i<locationNodeList.getLength();node_i++)
        {
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
            newLocation.setCoordinates(new Coordinates(curLong,curLat));
            newLocation.setFormattedAddress(curAddr);

            this.locationList.add(newLocation);
            this.locationNameList.add(curName);






        }




        return this;
    }


    public boolean contains(String locationName)
    {
        return this.locationNameList.contains(locationName);
    }




    private void appendLocationElement(Location loc, Element root, Document doc)
    {
        Element locElement = doc.createElement(LOCATION_ELMENET_NAME);
        locElement.setAttribute(LOCATION_ELEMENT_ATTR_NAME,loc.getName());
        locElement.setAttribute(LOCATION_ELEMENT_ATTR_ADDR,loc.getFormattedAddress());
        locElement.setAttribute(LOCATION_ELEMENT_ATTR_LAT, String.valueOf(loc.getCoordinates().getLatitude()));
        locElement.setAttribute(LOCATION_ELEMENT_ATTR_LONG,String.valueOf(loc.getCoordinates().getLongitude()));

        root.appendChild(locElement);

    }


    public void write(ArrayList<Location> locationList) throws Exception {
        Document d = writer.createNewDocument();
        if ( d == null)
        {
            throw new Exception("XML Document could not be created. Reason not clear. Should not happen.");
        }
        Element root = d.createElement(ROOT_ELMENET_NAME);
        d.appendChild(root);
        for(Location loc : locationList)
        {
            this.appendLocationElement(loc,root,d);
        }


        writer.save(d);

    }





}


