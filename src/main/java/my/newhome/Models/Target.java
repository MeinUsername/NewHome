package my.newhome.Models;

import javafx.scene.layout.Priority;

/**
 * Created by Florian on 27.03.2016.
 */
public class Target implements LocationInterface {


    private final Location _location;
    private double _priority = 0.5 ;

    public String getFormattedAddress() {
        return this._location.getFormattedAddress();
    }

    public LocationInterface setFormattedAddress(String formattedAddress) {
        this._location.setFormattedAddress(formattedAddress);
        return this;
    }

    public Coordinates getCoordinates() {
        return this._location.getCoordinates();
    }

    public LocationInterface setCoordinates(Coordinates coordinates) {
        this._location.setCoordinates(coordinates);
        return this;
    }

    public String getName() {
        return this._location.getName();
    }

    public LocationInterface setName(String name) {
        this._location.setName(name);
        return this;
    }

    public Target(String nickName)
    {
        this._location = new Location(nickName);
        this._priority = 0.5;
    }

}
