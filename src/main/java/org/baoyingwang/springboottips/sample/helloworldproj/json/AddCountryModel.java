package org.baoyingwang.springboottips.sample.helloworldproj.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddCountryModel {

    @JsonProperty("country-name")
    String country;
}
