package my.newhome.FileHandling;

import my.newhome.FileHandling.LocationFileHandling.XMLFileParser;
import my.newhome.XMLFileHandling.XmlFileReader;

import java.util.ArrayList;

/**
 * Created by Florian on 02.04.2016.
 */
public interface XMLParser<R> {


    void setXmlFileReader(XmlFileReader reader);
    XmlFileReader getXmlFileReader();

    void readFile(String fileName);

    ArrayList<R> parse();


//    ArrayList<R> parse(XMLParser parser);
}
