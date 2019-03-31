package org.baoyingwang.springboottips.sample.helloworldproj;

import org.baoyingwang.springboottips.sample.helloworldproj.dao.CountryDao;
import org.baoyingwang.springboottips.sample.helloworldproj.entity.CountryEntity;
import org.baoyingwang.springboottips.sample.helloworldproj.json.CountryModel;
import org.baoyingwang.springboottips.sample.helloworldproj.service.HelloWorldService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("ut")
public class HelloWorldServiceTest {

    @Autowired
    private HelloWorldService helloWorldService;

    @MockBean
    private CountryDao countryDao; //use this mocked bean, rather than the

    @Test
    public void testEchoHello(){

        String country = "India";
        Integer population = 1_200_000_000;
        CountryEntity india = new CountryEntity();
        india.setCountry(country);
        india.setPopulation(population);
        when(countryDao.query("India")).thenReturn(india);

        Optional<CountryModel> countryModel = helloWorldService.query("India");
        verify(countryDao, times(1)).query(country);

        assertTrue(countryModel.isPresent());
        assertEquals(country, countryModel.get().getCountry());
        assertEquals(population, countryModel.get().getPopulation());


    }
}
