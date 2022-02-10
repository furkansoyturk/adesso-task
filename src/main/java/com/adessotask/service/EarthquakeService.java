package com.adessotask.service;


import com.adessotask.dto.UsgsApiEarthquake;
import com.adessotask.enums.CountriesEnum;
import com.google.gson.*;
import okhttp3.*;

import javax.inject.Singleton;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Singleton
public class EarthquakeService {

    public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<UsgsApiEarthquake> fetchEarthquakes(String country, int days) throws IOException {
        Calendar calXDaysAgo = Calendar.getInstance();
        calXDaysAgo.add(Calendar.DATE, -days);

        String dateXDaysAgo = simpleDateFormat.format(calXDaysAgo.getTime());
        String dateNow = simpleDateFormat.format(new Date());


        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("earthquake.usgs.gov")
                .addPathSegment("fdsnws")
                .addPathSegment("event")
                .addPathSegment("1")
                .addPathSegment("query")
                .addQueryParameter("format","geojson")
                .addQueryParameter("starttime", dateXDaysAgo)
                .addQueryParameter("endtime", dateNow)
                .addQueryParameter("latitude", String.valueOf(CountriesEnum.valueOf(country).getLatitude()))
                .addQueryParameter("longitude", String.valueOf(CountriesEnum.valueOf(country).getLongitude()))
                .addQueryParameter("maxradius", "5")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();

        System.out.println(request);
        Response response = client.newCall(request).execute();

        return usgsApiResponseMapper(response, country);

    }

    private List<UsgsApiEarthquake> usgsApiResponseMapper(Response response, String country) throws IOException {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .create();
        JsonParser jsonParser = new JsonParser();

        JsonObject jo = (JsonObject)jsonParser.parse(response.body().string());
        JsonArray jsonArr = jo.getAsJsonArray("features");

        List<UsgsApiEarthquake> usgsApiEarthquakeLists = new ArrayList<>();
        for (JsonElement featureJsonElems : jsonArr) {
            JsonObject featureJsonObj = featureJsonElems.getAsJsonObject();
            JsonObject propertiesJson = featureJsonObj.getAsJsonObject("properties");
            UsgsApiEarthquake usgsApiEarthquake = gson.fromJson(propertiesJson, UsgsApiEarthquake.class);
            usgsApiEarthquake.country = country;

            // Timestamp to readable date
            Timestamp ts=new Timestamp(Long.parseLong(usgsApiEarthquake.date));
            Date date=new Date(ts.getTime());
            usgsApiEarthquake.date = String.valueOf(date);
            usgsApiEarthquakeLists.add(usgsApiEarthquake);
        }

        return usgsApiEarthquakeLists;
    }
}
