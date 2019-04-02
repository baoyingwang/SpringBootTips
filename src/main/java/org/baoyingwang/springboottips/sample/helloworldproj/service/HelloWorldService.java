package org.baoyingwang.springboottips.sample.helloworldproj.service;

import org.baoyingwang.springboottips.sample.helloworldproj.dao.CountryDao;
import org.baoyingwang.springboottips.sample.helloworldproj.entity.CountryEntity;
import org.baoyingwang.springboottips.sample.helloworldproj.enums.RestBusinessErrorCode;
import org.baoyingwang.springboottips.sample.helloworldproj.json.CountryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HelloWorldService {

    @Autowired
    private CountryDao countryDao;

    public List<CountryModel> queryAll(){

        List<CountryEntity> countryEntityList = countryDao.query();
        if(countryEntityList == null){
            return Collections.emptyList();
        }

        List<CountryModel> result = countryEntityList.stream().map(countryEntity ->{
            CountryModel countryModel = new CountryModel();
            countryModel.setCountry(countryEntity.getCountry());
            countryModel.setPopulation(countryEntity.getPopulation());
            return countryModel;
        }).collect(Collectors.toList());

        return result;
    }

    public Optional<CountryModel> query(String country){

        if(country == null){
            return Optional.empty();
        }

        CountryEntity countryEntity = countryDao.query(country);
        if(countryEntity == null){
            return Optional.empty();
        }

        CountryModel countryModel = new CountryModel();
        countryModel.setCountry(countryEntity.getCountry());
        countryModel.setPopulation(countryEntity.getPopulation());
        return Optional.of(countryModel);

    }

    //TODO return entity id with error code
    public RestBusinessErrorCode  addCountry(String country, Integer population){

        CountryEntity countryEntity = countryDao.query(country);
        if(countryEntity != null){
            return RestBusinessErrorCode.FAIL_INSERT;
        }

        countryEntity = new CountryEntity();
        countryEntity.setCountry(country);
        countryEntity.setPopulation(population);
        countryDao.addCountry(countryEntity);

        CountryModel countryModel = new CountryModel();
        countryModel.setCountry(countryEntity.getCountry());
        countryModel.setPopulation(countryEntity.getPopulation());
        return RestBusinessErrorCode.SUCCESS;

    }
}
