package my.newhome;

import my.newhome.FileHandling.LocationFileHandling.LocationParser;
import my.newhome.FileHandling.LocationFileHandling.XMLFileParser;
import my.newhome.FileHandling.XMLBuilder;
import my.newhome.Models.*;
import my.newhome.TestpointConversion.ConvertTestpoint;
import my.newhome.TestpointFiltration.FilterTestpoints;
import my.newhome.XMLFileHandling.XmlFileReader;
import my.newhome.config.Config;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.*;

public class Main {

    private static final String version = "0.1";


    public enum Tasks{
        convert,        //convert csv to xml
        filter,         //filter interesting points (limit subset)
        search,          //search new home
        nothing     //do nothing
    }

    public static void main(String[] args) {


        Options options = new Options();
        options.addOption("convert",false, "Convert CSV to XML (with geocoding the files). Call like -convert AllTespoints.csv");
        options.addOption("filter",false, "Filter XML after conversion. Call like -filter output.xml");
        options.addOption("out",true, "Output of filteration or conversion or search. Call like -out output_filter.xml");
        options.addOption("in",true, "Input of filteration or conversion or search. Call like -in AllTespoints.csv");
        options.addOption("version", false, "Show current version");



        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse( options, args);
        } catch (ParseException e) {
            e.printStackTrace();
            exit(100);
        }

        if ( cmd.hasOption("version"))
        {

            out.println("The current version is " + version);
            exit(0);
        }




        String outputFile = "output.xml";
        String inputFile = "AllTestpoints.csv";
        Tasks currentTask = Tasks.nothing ;
        if ( cmd.hasOption("convert"))
        {
            currentTask = Tasks.convert;

            outputFile = "output.xml";

        }

        if (cmd.hasOption("filter"))
        {
            currentTask = Tasks.filter;
            inputFile = "output_back.xml";
            outputFile = "output_filter.xml";
        }

        if ( cmd.hasOption("out"))
        {
            outputFile = cmd.getOptionValue("out");
        }

        if ( cmd.hasOption("in"))
        {
            inputFile = cmd.getOptionValue("in");
        }










        String lAPIKey = "";
        try
        {
            Config lConfig = new Config("config.ini");

            lAPIKey = lConfig.getApiKey();

            out.println("Use API-Key: " + lAPIKey);

            switch (currentTask)
            {
                case convert:
                    ConvertTask(inputFile, outputFile);
                    break;
                case filter:
                    FilterTask(inputFile,outputFile);
                    break;
                case search:
                    SearchTask(inputFile,outputFile);
                    break;
                default:
                    throw new IllegalArgumentException("Given task is not supported");

            }



        }
        catch (IOException e)
        {
            out.println(e.getMessage());
        }
    }

    private static void SearchTask(String inputFile, String outputFile) {
    }

    private static void FilterTask(String inputFile, String outputFile) {



        //read locations
        XMLFileParser<LocationInterface> xmlParser = new XMLFileParser<>(inputFile);

        xmlParser.setFileName(inputFile);
        xmlParser.setXmlReader(new XmlFileReader());
        ArrayList<LocationInterface> locations = xmlParser.parse(new LocationParser());


        ArrayList<Target> targets = new ArrayList<>();

        Target arvato = new Target("arvato");
        //47.390044, 8.515227
        arvato.setCoordinates(new Coordinates(8.515227,47.390044));
        //47.149582, 8.433291
        Target roche = new Target("roche");
        roche.setCoordinates(new Coordinates(8.433291,47.149582));


        targets.add(arvato);
        targets.add(roche);




        FilterTestpoints x  = new FilterTestpoints(locations);
        ArrayList<LocationAvgDistance> sortedList =  x.sortByAvgDistance(locations,targets);




        for(LocationInterface loc : sortedList)
        {
            System.out.println(loc.toString());
        }


    }

    private static void ConvertTask(String inputFile, String outputFile) throws IOException {
        ConvertTestpoint lConvertTestpoints = new ConvertTestpoint();

        ArrayList<Location> lList = lConvertTestpoints.ConvertCSVFile(inputFile, outputFile);
        for (Location lLocationValue:lList) {
            System.out.println(lLocationValue.toString());


        }
    }
}
