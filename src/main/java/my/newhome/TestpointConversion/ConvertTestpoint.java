package my.newhome.TestpointConversion;

import com.google.maps.model.GeocodingResult;
import my.newhome.GoogleMaps.GeoCoding;
import my.newhome.Models.Coordinates;
import my.newhome.Models.Location;
import my.newhome.config.Config;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Florian on 12.03.2016.
 */
public class ConvertTestpoint {

    public ConvertTestpoint() {

    }

    public ArrayList<Location> ConvertCSVFile(String aFileName, String outputFileName) throws IOException {

        Config config = new Config("config.ini");

        XmlFileWriter xmlWriter = new XmlFileWriter();
        XmlFileReader xmlReader = new XmlFileReader();
        LocationXMLWrapper locationXMLWrapper = new LocationXMLWrapper(outputFileName, xmlWriter, xmlReader);
        String apiKey = config.getApiKey();
        int rateLimit = 2500;
        try {
            locationXMLWrapper.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }


        CSVFileReader lWrapper = new CSVFileReader().setFileName(aFileName);


        ArrayList<Location> lList = lWrapper.ReadCSV();
        ArrayList<Location> xmlList = locationXMLWrapper.getLocationList();

        for (Location lLocation : lList) {
            GeoCoding lGeoCoding = new GeoCoding();
            lGeoCoding.setApiKey(apiKey).setRateLimit(rateLimit);
            String locationName = lLocation.getName();
            if ( locationXMLWrapper.contains(locationName))
            {

                System.out.println("Found location " + locationName + " in xml file.");
                continue;
//
            }
            GeocodingResult[] geoCodingResultArray = lGeoCoding.geocode(locationName, "ch");
            if (geoCodingResultArray.length > 0) {

                GeocodingResult geoCodingResult = geoCodingResultArray[0];
                double longitude = geoCodingResult.geometry.location.lng;
                double latitude = geoCodingResult.geometry.location.lat;
                String address = geoCodingResult.formattedAddress;
//                lLocation.getCoordinates().setLatitude(latitude);
//                lLocation.getCoordinates().setLongitude(longitude);
                lLocation.setCoordinates(new Coordinates(longitude, latitude));
                lLocation.setFormattedAddress(address);
                xmlList.add(lLocation);     //new address
            }
            else
            {
                lLocation.setCoordinates(new Coordinates(0.0,0.0));
                lLocation.setFormattedAddress("InvalidPoint");
                xmlList.add(lLocation);
            }



        }

        try {
            locationXMLWrapper.write(xmlList);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return lList;
    }


}
