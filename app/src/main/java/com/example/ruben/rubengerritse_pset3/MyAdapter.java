package com.example.ruben.rubengerritse_pset3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by ruben on 22-9-16.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private JSONArray moviesArray;
    private View.OnClickListener listener;

    public MyAdapter(JSONArray movies) {
        this.moviesArray = movies;
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = ((ViewGroup) view.getParent()).indexOfChild(view);
                // After click event
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView itemTextView;
        public ViewHolder(View itemView){
            super(itemView);
            itemTextView = (TextView) itemView.findViewById(R.id.item_text_view);
        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int position){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder vh, int position){
        try {
            vh.itemTextView.setText(moviesArray.getJSONObject(position).getString("Title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount(){
        return moviesArray.length();
    }
}
