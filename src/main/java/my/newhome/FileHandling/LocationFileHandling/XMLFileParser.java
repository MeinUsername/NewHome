package my.newhome.FileHandling.LocationFileHandling;

import my.newhome.FileHandling.XMLParser;
import my.newhome.XMLFileHandling.XmlFileReader;

import java.util.ArrayList;

/**
 * Created by Florian on 02.04.2016.
 */
public class XMLFileParser<Loc> {

    public XmlFileReader getXmlReader() {
        return xmlReader;
    }

    public XMLFileParser<Loc> setXmlReader(XmlFileReader xmlReader) {
        this.xmlReader = xmlReader;
        return this;
    }

    private XmlFileReader xmlReader;

    public String getFileName() {
        return this.fileName;
    }

    public XMLFileParser setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    private  String fileName;

    public XMLFileParser(String fileName)
    {
        this.fileName = fileName;
    }

    public XMLFileParser()
    {

    }

    public ArrayList<Loc> parse(XMLParser<Loc> parser)
    {
        parser.setXmlFileReader(this.getXmlReader());
        parser.readFile(this.getFileName());
        return parser.parse();
//        ArrayList<Loc> result = new ArrayList<Loc>();
//        return result;
    }


}
