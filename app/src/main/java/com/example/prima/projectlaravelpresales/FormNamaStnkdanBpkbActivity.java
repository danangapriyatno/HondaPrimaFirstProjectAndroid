package com.example.prima.projectlaravelpresales;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import Api.ApiInterface;
import Customer.CustomerGetSet;
import Customer.CustomerReponse;
import Customer_Term.Customer_TermGetSet;
import Presales.ApiClient;
import Presales.Presale;
import Presales.PresaleResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormNamaStnkdanBpkbActivity extends AppCompatActivity {
    Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.45.31/api/").addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    ApiInterface userclient = retrofit.create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_nama_stnkdan_bpkb);
        //========================================================inisialisasi=========================================//
        final EditText txtNIK = (EditText) findViewById(R.id.namastnkdanbpkedittextNIK);
        final EditText txtNama = (EditText) findViewById(R.id.namastnkdanbpkedittextNama);
        final EditText txtTelepon = (EditText) findViewById(R.id.namastnkdanbpkedittextTelepon);
        final EditText txtKotaKabupaten =(EditText) findViewById(R.id.namastnkdanbpkedittextKotaKabupaten);

        //=============================================================================================================//
        setCheckBoxListener();
        SharedPreferences sharedpreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        final String tokenusersharedpreferenced = sharedpreferences.getString("tokenuser","");
        Button ButtonNext = (Button)  findViewById(R.id.namastnkdanbpkbuttonNext);
        ButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SharedPreferences shared = getSharedPreferences("datas", Context.MODE_PRIVATE);
                SharedPreferences.Editor datasharedpreferenced = shared.edit();
                datasharedpreferenced.putString("formNamaSTNKdanBPKBNIK", txtNIK.getText().toString());
                datasharedpreferenced.putString("formNamaSTNKdanBPKBNama", txtNama.getText().toString());
                datasharedpreferenced.putString("formNamaSTNKdanBPKBTelepon", txtTelepon.getText().toString());
                datasharedpreferenced.putString("formNamaSTNKdanBPKBKotaLabupaten", txtKotaKabupaten.getText().toString());
                datasharedpreferenced.commit();
                Intent NextPage = new Intent(FormNamaStnkdanBpkbActivity.this,FormUnitIdentification.class);
                startActivity(NextPage);
                finish();
//                Call<CustomerReponse> call = userclient.GetCustomerData(tokenusersharedpreferenced.toString());
//                call.enqueue(new Callback<CustomerReponse>() {
//                    @Override
//                    public void onResponse(Call<CustomerReponse> call, Response<CustomerReponse> response) {
//                        List<CustomerGetSet>  customer = response.body().getData();
//
//                        for (int ai=0;ai<=customer.size()-1;ai++){
//
//                            if(String.valueOf(customer.get(ai).getNik()).equals("321542234")){
//                                Toast.makeText(FormNamaStnkdanBpkbActivity.this,String.valueOf(customer.get(ai).getName()), Toast.LENGTH_SHORT).show();SharedPreferences sharedpreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
//                                String tokenusersharedpreferenced = sharedpreferences.getString("tokenuser","");
//                                Customer_TermGetSet customer_termGetSet = new Customer_TermGetSet("5",txtNIK.getText().toString(),txtNama.getText().toString(),txtTelepon.getText().toString(),String.valueOf(customer.get(ai).getId()),txtKotaKabupaten.getText().toString());
//                                Call<Customer_TermGetSet> calll= userclient.InsertCustomer_Term(customer_termGetSet,tokenusersharedpreferenced);
//                                calll.enqueue(new Callback<Customer_TermGetSet>() {
//                                    @Override
//                                    public void onResponse(Call<Customer_TermGetSet> call, Response<Customer_TermGetSet> response) {
//                                        Toast.makeText(FormNamaStnkdanBpkbActivity.this, "Data Berhasil", Toast.LENGTH_SHORT).show();
//                                    }
//
//                                    @Override
//                                    public void onFailure(Call<Customer_TermGetSet> call, Throwable t) {
//                                        Toast.makeText(FormNamaStnkdanBpkbActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                            }
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<CustomerReponse> call, Throwable t) {
//                        Toast.makeText(FormNamaStnkdanBpkbActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });
    }
    private void setCheckBoxListener() {
        CheckBox sama = (CheckBox) findViewById(R.id.namastnkdanbpkcheckBox);
        sama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()) {
                    final EditText txtNIK = (EditText) findViewById(R.id.namastnkdanbpkedittextNIK);
                    final EditText txtNama = (EditText) findViewById(R.id.namastnkdanbpkedittextNama);
                    final EditText txtTelepon = (EditText) findViewById(R.id.namastnkdanbpkedittextTelepon);
                    final EditText txtKotaKabupaten =(EditText) findViewById(R.id.namastnkdanbpkedittextKotaKabupaten);
                    final SharedPreferences shared = getSharedPreferences("datas", Context.MODE_PRIVATE);
                    String NIK = shared.getString("formbuyeridentificationNIK","");
                    String Nama = shared.getString("formbuyeridentificationNama","");
                    String Telepon = shared.getString("formbuyeridentificationTelepon","");
                    String KotaKabupaten = shared.getString("formbuyeridentificationKota","");
                    txtNama.setText(Nama);
                    txtNIK.setText(NIK);
                    txtTelepon.setText(Telepon);
                    txtKotaKabupaten.setText(KotaKabupaten);
                }
            }
        });
    }

}
