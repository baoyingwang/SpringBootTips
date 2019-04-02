package org.baoyingwang.springboottips.sample.helloworldproj.integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.baoyingwang.springboottips.sample.helloworldproj.enums.RestBusinessErrorCode;
import org.baoyingwang.springboottips.sample.helloworldproj.json.CountryModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("ut")
public class HelloWorldIntegrationTest {

    @LocalServerPort
    private int port;

    //simulate restful client(since HelloWorkService is listening
    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Test
    public void testGETCase(){

        testGET("China", 1_300_000_000);
    }

    @Test
    public void testPOST(){

        CountryModel india = new CountryModel();
        india.setCountry("India");
        india.setPopulation(1_000_000_000);
        HttpEntity<CountryModel> entity = new HttpEntity<>(india, headers);

        ResponseEntity<SimpleResponseEAddCountryModel> response = restTemplate.exchange(
                "http://localhost:"+port+"/country",
                HttpMethod.POST, entity, SimpleResponseEAddCountryModel.class);

        SimpleResponseEAddCountryModel model = response.getBody();
        Assert.assertNotNull(model);
        Assert.assertEquals(new Integer(RestBusinessErrorCode.SUCCESS.getCode()), model.getErrorCode());
        Assert.assertNotNull(model.getData());
        Assert.assertEquals("India", model.getData());

        testGET("India", 1_000_000_000);

    }

    public void testGET(String country, Integer expectedPopulation){

        HttpEntity<String> entity = null;
        ResponseEntity<SimpleResponseEchoCountryModel> response = restTemplate.exchange(
                "http://localhost:"+port+"/country/" + country,
                HttpMethod.GET, entity, SimpleResponseEchoCountryModel.class);

        SimpleResponseEchoCountryModel model = response.getBody();
        Assert.assertNotNull(model);
        Assert.assertEquals(new Integer(RestBusinessErrorCode.SUCCESS.getCode()), model.getErrorCode());
        Assert.assertNotNull(model.getData());

        Assert.assertEquals(country, model.getData().getCountry());
        Assert.assertEquals(expectedPopulation, model.getData().getPopulation());

    }

    //Generic type cannot be identified while unmarshal from string - so , hardcode the type here
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    static public class SimpleResponseEchoCountryModel {

        //TODO re-check whether use error-code or some others to make it consistent with best practice
        @JsonProperty("error-code")
        private Integer errorCode;

        @JsonProperty("error-message")
        private String errorMessage;

        @JsonProperty("data")
        private CountryModel data;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    static public class SimpleResponseEAddCountryModel {

        //TODO re-check whether use error-code or some others to make it consistent with best practice
        @JsonProperty("error-code")
        private Integer errorCode;

        @JsonProperty("error-message")
        private String errorMessage;

        @JsonProperty("data")
        private String data; //as country
    }
}
