package my.newhome.FileHandling.LocationFileHandling;

import my.newhome.FileHandling.XMLBuilder;
import my.newhome.Models.Location;
import my.newhome.Models.LocationAvgDistance;
import my.newhome.XMLFileHandling.XmlFileWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

/**
 * Created by Florian on 03.04.2016.
 */
public class LocationWithDistancesFileBuilder implements XMLBuilder<LocationAvgDistance> {

    private static final String ROOT_ELMENET_NAME = "Locations";
    private static final String LOCATION_ELMENET_NAME = "Location";
    private static final String LOCATION_ELEMENT_ATTR_LAT = "LAT";
    private static final String LOCATION_ELEMENT_ATTR_LONG = "LNG";
    private static final String LOCATION_ELEMENT_ATTR_ADDR = "ADDR";
    private static final String LOCATION_ELEMENT_ATTR_NAME = "NAME";

    private static final String LOCATION_ELEMENT_ATTR_AVG_GEODIST = "AVG_GEODIST";
    private static final String LOCATION_ELEMENT_ATTR_AVG_TRAINDIST = "AVG_TRAINDIST";
    private static final String LOCATION_ELEMENT_ATTR_AVG_CARDIST = "AVG_CARDIST";
    private static final String LOCATION_ELEMENT_ATTR_AVG_AUTODIST = "AVG_AUTODIST";



    private XmlFileWriter writer;
    private Document doc;

    @Override
    public void setXmlFileWriter(XmlFileWriter writer) {
        this.writer = writer;
    }


    LocationWithDistancesFileBuilder(XmlFileWriter writer)
    {
        this.writer = writer;
    }

    @Override
    public XmlFileWriter getXmlFileWriter() {
        return this.writer;
    }

    @Override
    public void createDOM() {
        this.doc = writer.createNewDocument();
    }

    @Override
    public void writeFile(String fileName) {

    }


    private void appendLocationElement(LocationAvgDistance loc, Element root)
    {
        Element locElement = doc.createElement(LOCATION_ELMENET_NAME);
        locElement.setAttribute(LOCATION_ELEMENT_ATTR_NAME,loc.getName());
        locElement.setAttribute(LOCATION_ELEMENT_ATTR_ADDR,loc.getFormattedAddress());
        locElement.setAttribute(LOCATION_ELEMENT_ATTR_LAT, String.valueOf(loc.getCoordinates().getLatitude()));
        locElement.setAttribute(LOCATION_ELEMENT_ATTR_LONG,String.valueOf(loc.getCoordinates().getLongitude()));
        locElement.setAttribute(LOCATION_ELEMENT_ATTR_AVG_AUTODIST,"");
        locElement.setAttribute(LOCATION_ELEMENT_ATTR_AVG_GEODIST, "" );
        locElement.setAttribute(LOCATION_ELEMENT_ATTR_AVG_TRAINDIST,"" );
        locElement.setAttribute(LOCATION_ELEMENT_ATTR_AVG_CARDIST,"");

        root.appendChild(locElement);

    }
    @Override
    public void build(ArrayList<LocationAvgDistance> values) {
        Element root = this.doc.createElement(ROOT_ELMENET_NAME);
        doc.appendChild(root);
        for(LocationAvgDistance loc : values)
        {
            this.appendLocationElement(loc,root);
        }


        writer.save(doc);
    }
}
