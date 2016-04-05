package my.newhome.Models;

import java.util.ArrayList;

/**
 * Created by Florian on 12.03.2016.
 */
public class Location implements LocationInterface {

    private String name = "";
    private String formattedAddress = "";
    private Coordinates coordinates = null;

    public Location() {
        this.init();
    }

    public Location(String aName) {
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

    public Location setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
        return this;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Location setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    private void init() {
        this.coordinates = new Coordinates(0.0, 0.0);
    }

    public String getName() {
        return name;
    }

    public Location setName(String name) {
        this.name = name;
        return this;
    }



}
