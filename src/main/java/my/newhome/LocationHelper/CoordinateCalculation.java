package my.newhome.LocationHelper;

import my.newhome.Models.Coordinates;
import my.newhome.Models.Location;

import java.util.ArrayList;

/**
 * Created by Florian on 14.03.2016.
 */
public class CoordinateCalculation {

    public CoordinateCalculation()
    {

    }


    public enum Units {
        Meter, Kilometer
    }


    /**
     * Calculates the distance between two Coordinates
     *
     * Adapted from: https://stackoverflow.com/questions/4656802/midpoint-between-two-latitude-and-longitude
     * @param coord1
     * @param coord1
     * @return Coordiates
     */
    public static Coordinates getMidPoint(Coordinates coord1, Coordinates coord2){


        double lat1 = coord1.getLatitude();
        double lat2 = coord2.getLatitude();
        double lon1 = coord1.getLongitude();
        double lon2 = coord2.getLongitude();


//        double lat1,double lon1,double lat2,double lon2
        double dLon = Math.toRadians(lon2 - lon1);


        //convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        lon1 = Math.toRadians(lon1);

        double Bx = Math.cos(lat2) * Math.cos(dLon);
        double By = Math.cos(lat2) * Math.sin(dLon);
        double lat3 = Math.atan2(Math.sin(lat1) + Math.sin(lat2), Math.sqrt((Math.cos(lat1) + Bx) * (Math.cos(lat1) + Bx) + By * By));
        double lon3 = lon1 + Math.atan2(By, Math.cos(lat1) + Bx);

        //print out in degrees

        Coordinates result = new Coordinates(Math.toDegrees(lon3),Math.toDegrees(lat3));

        return result;
//        System.out.println(Math.toDegrees(lat3) + " " + Math.toDegrees(lon3));
    }


    public static double getDistance(double lat1, double lng1, double lat2, double lng2, double earthRadius) {
//        double earthRadius = 3958.75; // miles (or 6371.0 kilometers)
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double sindLat = Math.sin(dLat / 2.0);
        double sindLng = Math.sin(dLng / 2.0);
        double a = Math.pow(sindLat, 2.0) + Math.pow(sindLng, 2.0)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return earthRadius * c;
    }



    public static double getDistance(Coordinates coord1,  Coordinates coord2, Units unit)
    {
        double earthradius = 6371.0; //km
        if ( unit == Units.Meter)
        {
            earthradius *= 1000.0;
        }

//        return getDistance(coord1.getLongitude(),coord2.getLongitude(),coord1.getLatitude(),coord2.getLatitude(),earthradius);
        return getDistance(coord1.getLatitude(),coord1.getLongitude(),coord2.getLatitude(),coord2.getLongitude(),earthradius);

    }


    public double getDistance(Location loc1, Location loc2, Units unit)
    {
        return this.getDistance(loc1.getCoordinates(),loc2.getCoordinates(),unit);
    }

    public ArrayList<Location> findInterestingNodes(ArrayList<Location> targetPoints, ArrayList<Location> testPoints, double allowedDistanceToNearestTargetInKm )
    {

        ArrayList<Location> result = new ArrayList<Location>();

        double NoOfTestpoints =  targetPoints.size();

        double maxDistanceToTestingPoin = 0.0;

        for( Location test_i : testPoints)
        {
            for(Location target_i : targetPoints)
            {
                double curDistance = this.getDistance(test_i,target_i,Units.Kilometer);
                if ( curDistance > maxDistanceToTestingPoin)
                {
                    maxDistanceToTestingPoin = curDistance;
                }
            }
            if ( maxDistanceToTestingPoin < allowedDistanceToNearestTargetInKm)
            {
                result.add(test_i);
            }
        }


        return result;

    }



    public ArrayList<Location> findMainLocations(ArrayList<Location> locations)
    {
        ArrayList<Location> result = new ArrayList<Location>();

        //1. Cluster locations to main cluster (say 500) --> this should be something like the town
        //2. Find central point for each cluster

        //3. Find snapping point in locations for central point

        return result;
    }

}

