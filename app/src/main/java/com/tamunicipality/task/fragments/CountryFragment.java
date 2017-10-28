package com.tamunicipality.task.fragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tamunicipality.task.R;
import com.tamunicipality.task.net.countries.Country;
import com.tamunicipality.task.net.countries.Currency;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CountryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountryFragment extends Fragment {

    private static final String ARG_PARAM1_COUNTRY = "param1";
    private static final String ARG_PARAM2_BORDERS_LIST = "param2";

    private Country mCountry;
    private List<Country> mBordersList;

    public CountryFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param country the chosen country
     * @return A new instance of fragment CountryFragment.
     */
    public static CountryFragment newInstance(Country country, List<Country> bordersList) {
        CountryFragment fragment = new CountryFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1_COUNTRY, country);
        if(bordersList!=null) {
            ArrayList<Country> arrayList;
            if(bordersList instanceof ArrayList){
                arrayList = (ArrayList<Country>)bordersList;
            }
            else {
                arrayList = new ArrayList<>();
                arrayList.addAll(bordersList);
            }
            args.putParcelableArrayList(ARG_PARAM2_BORDERS_LIST, arrayList);
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCountry = getArguments().getParcelable(ARG_PARAM1_COUNTRY);
            mBordersList = getArguments().getParcelableArrayList(ARG_PARAM2_BORDERS_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_country, container, false);
        TextView textViewCountryName = (TextView)view.findViewById(R.id.textViewCountryName);
        TextView textViewCountryCapitalName = (TextView)view.findViewById(R.id.textViewCountryCapitalName);
        TextView textViewCountryCurrenciesName = (TextView)view.findViewById(R.id.textViewCountryCurrenciesName);
        textViewCountryName.setText(mCountry.getName());
        if(!TextUtils.isEmpty(mCountry.getCapital().trim()))
            textViewCountryCapitalName.setText(getString(R.string.capital_city) + mCountry.getCapital());
        else
            textViewCountryCapitalName.setText(getString(R.string.capital_city) + "-");
        String currenciesStr = "";
        List<Currency> currenciesList = mCountry.getCurrencies();
        int i=0;
        for(Currency currency : currenciesList){
            if(currency.getName()!=null) {
                if(i++ != 0) {
                    currenciesStr += ", ";
                }
                currenciesStr += currency.getName() +" ("+currency.getSymbol()+")";
            }
        }
        if(TextUtils.isEmpty(currenciesStr)) {
            currenciesStr = "-";
        }
        if(i > 1) {
            currenciesStr = getString(R.string.currencies) + currenciesStr;
        }
        else {
            currenciesStr = getString(R.string.currency) + currenciesStr;
        }
        textViewCountryCurrenciesName.setText(currenciesStr);
        TextView textViewBorders = (TextView)view.findViewById(R.id.textViewBorders);
        textViewBorders.setText(getString(R.string.border_countries) + mCountry.getName()+":");
        Context context = getActivity().getApplicationContext();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerListCountries);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        RecyclerView.Adapter adapter = new CountriesRecyclerViewAdapter(mBordersList, null);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        view.setOnTouchListener((v, event) -> true);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
