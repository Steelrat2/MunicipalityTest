package com.tamunicipality.task.net;

import com.tamunicipality.task.net.countries.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Dmitriy on 19.06.2017.
 */

public interface CountriesApi {

    @GET("rest/v2/all")
    Call<List<Country>> getAll();

}
