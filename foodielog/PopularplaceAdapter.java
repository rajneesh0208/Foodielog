package com.example.foodielog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class PopularplaceAdapter extends RecyclerView.Adapter<PopularplaceAdapter.Viewholder> {


  List<Popularplace_getset> popularlist;
  Context context;


  public PopularplaceAdapter(ArrayList<Popularplace_getset> popularlist, MainActivity mainActivity) {
    this.popularlist = popularlist;
    this.context = mainActivity;
  }


  @NonNull
  @Override
  public PopularplaceAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(context);
    View view = layoutInflater.inflate(R.layout.popularplaceview,null);
    return new Viewholder(view);
  }


  @Override
  public void onBindViewHolder(@NonNull Viewholder holder, int position) {
      Popularplace_getset popularplaceGetset = popularlist.get(position);
    String rest_image = popularlist.get(position).getRestaurent_image();
    String ratenumber = popularlist.get(position).getTxt_ratenumber();
    String restaurent_name = popularlist.get(position).getRestaurent_name();
    String review = popularlist.get(position).getReview();
    Picasso.get().load( rest_image).fit().centerInside().into(holder.restaurent_image );
//    holder.setData(rest_image,ratenumber,review,restaurent_name);
    holder.ratenumber.setText(ratenumber);
    holder.restaurent_name.setText(restaurent_name);
    holder.review.setText(review);
  }

  @Override
  public int getItemCount() {
    return popularlist.size();
  }

  class Viewholder extends RecyclerView.ViewHolder{

    private ImageView restaurent_image;
    private TextView restaurent_name;
    private TextView ratenumber;
    private TextView review;

    public Viewholder(@NonNull View itemView) {
      super(itemView);

      restaurent_image = itemView.findViewById(R.id.restaurent_img);
      restaurent_name = itemView.findViewById(R.id.restaurent_name);
      ratenumber = itemView.findViewById(R.id.txt_ratenumber);
      review = itemView.findViewById(R.id.review);
    }
//
//    private void setData(String rest_image, String txtratenumber, String ppreview, String rest_name){
//      restaurent_image.setImageResource(rest_image);
//      ratenumber.setText(txtratenumber);
//      review.setText(ppreview);
//      restaurent_name.setText(rest_name);
//
//
//    }
  }
}
