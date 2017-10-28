package com.tamunicipality.task.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tamunicipality.task.R;
import com.tamunicipality.task.net.countries.Country;

import java.util.List;

/**
 * Created by Dmitriy on 24.10.2017.
 */

public class CountriesRecyclerViewAdapter extends RecyclerView.Adapter<CountriesRecyclerViewAdapter.ViewHolder>  {
    private List<Country> regionsList;
    private MainFragment.OnMainFragmentListener listener;

    CountriesRecyclerViewAdapter(List<Country> regionsList, MainFragment.OnMainFragmentListener listener){
        this.regionsList = regionsList;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return regionsList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_regions, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Country country = regionsList.get(position);
        holder.textViewRegion.setGravity(Gravity.LEFT);
        holder.textViewRegion.setText(country.getName());
        holder.textViewName.setVisibility(View.VISIBLE);
        holder.textViewName.setText(country.getNativeName());
        if(listener!=null) {
            holder.textViewArea.setVisibility(View.VISIBLE);
            holder.textViewArea.setText(String.valueOf(country.getArea()));
        }
        holder.textViewSeparator.setVisibility(View.VISIBLE);
        holder.view.setOnClickListener((v)->{
            if (null != listener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                listener.onSetCountry(country);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView textViewRegion;
        TextView textViewName;
        TextView textViewArea;
        TextView textViewSeparator;
        public ViewHolder(View view) {
            super(view);
            this.view = view;
            this.textViewRegion = (TextView) view.findViewById(R.id.textViewRegion);
            this.textViewName = (TextView) view.findViewById(R.id.textViewName);
            this.textViewArea = (TextView) view.findViewById(R.id.textViewArea);
            this.textViewSeparator = (TextView) view.findViewById(R.id.textViewSeparator);
        }
    }
}
