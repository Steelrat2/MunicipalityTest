package com.tamunicipality.task;


import com.tamunicipality.task.model.Countries;
import com.tamunicipality.task.net.countries.Country;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(MockitoJUnitRunner.class)
public class UnitTest {

    @Mock
    MainActivity mMockMainActivity;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        List<Country> mockCountriesList = new ArrayList<>();
        mockCountriesList.add(createCountry("Israel", "ISR", "asia", 210.0f));
        mockCountriesList.add(createCountry("Russia", "RUS", "europe", 760000.0f));
        mockCountriesList.add(createCountry("Syria", "SYR", "asia", 910.0f));
        mockCountriesList.add(createCountry("Egypt", "EGP", "asia", 1300.7f));
        Countries.getInstance().response(mockCountriesList, "");
    }

    @Test
    public void checkCountries(){
        List<String> areas = Countries.getInstance().getRegions();
        assertEquals(areas.size(), 2);
        List<Country> countriesList =Countries.getInstance().getCountriesByRegion("asia");
        assertEquals(countriesList.size(), 3);
        assertTrue(countriesList.get(0).getArea() > countriesList.get(1).getArea()  );
        assertTrue(countriesList.get(1).getArea() > countriesList.get(2).getArea() );
    }

    private static Country createCountry(String name, String alpha3Code, String region, float area ){
        Country country = new Country();
        country.setName(name);
        country.setAlpha3Code(alpha3Code);
        country.setRegion(region);
        country.setArea(area);
        return country;
    }
}