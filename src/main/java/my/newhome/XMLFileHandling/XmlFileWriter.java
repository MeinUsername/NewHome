package my.newhome.XMLFileHandling;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Created by Florian on 12.03.2016.
 */
public class XmlFileWriter
{

    private static final  String ROOT_ELMENET_NAME = "Locations";
    private static final  String LOCATION_ELMENET_NAME = "Location";
    private static final String LOCATION_ELEMENT_ATTR_LAT = "LAT";
    private static final String LOCATION_ELEMENT_ATTR_LONG = "LNG";
    private static final String LOCATION_ELEMENT_ATTR_ADDR = "ADDR";
    private static final String LOCATION_ELEMENT_ATTR_NAME = "NAME";


    public String getFileName() {
        return fileName;
    }

    public XmlFileWriter setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    private  String fileName;


    public XmlFileWriter()
    {

    }


    public Document createNewDocument()
    {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = null;

            docBuilder = docFactory.newDocumentBuilder();
            return docBuilder.newDocument();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        // root elements
        return null;

    }


//    private void appendLocationElement(Location loc, Element root, Document doc)
//    {
//        Element locElement = doc.createElement(LOCATION_ELMENET_NAME);
//        locElement.setAttribute(LOCATION_ELEMENT_ATTR_NAME,loc.getName());
//        locElement.setAttribute(LOCATION_ELEMENT_ATTR_ADDR,loc.getFormattedAddress());
//        locElement.setAttribute(LOCATION_ELEMENT_ATTR_LAT, String.valueOf(loc.getCoordinates().getLatitude()));
//        locElement.setAttribute(LOCATION_ELEMENT_ATTR_LONG,String.valueOf(loc.getCoordinates().getLongitude()));
//
//        root.appendChild(locElement);
//
//    }
//
//
//    public void write(ArrayList<Location> locationList) throws Exception {
//        Document d = this.createNewDocument();
//        if ( d == null)
//        {
//            throw new Exception("XML Document could not be created. Reason not clear. Should not happen.");
//        }
//        Element root = d.createElement(ROOT_ELMENET_NAME);
//        d.appendChild(root);
//        for(Location loc : locationList)
//        {
//            this.appendLocationElement(loc,root,d);
//        }
//
//
//        this.save(d);
//
//    }

    public void save(Document document)  {

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(this.getFileName()));
            transformer.transform(source, result);
        } catch (TransformerConfigurationException pce)
        {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }








}
