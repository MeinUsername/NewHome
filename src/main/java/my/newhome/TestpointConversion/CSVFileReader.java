package my.newhome.TestpointConversion;

import com.opencsv.CSVReader;
import my.newhome.Models.Location;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Florian on 12.03.2016.
 */
public class CSVFileReader {


    private final static Logger LOGGER = Logger.getLogger(CSVFileReader.class.getName());
    private  char csvSepChar =  ',';

    public String getFileName() {
        return fileName;
    }

    public CSVFileReader setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    private String fileName = "";


    public CSVFileReader() {
        LOGGER.log(Level.INFO, "Created instance of CSVFileReader");

    }



    protected ArrayList<Location> ReadCSV() throws IOException {
        CSVReader lReader = new CSVReader(new FileReader(this.getFileName()),this.getCsvSepChar());

        ArrayList<Location> lResult = new ArrayList<Location>();
        LOGGER.log(Level.INFO,"Read the first ten entries");
        String[] lNextLine;
        String LocationName = "";

        for(int i=0; i<2600;i++)
        {

            lNextLine = lReader.readNext();
            LocationName = lNextLine[0];
            Location lNewLocation = new Location(LocationName);
            lResult.add(lNewLocation);


//            while ((lNextLine = lReader.readNext()) != null) {
//                // lNextLine[] is an array of values from the line
//                System.out.println(lNextLine[0] + lNextLine[1] + "etc...");
//            }

        }
        return lResult;
    }


    public char getCsvSepChar() {
        return csvSepChar;
    }

    public CSVFileReader setCsvSepChar(char csvSepChar) {
        this.csvSepChar = csvSepChar;
        return this;
    }
}
