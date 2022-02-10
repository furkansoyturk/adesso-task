package com.adessotask.dto;

import com.google.gson.annotations.SerializedName;

public class UsgsApiEarthquake {
    @SerializedName(value = "emailId") public String country;
    @SerializedName(value = "place") public String place;
    @SerializedName(value = "mag") public Double magnitude;
    @SerializedName(value = "time") public String date;
}
