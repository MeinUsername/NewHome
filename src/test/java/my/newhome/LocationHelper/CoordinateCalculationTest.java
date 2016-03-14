package my.newhome.LocationHelper;

import static org.junit.Assert.*;

/**
 * Created by Florian on 14.03.2016.
 */
public class CoordinateCalculationTest {

    @org.junit.Test
    public void testGetDistance() throws Exception {


        CoordinateCalculation calcCoord = new CoordinateCalculation();

        //test the distance between two
        //copied from http://www.movable-type.co.uk/scripts/latlong.html
        double lat1 =  42.990967;
        double long1= -71.463767;
        // 10.990967 -41.463767
        double lat2 =  10.990967;
        double long2= -41.463767;

        //km
        double earthradiusInKM =  6371.0;
        //m
        double earthradiusInM =  earthradiusInKM * 1000.0;

        double result = CoordinateCalculation.getDistance(lat1,long1,lat2,long2,earthradiusInKM);
//        double result = CoordinateCalculation.getDistance(long1,lat1,long2,lat2,earthradiusInKM);

        double resultInM = CoordinateCalculation.getDistance(lat1,long1,lat2,long2,earthradiusInM);

        assert(result == 4580.75);
        assert(resultInM == 4580.75*1000.0);
        //5% margin
        assertEquals(4580.75,result,4580.75*0.05);
        assertEquals(4580.75*1000.0,resultInM,4580.75*1000.0*0.05);


    }

    @org.junit.Test
    public void testGetDistance1() throws Exception {
        assert(true);
    }

    @org.junit.Test
    public void testGetDistance2() throws Exception {
        assert(true);
    }

    @org.junit.Test
    public void testFindInterestingNodes() throws Exception {
        assert(true);
    }

    @org.junit.Test
    public void testFindMainLocations() throws Exception {
        assert(true);
    }
}