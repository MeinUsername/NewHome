package my.newhome.LocationHelper;

import com.google.maps.model.GeocodingResult;
import my.newhome.GoogleMaps.GeoCoding;
import my.newhome.Models.Coordinates;
import my.newhome.Models.Location;

/**
 * Created by Florian on 14.03.2016.
 */
public class GoogleMapsGeocoding {


    private GeoCoding geoCoding;




    public void GoogleMapsGeocoding(GeoCoding geoCoding)
    {
        this.geoCoding = geoCoding;

    }


    /**
     * Has to be tested
     * @param location
     */
    public void completLocation(Location location)
    {
        String locationName = location.getName();
        GeocodingResult[] geoCodingResultArray = this.geoCoding.geocode(locationName, "ch");
        if (geoCodingResultArray.length > 0) {

            GeocodingResult geoCodingResult = geoCodingResultArray[0];
            double longitude = geoCodingResult.geometry.location.lng;
            double latitude = geoCodingResult.geometry.location.lat;
            String address = geoCodingResult.formattedAddress;
//                location.getCoordinates().setLatitude(latitude);
//                location.getCoordinates().setLongitude(longitude);
            location.setCoordinates(new Coordinates(longitude, latitude));
            location.setFormattedAddress(address);
        }
        else
        {
            location.setCoordinates(new Coordinates(0.0,0.0));
            location.setFormattedAddress("InvalidPoint");

        }
    }



}
