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
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {


    ArrayList<Categorygetset> mcategorylist;
    Context context;


    public Adapter(ArrayList<Categorygetset> categorylist, MainActivity mainActivity) {

        this.context = mainActivity;
        this.mcategorylist = categorylist;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.categoryview,null );
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Categorygetset mcategorygetset = mcategorylist.get(position);
        String resource = mcategorylist.get(position).getCate_image();
        String dishname = mcategorylist.get(position).getDishname();
        String venue = mcategorylist.get(position).getVenue();
        holder.dishname.setText(dishname);
//        holder.venue.setText(venue);

        Picasso.get().load( resource).fit().centerInside().into(holder.cat_image);
        /////Image ke liye string lo tobi chalege
    }

    @Override
    public int getItemCount() {
        return mcategorylist.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{

        private ImageView cat_image;
        private TextView dishname;
        private TextView venue;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            cat_image = itemView.findViewById(R.id.cat_img);
            dishname = itemView.findViewById(R.id.dish_name);
            venue = itemView.findViewById(R.id.venues);
        }

//        private void setData(String resource, String dishes, String venues){
//            cat_image.setImageResource(resource);
//            dishname.setText(dishes);
//            venue.setText(venues);


        }
    }
