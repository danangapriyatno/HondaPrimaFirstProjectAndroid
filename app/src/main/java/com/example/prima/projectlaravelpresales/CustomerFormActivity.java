package com.example.prima.projectlaravelpresales;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import Api.ApiInterface;
import Customer.CustomerGetSet;
import Customer.CustomerReponse;
import Presales.ApiClient;
import Presales.Presale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerFormActivity extends AppCompatActivity {
    Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.45.31/api/").addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    ApiInterface userclient = retrofit.create(ApiInterface.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_form);

    }
}
