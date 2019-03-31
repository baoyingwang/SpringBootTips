package org.baoyingwang.springboottips.sample.helloworldproj.service;

import org.baoyingwang.springboottips.sample.helloworldproj.dao.CountryDao;
import org.baoyingwang.springboottips.sample.helloworldproj.entity.CountryEntity;
import org.baoyingwang.springboottips.sample.helloworldproj.json.CountryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HelloWorldService {

    @Autowired
    private CountryDao countryDao;

    public Optional<CountryModel> query(String country){

        CountryEntity countryEntity = countryDao.query(country);

        if(countryEntity == null){
            return Optional.empty();
        }

        CountryModel countryModel = new CountryModel();
        countryModel.setCountry(countryEntity.getCountry());
        countryModel.setPopulation(countryEntity.getPopulation());
        return Optional.of(countryModel);

    }

    public CountryModel addCountry(String country, Integer population){

        CountryEntity countryEntity = countryDao.query(country);
        if(countryEntity != null){
            throw new RuntimeException("add exists country:"+ country);
        }

        countryEntity = new CountryEntity();
        countryEntity.setCountry(country);
        countryEntity.setPopulation(population);
        countryDao.addCountry(countryEntity);

        CountryModel countryModel = new CountryModel();
        countryModel.setCountry(countryEntity.getCountry());
        countryModel.setPopulation(countryEntity.getPopulation());
        return countryModel;

    }
}
