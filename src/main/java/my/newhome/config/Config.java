package my.newhome.config;

import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;

/**
 * Created by Florian on 12.03.2016.
 */
public class Config {

    private final String configfile;
    private int rateLimit;
    private String apiKey = "";

    public Config(String aIniFileName) throws IOException {
        this.configfile = aIniFileName;

        this.ReadIniFile(aIniFileName);

    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    private void ReadIniFile(String aIniFileName) throws IOException {
        Wini ini = new Wini(new File(aIniFileName));
        this.apiKey = ini.get("Google Maps", "apikey");
        this.rateLimit = Integer.parseInt(ini.get("Google Maps", "ratelimit"));

    }


    private void Process() {

    }


    public int getRateLimit() {
        return rateLimit;
    }

    public Config setRateLimit(int rateLimit) {
        this.rateLimit = rateLimit;
        return this;
    }
}
