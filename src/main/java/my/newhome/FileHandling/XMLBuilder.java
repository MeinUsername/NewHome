package my.newhome.FileHandling;

import my.newhome.XMLFileHandling.XmlFileReader;
import my.newhome.XMLFileHandling.XmlFileWriter;

import java.util.ArrayList;

/**
 * Created by Florian on 03.04.2016.
 */
public interface XMLBuilder<T> {

    void setXmlFileWriter(XmlFileWriter reader);
    XmlFileWriter getXmlFileWriter();


    void createDOM();

    void writeFile(String fileName);

    void build(ArrayList<T> values);

}
