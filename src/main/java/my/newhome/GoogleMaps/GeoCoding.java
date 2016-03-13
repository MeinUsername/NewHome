package my.newhome.GoogleMaps;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;

/**
 * Created by Florian on 12.03.2016.
 */
public class GeoCoding {



    public String getApiKey() {
        return apiKey;
    }

    public GeoCoding setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public int getRateLimit() {
        return rateLimit;
    }

    public GeoCoding setRateLimit(int rateLimit) {
        this.rateLimit = rateLimit;
        return this;
    }

    private int rateLimit;
    private String apiKey;
    private GeoApiContext apiContext =null;
    public GeoCoding() {

    }


    public GeoCoding(String apiKey, int rateLimit) {
        this.apiKey = apiKey;
        this.rateLimit = rateLimit;

    }

    private GeoApiContext getGeoApiContext()
    {
        if ( this.apiContext == null)
        {
            this.apiContext  = new GeoApiContext().setApiKey(this.getApiKey());

            apiContext.setQueryRateLimit(this.getRateLimit());
        }
        return this.apiContext;
    }





    public GeocodingResult[] geocode(String locationName, String region)
    {
        GeocodingResult[] results = new GeocodingResult[0];
        try {
            GeocodingApiRequest request = GeocodingApi.newRequest(this.getGeoApiContext());
            request.region(region);
            request.address(locationName);
            results = request.await();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
}


