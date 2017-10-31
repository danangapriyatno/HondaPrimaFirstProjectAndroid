package com.example.prima.projectlaravelpresales;

import android.graphics.Movie;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.List;


import Api.ApiInterface;
import Presales.ApiClient;
import Presales.Presale;
import Presales.PresaleAdapter;
import Presales.PresaleResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MenuUtamaActivity extends AppCompatActivity {
    private static final String TAG = MenuUtamaActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MenuUtamaActivity.this,FormBuyerIdentificationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tampilData();

        /*menambah warna pada SwipeRefreshLayout*/
        final SwipeRefreshLayout dorefresh = (SwipeRefreshLayout)findViewById(R.id.swipeRefresh);
        dorefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        /*event ketika widget dijalankan*/
        dorefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                refreshItem();
            }

            void refreshItem() {
                tampilData();
                onItemLoad();
            }

            void onItemLoad() {
                dorefresh.setRefreshing(false);
            }

        });

    }
    private void tampilData() {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mainmenurecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ApiInterface apiService =ApiClient.getClient().create(ApiInterface.class);
        SharedPreferences sharedpreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        String tokenusersharedpreferenced = sharedpreferences.getString("tokenuser","");
        Call<PresaleResponse> call = apiService.getPresaleData(tokenusersharedpreferenced.toString());
        call.enqueue(new Callback<PresaleResponse>() {
            @Override
            public void onResponse(Call<PresaleResponse>call, Response<PresaleResponse> response) {
                final List<Presale> movies = response.body().getData();
                Log.d(TAG, "Number of movies received: " + movies.size());
                Toast.makeText(MenuUtamaActivity.this, "Number of movies received: " + movies.size(), Toast.LENGTH_LONG).show();
                recyclerView.setAdapter(new PresaleAdapter(movies, R.layout.list_item_presales, getApplicationContext()));

                /*perintah klik recyclerview*/
                recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                    GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                        public boolean onSingleTapUp(MotionEvent e){
                            return true;
                        }
                    });

                    @Override
                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                        View child = rv.findChildViewUnder(e.getX(), e.getY());
                        if (child != null && gestureDetector.onTouchEvent(e)){
                            int position = rv.getChildAdapterPosition(child);
                            Toast.makeText(getApplicationContext(), "Id : " + movies.get(position).getId() + " selected", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(MenuUtamaActivity.this, PresaleDetailActivity.class);
                            i.putExtra("presales_no",String.valueOf(movies.get(position).getPresales_no()) );
                            i.putExtra("installment", String.valueOf(movies.get(position).getInstallment()));
                            i.putExtra("payment", String.valueOf(movies.get(position).getPayment()));
                            i.putExtra("down_payment",String.valueOf(movies.get(position).getDown_payment()) );
                            MenuUtamaActivity.this.startActivity(i);
                            finish();

                        }
                        return false;
                    }

                    @Override
                    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

                    }

                    @Override
                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                    }
                });
            }

            @Override
            public void onFailure(Call<PresaleResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }
    @Override
    public void onBackPressed() {

    }
}
