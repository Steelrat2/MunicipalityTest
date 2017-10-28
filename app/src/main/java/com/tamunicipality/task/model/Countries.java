package com.tamunicipality.task.model;

import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.tamunicipality.task.net.Client;
import com.tamunicipality.task.net.CountriesApi;
import com.tamunicipality.task.net.countries.Country;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Dmitriy on 22.10.2017.
 */

public class Countries {

    private final static String TAG = Countries.class.getSimpleName();
    private final static Countries sCountries = new Countries();

    private volatile ICountriesResponseCallback callback;
    private Map<String, Country> mAlpha3CodeMap;
    private Map<String, List<Country>> mRegionMap;
    private boolean isError = false;
    private String mErrorMsg = "";

    private Countries(){}

    public static Countries getInstance() {
        return  sCountries;
    }

    public void setCountriesResponseCallback(ICountriesResponseCallback callback) {
        this.callback = callback;
    }

    public void requestCountries(ICountriesResponseCallback callback) {
        isError = false;
        mRegionMap=null;
        mAlpha3CodeMap=null;
        mErrorMsg="";
        RequestAsyncTask firstAsync = new RequestAsyncTask();
        this.callback = callback;
        firstAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public boolean isError() {
        return isError;
    }

    public String getErrorMsg(){
        return mErrorMsg;
    }

    public List<String> getRegions() {
        List<String> list=null;
        if(mRegionMap!=null) {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                list = mRegionMap.keySet().stream().sorted().collect(Collectors.toList());
            }
            else {
                list = new ArrayList();
                list.addAll(mRegionMap.keySet());
                Collections.sort(list, (o1,o2)->o1.compareTo(o2));
            }
        }
        return list;
    }

    public List<Country> getCountriesByRegion(String regionName){
        List<Country> list=null;
        if(mRegionMap!=null) {
            list = mRegionMap.get(regionName);
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                list=list.stream().sorted(countryComparator).collect(Collectors.toList());
            }
            else {
                Collections.sort(list, countryComparator);
            }
        }
        return list;
    }

    private Comparator<Country> countryComparator =
            (c1,c2)->c2.getArea()-c1.getArea()>0?1:c1.getArea()-c2.getArea()==0?0:-1;


    public List<Country> getBorderCountries(Country country){
        List<Country> list = null;
        if(mAlpha3CodeMap!=null){
            list = new ArrayList();
            for(String alpha3Code : country.getBorders())
                list.add(mAlpha3CodeMap.get(alpha3Code));
        }
        return list;
    }

    public void response(List<Country> list, String errorMessage) {
        if(list != null) {
            mAlpha3CodeMap = new HashMap();
            mRegionMap = new HashMap();
            for (Country country : list) {
                mAlpha3CodeMap.put(country.getAlpha3Code(), country);
                String region = country.getRegion();
                if(TextUtils.isEmpty(region))
                    region="No Region";
                if(!mRegionMap.containsKey(region)) {
                    mRegionMap.put(region, new ArrayList());
                }
                mRegionMap.get(region).add(country);

            }
            if(callback!=null)
                callback.successful(getRegions(), this);
            isError = false;
        }
        else {
            isError = true;
            mErrorMsg = errorMessage;
            if(callback!=null)
                callback.error(errorMessage);
        }
    }


    private class RequestAsyncTask extends AsyncTask<Void, Void, List<Country>> {

        private String errorMsg = "";

        @Override
        protected List<Country> doInBackground(Void... params) {
            List<Country> clist = null;
            CountriesApi countriesApi = Client.getCountriesApi();
            Call<List<Country>> callCountryList = countriesApi.getAll();
            try {
                Response<List<Country>> responseCountryList = callCountryList.execute();
                if(responseCountryList.isSuccessful()){
                    clist = responseCountryList.body();
                }
                else {
                    errorMsg = responseCountryList.errorBody().string();
                }
            } catch (IOException e) {
                Log.e("RequestAsyncTask", "IOException: "+e.getLocalizedMessage() ,e);
                errorMsg = e.getMessage();
            }

            return clist;
        }

        @Override
        protected void onPostExecute(List<Country> list){
            response(list, errorMsg);
        }
    }
}
