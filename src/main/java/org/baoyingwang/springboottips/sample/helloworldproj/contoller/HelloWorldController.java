package org.baoyingwang.springboottips.sample.helloworldproj.contoller;

import lombok.extern.slf4j.Slf4j;
import org.baoyingwang.springboottips.sample.helloworldproj.json.AddCountryModel;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class HelloWorldController {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String echoHello(@RequestParam(value = "country") String country){
        return String.format("hello,%s !", country);
    }

    @RequestMapping(value = "hello/add_country", method = RequestMethod.POST)
    public String addCountry(@RequestBody AddCountryModel model){

        return String.format("hello,%s !", model.getCountry());
    }

}
