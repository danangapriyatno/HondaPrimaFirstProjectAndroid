package com.example.prima.projectlaravelpresales;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Api.ApiInterface;
import Customer.CustomerGetSet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormBuyerIdentificationActivity extends Activity {
    Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.45.31/api/").addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    ApiInterface userclient = retrofit.create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_buyer_identification);
        final EditText EdittextNIK = (EditText)findViewById(R.id.buyeridentificationtextviewNIK);
        final EditText EdittextNama = (EditText)findViewById(R.id.buyeridentificationtextviewNama);
        final EditText EdittextTelepon = (EditText)findViewById(R.id.buyeridentificationtextviewTelepon);
        final EditText EdittextKotaKabupaten = (EditText) findViewById(R.id.buyeridentificationtextviewKotaKabupaten);
        Button ButtonFormBuyerIdentification = (Button) findViewById(R.id.buyeridentificationButtonNext);

        ButtonFormBuyerIdentification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EdittextNIK.getText().toString().equals("")||EdittextNama.getText().toString().equals("")||EdittextTelepon.getText().toString().equals("")||EdittextKotaKabupaten.getText().toString().equals("")){
                    Toast.makeText(FormBuyerIdentificationActivity.this, "Harap lengkapi 'Form'", Toast.LENGTH_SHORT).show();
                }else{
                    final SharedPreferences shared = getSharedPreferences("datas", Context.MODE_PRIVATE);
                    SharedPreferences.Editor datasharedpreferenced = shared.edit();
                    datasharedpreferenced.putString("formbuyeridentificationNIK", EdittextNIK.getText().toString());
                    datasharedpreferenced.putString("formbuyeridentificationNama", EdittextNama.getText().toString());
                    datasharedpreferenced.putString("formbuyeridentificationTelepon", EdittextTelepon.getText().toString());
                    datasharedpreferenced.putString("formbuyeridentificationKota", EdittextKotaKabupaten.getText().toString());
                    datasharedpreferenced.commit();
//                    SharedPreferences sharedpreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
//                    String tokenusersharedpreferenced = sharedpreferences.getString("tokenuser","");
//                    CustomerGetSet customerGetSet = new CustomerGetSet("",EdittextNIK.getText().toString(),EdittextNama.getText().toString(),EdittextTelepon.getText().toString(),EdittextKotaKabupaten.getText().toString(),"");
//                    Call<CustomerGetSet> call= userclient.InsertCustomer(customerGetSet,tokenusersharedpreferenced);
//                    call.enqueue(new Callback<CustomerGetSet>() {
//                        @Override
//                        public void onResponse(Call<CustomerGetSet> call, Response<CustomerGetSet> response) {
//                            Toast.makeText(FormBuyerIdentificationActivity.this, "Data berhasil di tambahkan", Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onFailure(Call<CustomerGetSet> call, Throwable t) {
//                            Toast.makeText(FormBuyerIdentificationActivity.this, "Error Connection", Toast.LENGTH_SHORT).show();
//                        }
//                    });
                    Intent intent = new Intent(FormBuyerIdentificationActivity.this,FormNamaStnkdanBpkbActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FormBuyerIdentificationActivity.this,MenuUtamaActivity.class );
        startActivity(intent);
        finish();
    }
}
