package org.example;

import jdk.jshell.spi.ExecutionControl;
import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.URISyntaxException;
import java.util.Formatter;
import java.util.Locale;
import java.util.Optional;

public class AddressResolver {

    public static final String MAPQUESTAPI_GEOCODING = "https://www.mapquestapi.com/geocoding/v1/reverse";
    private static final int API_SUCCESS = 0;
    private ISimpleHttpClient httpClient;

    public AddressResolver(ISimpleHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Optional<Address> findAddressForLocation(double latitude, double longtitude) throws URISyntaxException, ParseException {

        String location = (new Formatter().format(Locale.US, "%.6f, %.6f", latitude, longtitude).toString());

        URIBuilder uriBuilder = new URIBuilder(MAPQUESTAPI_GEOCODING);
        uriBuilder.addParameter("key", "temporary_test_key"); //TODO: If needed add the key.
        uriBuilder.addParameter("location", location);

        String response = httpClient.doHttpGet(uriBuilder.build().toString());

        JSONObject jsonResponse = (JSONObject) new JSONParser().parse(response);

        if ((Long) ((JSONObject) jsonResponse.get("info")).get("statuscode") != API_SUCCESS) {
            return Optional.empty();
        } else {
            jsonResponse = (JSONObject) ((JSONArray) jsonResponse.get("results")).get(0);

            if (((JSONArray) jsonResponse.get("locations")).isEmpty()) {
                return Optional.empty();
            } else {
                JSONObject address = (JSONObject) ((JSONArray) jsonResponse.get("locations")).get(0);

                String road = (String) address.get("street");
                String city = (String) address.get("adminArea5");
                String state = (String) address.get("adminArea3");
                String zip = (String) address.get("postalCode");
                return Optional.of(new Address(road, city, state, zip, null));
            }
        }
    }
}