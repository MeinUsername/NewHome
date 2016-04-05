package my.newhome.Models;

/**
 * Created by Florian on 12.03.2016.
 */
public class LocationAvgDistance implements ILocationAvgDistance {

    private String name = "";
    private String formattedAddress = "";
    private Coordinates coordinates = null;
    private Double avgGeoDistanceInMin = 1.0e6;
    private Double avgCarDistanceInMin = 1.0e6;
    private Double avgTrainDistanceInMin = 1.0e6;
    private Double avgAutoDistanceInMin = 1.0e6;
    private Double avgGeoDistance = 1.0e6;

    public LocationAvgDistance() {
        this.init();
    }

    public LocationAvgDistance(String aName) {
        this.name = aName;
        this.init();
    }


    @Override
    public String toString() {
        return "Location{" +
                "coordinates=" + coordinates +
                ", formattedAddress='" + formattedAddress + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public LocationAvgDistance setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
        return this;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocationAvgDistance setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    private void init() {
        this.coordinates = new Coordinates(0.0, 0.0);
    }

    public String getName() {
        return name;
    }

    public LocationAvgDistance setName(String name) {
        this.name = name;
        return this;
    }


    @Override
    public double getAvgGeoDistance() {
        return this.avgGeoDistance;
    }

    @Override
    public void setAvgGeoDistance(double avgDistance) {
        this.avgGeoDistance = avgDistance;
    }

    @Override
    public double getAvgCarDistanceInMin() {
        return this.avgCarDistanceInMin;
    }

    @Override
    public void setAvgCarDistanceInMin(double avgDistance) {
        this.avgCarDistanceInMin = avgDistance;

    }

    @Override
    public double getAvgTrainDistanceInMin() {
        return this.avgTrainDistanceInMin;
    }

    @Override
    public void setAvgTrainDistanceInMin(double avgDistance) {
        this.avgTrainDistanceInMin = avgDistance;

    }

    @Override
    public double getAvgAutoDistanceInMin() {
        return this.avgAutoDistanceInMin;
    }

    @Override
    public void setAvgAutoDistanceInMin(double avgDistance) {
        this.avgAutoDistanceInMin=avgDistance;
    }
}
