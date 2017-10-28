package com.tamunicipality.task.fragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tamunicipality.task.R;
import com.tamunicipality.task.net.countries.Country;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnMainFragmentListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    private static final String TAG = MainFragment.class.getSimpleName();

    public enum TypeFragment{
        REGIONS(1),
        COUNTRIES(2);
        private int index;
        TypeFragment(int index) {
            this.index = index;
        }
        public int getIndex(){
            return index;
        }
        public static TypeFragment getInstance(int index) {
            switch(index){
                case 1: return REGIONS;
                case 2: return COUNTRIES;
                default: return null;
            }
        }
    }
    private static final String ARG_PARAM1_TYPE = "param1";
    private static final String ARG_PARAM2_STRING_ARRAY = "param2";
    private static final String ARG_PARAM3_REGION_NAME = "param3";
    private static final String ARG_PARAM4_COUNTRIES = "param4";

    private TypeFragment mTypeFragment;
    private List<String> mRegionsList;
    private String mRegionName;
    private List<Country> mCountriesList;

    private OnMainFragmentListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param typeFragment TypeFragment enum.
     * @param regions list of regions.
     * @return A new instance of fragment MainFragment.
     */
    public static MainFragment newInstance(TypeFragment typeFragment, List<String> regions,
                                           String regionName, List<Country> countries) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1_TYPE, typeFragment.getIndex());
        if(regions!=null) {
            ArrayList<String> arrayList;
            if(regions instanceof ArrayList){
                arrayList = (ArrayList<String>)regions;
            }
            else {
                arrayList = new ArrayList<>();
                arrayList.addAll(regions);
            }
            args.putStringArrayList(ARG_PARAM2_STRING_ARRAY, arrayList);
        }
        if(regionName != null){
            args.putString(ARG_PARAM3_REGION_NAME, regionName);
        }
        if(countries!=null){
            ArrayList<Country> arrayList;
            if(regions instanceof ArrayList){
                arrayList = (ArrayList<Country>)countries;
            }
            else {
                arrayList = new ArrayList<>();
                arrayList.addAll(countries);
            }
            args.putParcelableArrayList(ARG_PARAM4_COUNTRIES, arrayList);
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTypeFragment = TypeFragment.getInstance(getArguments().getInt(ARG_PARAM1_TYPE));
            mRegionsList = getArguments().getStringArrayList(ARG_PARAM2_STRING_ARRAY);
            mRegionName = getArguments().getString(ARG_PARAM3_REGION_NAME);
            mCountriesList = getArguments().getParcelableArrayList(ARG_PARAM4_COUNTRIES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_regions, container, false);
        Context context = getActivity().getApplicationContext();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        RecyclerView.Adapter adapter=null;
        if(mTypeFragment == TypeFragment.REGIONS){
            adapter = new RegionsRecyclerViewAdapter(mRegionsList, mListener);
        }
        else if(mTypeFragment == TypeFragment.COUNTRIES){
            TextView textView = (TextView)view.findViewById(R.id.textViewRegions);
            textView.setText(mRegionName + " " + getString(R.string.region));
            adapter = new CountriesRecyclerViewAdapter(mCountriesList, mListener);
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMainFragmentListener) {
            mListener = (OnMainFragmentListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnMainFragmentListener {
        void onSetRegion(String name);
        void onSetCountry(Country country);
    }
}
