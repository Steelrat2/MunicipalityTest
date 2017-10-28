package com.tamunicipality.task.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tamunicipality.task.R;

import java.util.List;

/**
 * Created by Dmitriy on 24.10.2017.
 */

public class RegionsRecyclerViewAdapter extends RecyclerView.Adapter<RegionsRecyclerViewAdapter.ViewHolder> {

    private List<String> regionsList;
    private MainFragment.OnMainFragmentListener listener;

    RegionsRecyclerViewAdapter(List<String> regionsList, MainFragment.OnMainFragmentListener listener){
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
        final String region = regionsList.get(position);
        holder.textViewRegion.setText(region);
        holder.view.setOnClickListener((v)->{
                if (listener != null) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    listener.onSetRegion(region);
                }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView textViewRegion;
        public ViewHolder(View view) {
            super(view);
            this.view = view;
            this.textViewRegion = (TextView) view.findViewById(R.id.textViewRegion);
        }
    }
}
