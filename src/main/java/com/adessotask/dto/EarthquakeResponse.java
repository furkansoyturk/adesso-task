package com.adessotask.dto;

import java.util.List;

public class EarthquakeResponse {
    public EarthquakeResponse(List<UsgsApiEarthquake> usgsApiEarthquakeList) {
        this.usgsApiEarthquakeList = usgsApiEarthquakeList;
    }

    public List<UsgsApiEarthquake> usgsApiEarthquakeList;
}

