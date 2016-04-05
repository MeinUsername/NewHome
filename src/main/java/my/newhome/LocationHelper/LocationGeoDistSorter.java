package my.newhome.LocationHelper;

import my.newhome.Models.ILocationAvgDistance;

import java.util.Comparator;

/**
 * Created by Florian on 02.04.2016.
 */
public class LocationGeoDistSorter implements Comparator<ILocationAvgDistance> {


    @Override
    public int compare(ILocationAvgDistance o1, ILocationAvgDistance o2) {
        return Double.compare(o1.getAvgGeoDistance(),o2.getAvgGeoDistance());
    }
}
