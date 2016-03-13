package my.newhome;

import my.newhome.Models.Location;
import my.newhome.TestpointConversion.ConvertTestpoint;
import my.newhome.config.Config;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {
        out.println("Given arguemnts: " + ArrayUtils.toString(args));

        out.println("Find a new home with Google Maps Distance Matrix");

        String lAPIKey = "";
        try
        {
            Config lConfig = new Config("config.ini");

            lAPIKey = lConfig.getApiKey();

            out.println("Use API-Key: " + lAPIKey);

            ConvertTestpoint lConvertTestpoints = new ConvertTestpoint();
            ArrayList<Location> lList = lConvertTestpoints.ConvertCSVFile("AllTestpoints.csv", "output.xml");
            for (Location lLocationValue:lList) {
                System.out.println(lLocationValue.toString());


            }



        }
        catch (IOException e)
        {
            out.println(e.getMessage());
        }
    }
}
