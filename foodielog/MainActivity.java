package com.example.foodielog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Adapter adapter;
    RequestQueue requestQueue;
    RecyclerView recyclerview, recyclerview1, recyclerview2;
    ArrayList<Categorygetset> categorylist;
    ArrayList<Popularplace_getset> popularlist;
    ArrayList<Nearbyplace_getset> nearbylist;
    RelativeLayout rel_venue;
    RelativeLayout rel_order;
    RelativeLayout rel_favourite;
    RelativeLayout rel_settings;
    ImageView img_vanue;
    ImageView order_img;
    ImageView favourite_img;
    ImageView settings_img;
    TextView txt_venu;
    TextView txt_order;
    TextView txt_favourite;
    TextView txt_settings;
    private ImageView venue_image;
    private TextView txt_venue;
    private ImageView img_order;
    private ImageView img_favourite;
    private ImageView img_settings;
    private Object Volley;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
        recyclerview = findViewById(R.id.recycleview_category);
        recyclerview.setLayoutManager( new LinearLayoutManager(this));


        recyclerview2 = findViewById(R.id.recycler_nearby_place);
        recyclerview2.setLayoutManager(new LinearLayoutManager(this));

        recyclerview1 = findViewById(R.id.recyclerview_popularplace);
        recyclerview1.setLayoutManager(new LinearLayoutManager(this));


        categorylist();
         popularplace();
         nearbyplace();
//         init(view);


    }


//    private void init(View view) {
//        rel_venue = findViewById(R.id.venues_layout);
//        rel_order = findViewById(R.id.orders_layout);
//        rel_favourite = findViewById(R.id.favorite_layout);
//        rel_settings = findViewById(R.id.setting_layout);
//
//        img_vanue = findViewById(R.id.venues);
//        img_order = findViewById(R.id.orders_image);
//        img_favourite = findViewById(R.id.favorite);
//        img_settings = findViewById(R.id.setting);
//
//        txt_venu = findViewById(R.id.txt_venue);
//        txt_order = findViewById(R.id.txt_orders);
//        txt_favourite = findViewById(R.id.txt_favourite);
//        txt_settings = findViewById(R.id.txt_setting);
//
//        rel_venue.setOnClickListener(this);
//        rel_order.setOnClickListener(this);
//        rel_favourite.setOnClickListener(this);
//        rel_settings.setOnClickListener(this);


//    }

    private void categorylist(){

        RequestQueue requestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());


        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();

        final String url = "https://freaktemplate.com/cityguide/api/category";

        categorylist = new ArrayList<>();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response );

            try {

                JSONObject jsonObject =new JSONObject(response);
                JSONArray data =jsonObject.getJSONArray("data");

                for (int i = 0; i < data.length() ; i++) {

                    JSONObject jsonObject1 = data.getJSONObject(i);
                    String dishname = jsonObject1.getString("name");
                    String id = jsonObject1.getString("id");
                    String dishimage = jsonObject1.getString("image");

                    Categorygetset catgetset = new Categorygetset();
                    catgetset.setDishname(dishname);
                    catgetset.setCate_image(dishimage);
                    catgetset.setVenue(id);
                    categorylist.add(catgetset);

                }

                setAdapter();

            } catch(JSONException e){
                e.printStackTrace();
            }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"onResponse: " + error.getMessage());
            }
        });
        requestQueue.add(stringRequest);

//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerview.setLayoutManager(layoutManager);
//        Adapter adapter = new Adapter(categorylist);
//        recyclerview.setAdapter(adapter);
    }

    private void setAdapter() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        Adapter adapter = new Adapter(categorylist,this);
        recyclerview.setAdapter(adapter);
    }



    private void popularplace(){

        RequestQueue requestQueue1;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());


        requestQueue1 = new RequestQueue(cache, network);
        requestQueue1.start();

        final String url = "https://freaktemplate.com/cityguide/api/home";

        popularlist = new ArrayList<>();


        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response );

                try {

                    JSONObject jsonObject =new JSONObject(response);
                    JSONObject data =jsonObject.getJSONObject("data");
                    JSONArray dataArray1 = data.getJSONArray("data");

                    for (int i = 0; i < dataArray1.length() ; i++) {
                        JSONObject jsonObject1 = dataArray1.getJSONObject(i);


                        String restaurentname = jsonObject1.getString("name");
                        String review = jsonObject1.getString("totalreview");
                        String txt_ratenumber = jsonObject1.getString("ratting");

                            String img =new String();
                        JSONArray image = jsonObject1.getJSONArray("image");
                        for(int k = 0; k< image.length(); k++){
                                img = image.getString(0);
                        }


                        Popularplace_getset popgetset = new Popularplace_getset();
                        popgetset.setRestaurent_name(restaurentname);
                        popgetset.setRestaurent_image(img);
                        popgetset.setReview(review);
                        popgetset.setTxt_ratenumber(txt_ratenumber);

                        popularlist.add(popgetset);

                    }

                    setPopularplaceAdapter();

                } catch(JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"onResponse: " + error.getMessage());
            }
        });
        requestQueue1.add(stringRequest1);



    }

    private void setPopularplaceAdapter() {
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview1.setLayoutManager(linearLayoutManager1);
        PopularplaceAdapter adapter1 = new PopularplaceAdapter(popularlist,this);
        recyclerview1.setAdapter(adapter1);
    }

    //        recyclerview1 = findViewById(R.id.recyclerview_popularplace);
//       final LinearLayoutManager linearLayout = new LinearLayoutManager(this);
//        linearLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerview1.setLayoutManager(linearLayout);
//        recyclerview1.setHasFixedSize(true);
//        PopularplaceAdapter adapter = new PopularplaceAdapter(popularlist);
//        recyclerview1.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//    }
    private void nearbyplace(){


        RequestQueue requestQueue2;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());


        requestQueue2 = new RequestQueue(cache, network);
        requestQueue2.start();

        String url = "https://freaktemplate.com/cityguide/api/nearbyplace?lat=21.230023223048985&long=72.89093659783934";
        nearbylist = new ArrayList<>();
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response );

                try {
                    JSONObject jsonObject =new JSONObject(response);
                    JSONObject data =jsonObject.getJSONObject("data");
                    JSONArray dataArray = data.getJSONArray("data");

                    for (int i = 0; i < dataArray.length(); i++) {

                        JSONObject jsonObject1 = dataArray.getJSONObject(i);
                        String id = jsonObject1.getString("id");
                        String name = jsonObject1.getString("name");
                        String ratting = jsonObject1.getString("ratting");
                        String review = jsonObject1.getString("totalreview");


                        String image = new String();

                        JSONArray img =jsonObject1.getJSONArray("image");
                        for(int k=0; k <img.length();k++){

                            image =img.getString(0);
                        }

                        Nearbyplace_getset nearbyplacegetset = new Nearbyplace_getset();
                        nearbyplacegetset.setNearbyimage(image);
                        nearbyplacegetset.setNearbyreview(review);
                        nearbyplacegetset.setNearbyrestname(name);
                        nearbyplacegetset.setNearbyratenumber(ratting);
                        nearbylist.add(nearbyplacegetset);

                        }
        
                        setNearbyAdapter();

                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"onResponse: " + error.getMessage());
            }
        });
        requestQueue2.add(stringRequest2);
    }

    private void setNearbyAdapter() {

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview2.setLayoutManager(linearLayoutManager2);
        NearbyAdapter adapter2 = new NearbyAdapter(nearbylist,this);
        recyclerview.setAdapter(adapter2);




    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.venues_layout:
//
//                venue_image.setImageResource(R.drawable.active_venue);
//                order_img.setImageResource(R.drawable.orders);
//                favourite_img.setImageResource(R.drawable.favourites);
//                settings_img.setImageResource(R.drawable.setting);
//
//                venue_image.setColorFilter(getResources().getColor(R.color.black));
//                venue_image.setColorFilter(getResources().getColor(R.color.light_blue));
//                venue_image.setColorFilter(getResources().getColor(R.color.light_blue));
//                venue_image.setColorFilter(getResources().getColor(R.color.light_blue));
//
//                txt_venue.setTextColor(getResources().getColor(R.color.black));
//                txt_order.setTextColor(getResources().getColor(R.color.light_blue));
//                txt_favourite.setTextColor(getResources().getColor(R.color.light_blue));
//                txt_settings.setTextColor(getResources().getColor(R.color.light_blue));
//
//                break;
//        }
//
//    }
}
