package com.example.foodielog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.ViewHolder> {
    private ArrayList<Nearbyplace_getset> nearbylist;
    private Context context;


    public NearbyAdapter(ArrayList<Nearbyplace_getset> nearbylist, MainActivity mainActivity) {
        this.context = mainActivity;
        this.nearbylist = nearbylist;

    }

    @NonNull
    @Override
    public NearbyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.nearbyplacesview,null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Nearbyplace_getset nearbyplaceGetset = nearbylist.get(position);
        String nearby_img = nearbylist.get(position).getNearbyimage();
        String nearby_rest_name = nearbylist.get(position).getNearbyrestname();
        String ratenumber = nearbylist.get(position).getNearbyratenumber();
        String review = nearbylist.get(position).getNearbyreview();
        holder.nearby_ratenumber.setText(ratenumber);
        holder.nearby_review.setText(review);
        holder.nearby_restaurent_name.setText(nearby_rest_name);

        Picasso.with(context).load(nearby_img).fit().centerInside().into(holder.nearby_image);
//        holder.setData(nearby_img,ratenumber,review,nearby_rest_name);
    }

    @Override
    public int getItemCount() {
        return nearbylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView nearby_image;
        private TextView nearby_restaurent_name;
        private TextView nearby_ratenumber;
        private TextView nearby_review;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nearby_image = itemView.findViewById(R.id.nearby_image);
            nearby_restaurent_name = itemView.findViewById(R.id.nearby_restaurent_name);
            nearby_ratenumber = itemView.findViewById(R.id.nearby_ratenumber);
            nearby_review = itemView.findViewById(R.id.nearby_review);
        }

//        private void setData(String nearby_img, String nearby_rest_name, String review, String ratenumber){
//            nearby_image.setImageResource(nearby_img);
//            Picasso.with(context).load(nearby_img).fit().centerInside().into(holder.nearby_image);
//
//            nearby_restaurent_name.setText(nearby_rest_name);
//            nearby_review.setText(review);
//            nearby_ratenumber.setText(ratenumber);
//
//
//        }

    }
}
