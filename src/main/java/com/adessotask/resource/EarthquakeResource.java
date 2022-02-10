package com.adessotask.resource;

import com.adessotask.dto.EarthquakeResponse;
import com.adessotask.service.EarthquakeService;
import com.adessotask.dto.UsgsApiEarthquake;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;

@Path("/earthquakes")

public class EarthquakeResource {

    @Inject
    EarthquakeService earthquakeService;

    @GET
    public Response getEarthquakes(@QueryParam("country") String country, @QueryParam("days") int days) throws IOException {
        List<UsgsApiEarthquake> usgsApiEarthquakes = earthquakeService.fetchEarthquakes(country.toUpperCase(), days);

        EarthquakeResponse response = new EarthquakeResponse(usgsApiEarthquakes);
        if(response.usgsApiEarthquakeList.size() == 0) {
            return Response.ok("No Earthquakes were recorded past " + days + " days").build();
        } else {
            return Response.ok(response).build();
        }
    }
}
