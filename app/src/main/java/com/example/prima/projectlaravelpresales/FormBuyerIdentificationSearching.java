package com.example.prima.projectlaravelpresales;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


import Api.ApiInterface;
import Customer.CustomerAdapter;
import Customer.CustomerGetSet;
import Customer.CustomerReponse;
import Presales.ApiClient;
import Presales.Presale;
import Presales.PresaleAdapter;
import Presales.PresaleResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormBuyerIdentificationSearching extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_buyer_identification_searching);
        EditText txtSearch = (EditText) findViewById(R.id.buyeridentificationsearchingEdittextSearching);
        Button btnSearch = (Button) findViewById(R.id.buyeridentificationsearchingButtonSearching);
        showData();

    }
    public void showData(){
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.buyeridentificationsearchingrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        SharedPreferences sharedpreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        String tokenusersharedpreferenced = sharedpreferences.getString("tokenuser","");
        String wa = "1";
        Call<CustomerReponse> call = apiService.GetCustomerData(tokenusersharedpreferenced.toString());
        call.enqueue(new Callback<CustomerReponse>() {
            @Override
            public void onResponse(Call<CustomerReponse> call, Response<CustomerReponse> response) {
                final List<CustomerGetSet> movies = response.body().getData();
                List<CustomerGetSet> list = response.body().getData();
                recyclerView.setAdapter(new CustomerAdapter(movies, R.layout.list_item_searching_customer, getApplicationContext()));
                recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                    GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                        public boolean onSingleTapUp(MotionEvent e) {
                            return true;
                        }
                    });

                    @Override
                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                        View child = rv.findChildViewUnder(e.getX(), e.getY());
                        if (child != null && gestureDetector.onTouchEvent(e)) {
                            int position = rv.getChildAdapterPosition(child);
                            Toast.makeText(getApplicationContext(), "Id : " + movies.get(position).getId() + " selected", Toast.LENGTH_SHORT).show();

                            //Intent i = new Intent(FormBuyerIdentificationSearching.this, PresaleDetailActivity.class);
                            Toast.makeText(FormBuyerIdentificationSearching.this, String.valueOf(movies.get(position).getId()), Toast.LENGTH_SHORT).show();
//                            i.putExtra("presales_no", String.valueOf(movies.get(position).getId()));
//                            i.putExtra("installment", String.valueOf(movies.get(position).getNik()));
//                            i.putExtra("payment", String.valueOf(movies.get(position).getName()));
//                            i.putExtra("down_payment", String.valueOf(movies.get(position).getCity()));
//                            i.putExtra("telepon", String.valueOf(movies.get(position).getTelephone()));
//                            FormBuyerIdentificationSearching.this.startActivity(i);
//                            finish();

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
            public void onFailure(Call<CustomerReponse> call, Throwable t) {

            }
        });
    }
    public void searchData(){
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.buyeridentificationsearchingrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        SharedPreferences sharedpreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        String tokenusersharedpreferenced = sharedpreferences.getString("tokenuser","");
        Call<CustomerReponse> call = apiService.GetCustomerData(tokenusersharedpreferenced.toString());
        call.enqueue(new Callback<CustomerReponse>() {
            @Override
            public void onResponse(Call<CustomerReponse> call, Response<CustomerReponse> response) {
                final List<CustomerGetSet> movies = response.body().getData();
                List<CustomerGetSet> list = response.body().getData();
                recyclerView.setAdapter(new CustomerAdapter(movies, R.layout.list_item_searching_customer, getApplicationContext()));
                final String[] datagetId = new String[list.size()];
                final String[] datagetNIK = new String[list.size()];
                final String[] datagetName= new String[list.size()];
                final String[] datagetTelephone = new String[list.size()];
                final String[] datagetCity = new String[list.size()];

                for (int ai=0;ai<=list.size()-1;ai++){
                    datagetId[ai] =String.valueOf(list.get(ai).getId());
                    datagetNIK[ai] =String.valueOf(list.get(ai).getNik());
                    datagetName[ai] = String.valueOf(list.get(ai).getName());
                    datagetTelephone[ai] = String.valueOf(list.get(ai).getTelephone());
                    datagetCity[ai] = String.valueOf(list.get(ai).getCity());

                }

                recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                    GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                        public boolean onSingleTapUp(MotionEvent e) {
                            return true;
                        }
                    });

                    @Override
                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                        View child = rv.findChildViewUnder(e.getX(), e.getY());
                        if (child != null && gestureDetector.onTouchEvent(e)) {
                            int position = rv.getChildAdapterPosition(child);
                            Toast.makeText(getApplicationContext(), "Id : " + movies.get(position).getId() + " selected", Toast.LENGTH_SHORT).show();

                            //Intent i = new Intent(FormBuyerIdentificationSearching.this, PresaleDetailActivity.class);
                            Toast.makeText(FormBuyerIdentificationSearching.this, String.valueOf(movies.get(position).getId()), Toast.LENGTH_SHORT).show();
//                            i.putExtra("presales_no", String.valueOf(movies.get(position).getId()));
//                            i.putExtra("installment", String.valueOf(movies.get(position).getNik()));
//                            i.putExtra("payment", String.valueOf(movies.get(position).getName()));
//                            i.putExtra("down_payment", String.valueOf(movies.get(position).getCity()));
//                            i.putExtra("telepon", String.valueOf(movies.get(position).getTelephone()));
//                            FormBuyerIdentificationSearching.this.startActivity(i);
//                            finish();

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
            public void onFailure(Call<CustomerReponse> call, Throwable t) {

            }
        });
    }
}
