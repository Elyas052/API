package io.loopcamp.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ZipCode {

    private List<ZipCodeInfo> places;

    @JsonProperty("post code")
    private String postCode;
    private String country;

    @JsonProperty("country abbreviation")
    private String countryAbbreviation;

    @JsonProperty("place name")
    private String placeName;
    private String state;

    @JsonProperty("state abbreviation")
    private String stateAbbreviation;
}
