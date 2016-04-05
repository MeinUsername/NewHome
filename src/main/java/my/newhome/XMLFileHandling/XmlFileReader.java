package my.newhome.XMLFileHandling;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Florian on 13.03.2016.
 */
public class XmlFileReader {


    public String getFileName() {
        return fileName;
    }

    public XmlFileReader setFileName(String fileName) {

        this.fileName = fileName;

        return this;
    }

    private String fileName = "";

    public XmlFileReader()
    {

    }

    private Document createDocument(File xmlFile) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        return doc;
    }

    protected Document readAsDocument() throws IOException, SAXException, ParserConfigurationException {
        File f = new File(this.fileName);
        if(f.exists() && !f.isDirectory()) {
            return this.createDocument(new File(this.getFileName()));
        }
        else
        {
            throw new FileNotFoundException("File not found: " + this.getFileName());
        }
    }

    public Document readAsDocument(String fileName)
    {

        try
        {
            this.setFileName(fileName);
            return this.readAsDocument();

        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
            return  null;
        }
        catch(SAXException saxEx)
        {
            saxEx.printStackTrace();
            return null;
        }
        catch(ParserConfigurationException parseEx)
        {
            parseEx.printStackTrace();
            return null;
        }



    }




}
