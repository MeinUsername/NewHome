package my.newhome.Models;

/**
 * Created by Florian on 12.03.2016.
 */


/**
 * Coordinates to represent longitude and latitude
 */
public class Coordinates {
    @Override
    public String toString() {
        return "Coordinates{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    public double getLongitude() {
        return longitude;
    }

    public Coordinates setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public Coordinates setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    private double longitude;
    private double latitude;

    public Coordinates() {

    }

    /**
     * @param longitude
     * @param latitude
     */
    public Coordinates(double longitude, double latitude) {
        this.setLatitude(latitude).setLongitude(longitude);
    }




}
