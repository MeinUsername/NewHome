package my.newhome.TestpointFiltration;

import my.newhome.LocationHelper.CoordinateCalculation;
import my.newhome.LocationHelper.LocationGeoDistSorter;
import my.newhome.Models.*;

import java.util.*;

/**
 * Created by Florian on 27.03.2016.
 */
public class FilterTestpoints {


    private final ArrayList<LocationInterface> _locationList;

    public FilterTestpoints(ArrayList<LocationInterface> locationList) {
        this._locationList = locationList;
    }


    public ArrayList<LocationAvgDistance> sortByAvgDistance(ArrayList<LocationInterface> testPoints, ArrayList<Target> targets)
    {
        ArrayList<LocationAvgDistance> result = new ArrayList<LocationAvgDistance>();


        for ( LocationInterface location_i : testPoints)
        {

            if (location_i.getFormattedAddress().equals("InvalidPoint"))
            {
                continue;
            }
            Double avgDistance = 0.0;
            for ( Target target_i : targets)
            {
                double curDistancenInKM = CoordinateCalculation.getDistance(location_i.getCoordinates(),target_i.getCoordinates(), CoordinateCalculation.Units.Kilometer);
                System.out.println("Distance between " + target_i.getName() + " and "  + location_i.getName() +  " = " + curDistancenInKM);

                avgDistance+= curDistancenInKM;

            }

            avgDistance/=targets.size();


            LocationAvgDistance e = new LocationAvgDistance(location_i.getName());
            e.setCoordinates(location_i.getCoordinates());
            e.setFormattedAddress(location_i.getFormattedAddress());

            e.setAvgGeoDistance(avgDistance);
            result.add(e);

        }

        Collections.sort(result, new LocationGeoDistSorter());
        return result;
    }


    public ArrayList<Location> filterByBoundingBox(Coordinates upperLeft, Coordinates lowerRight) {
        ArrayList<Location> result = new ArrayList<Location>();
        return result;
    }

    public ArrayList<Location> filterByRadius(Coordinates center, double radius, ArrayList<Location> sourcePoints) {
        ArrayList<Location> result = new ArrayList<Location>();
        return result;
    }


}
