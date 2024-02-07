package io.loopcamp.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ZipCodeInfo {

    @JsonProperty("place name")
    private String placeName;
    private String longitude;
    private String state;

    @JsonProperty("state abbreviation")
    private String stateAbbreviation;
    private String latitude;

    @JsonProperty("post code")
    private String postCode;
}
