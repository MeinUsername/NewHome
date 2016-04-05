package my.newhome.Models;

/**
 * Created by Florian on 02.04.2016.
 */
public interface ILocationAvgDistance extends LocationInterface {
    double getAvgGeoDistance();
    void setAvgGeoDistance(double avgDistance);


    double getAvgCarDistanceInMin();
    void setAvgCarDistanceInMin(double avgDistance);

    double getAvgTrainDistanceInMin();
    void setAvgTrainDistanceInMin(double avgDistance);

    double getAvgAutoDistanceInMin();
    void setAvgAutoDistanceInMin(double avgDistance);


}
