package org.baoyingwang.springboottips.sample.helloworldproj.contoller;

import lombok.extern.slf4j.Slf4j;

import org.baoyingwang.springboottips.sample.helloworldproj.enums.RestBusinessErrorCode;
import org.baoyingwang.springboottips.sample.helloworldproj.json.CountryModel;
import org.baoyingwang.springboottips.sample.helloworldproj.json.SimpleResultResponse;
import org.baoyingwang.springboottips.sample.helloworldproj.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    //TODO think about how a GOOD restful interface should be !!!
    //note: xxx?a=b, @RequestParam(value = "a" String a) will get the value "b"
    @RequestMapping(value = "/country/{country}", method = RequestMethod.GET)
    public SimpleResultResponse<CountryModel> echoHello(@PathVariable("country") String countryName){

        if(countryName == null){
            return SimpleResultResponse.newError(998, "invalid parameter - empty country name");
        }

        Optional<CountryModel> model = helloWorldService.query(countryName);
        if(!model.isPresent()){
            SimpleResultResponse.newError(999, "not found" + countryName);
        }

        return SimpleResultResponse.newInstance(model.get());

    }

    //TODO return id of the new entity
    @RequestMapping(value = "/country", method = RequestMethod.POST)
    public SimpleResultResponse<String> addCountry(@RequestBody CountryModel model){

        //TODO validate fields of model (by hibernate validator or some other validator)
        RestBusinessErrorCode errorCode = helloWorldService.addCountry(model.getCountry(), model.getPopulation());

        return SimpleResultResponse.newInstance(model.getCountry());
    }

}
