package my.newhome.Models;

/**
 * Created by Florian on 27.03.2016.
 */
public interface LocationInterface {
    String getFormattedAddress();

    LocationInterface setFormattedAddress(String formattedAddress);

    Coordinates getCoordinates();

    LocationInterface setCoordinates(Coordinates coordinates);

    String getName();

    LocationInterface setName(String name);
}
