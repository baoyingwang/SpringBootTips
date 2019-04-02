package org.baoyingwang.springboottips.sample.helloworldproj.dao;

import org.baoyingwang.springboottips.sample.helloworldproj.entity.CountryEntity;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//TODO use h2 as database, rather than in memory. Then we can add more test case samples related with transaction
@Component
public class CountryDao {

    private static Map<String, CountryEntity> countries = new ConcurrentHashMap<>();
    static {
        CountryEntity china = new CountryEntity();
        china.setCountry("China");
        china.setPopulation(1_300_000_000);
        countries.put("China", china);

        CountryEntity japan = new CountryEntity();
        japan.setCountry("Japan");
        japan.setPopulation(100_000_000);
        countries.put("Japan", china);
    }

    //WARN: not too many country in the world. But for others, limit result size is required
    //TODO limit result size
    public List<CountryEntity> query(){
        return new ArrayList<>(countries.values());
    }

    public CountryEntity query(String country){
        return countries.get(country);
    }

    public void addCountry(CountryEntity country){
        countries.put(country.getCountry(), country);
    }

    public void updateCountry(CountryEntity country){
        countries.put(country.getCountry(), country);
    }
}
