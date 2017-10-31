package com.example.prima.projectlaravelpresales;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import Api.ApiInterface;
import Customer_Term.Customer_TermGetSet;
import Presales.Presale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormPresales extends Activity  implements RadioGroup.OnCheckedChangeListener {
    String PaymentData;
    Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.45.31/api/").addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    ApiInterface userclient = retrofit.create(ApiInterface.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_presales);
        PaymentData="";
        RadioButton radioButtonCash = (RadioButton) findViewById(R.id.presalesCheckBoxCash);
        RadioButton radioButtonedit = (RadioButton) findViewById(R.id.presalesCheckBoxCedit);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.presaleactivityRadioGruop);
        final EditText txtOTR = (EditText) findViewById(R.id.presaleactivityEdittextOTR);
        final EditText txtUangMuka = (EditText) findViewById(R.id.presaleactivityEdittextUangMuka);
        final EditText txtDiscount = (EditText) findViewById(R.id.presaleactivityEdittextDiscount);
        final EditText txtLeasing = (EditText) findViewById(R.id.presaleactivityEdittextLeasing);
        final EditText txtAngsuran = (EditText) findViewById(R.id.presaleactivityEdittextAngsuran);
        final EditText txtTenor = (EditText) findViewById(R.id.presaleactivityEdittextTenor);
        final EditText txtProgram = (EditText) findViewById(R.id.presaleactivityEdittextProgram);
        Button buttonNext = (Button) findViewById(R.id.presaleactivityButtonNext);
        radioGroup.setOnCheckedChangeListener(this);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(PaymentData.equals("")||txtOTR.getText().toString().equals("")||txtUangMuka.getText().toString().equals("")||txtDiscount.getText().toString().equals("")||txtLeasing.getText().toString().equals("")||txtAngsuran.getText().toString().equals("")||txtTenor.getText().toString().equals("")||txtProgram.getText().toString().equals("")) {

                }else{
                    final SharedPreferences shared = getSharedPreferences("datas", Context.MODE_PRIVATE);
                    SharedPreferences.Editor datasharedpreferenced = shared.edit();
                    datasharedpreferenced.putString("formpresalesCashKredit",PaymentData);
                    datasharedpreferenced.putString("formpresalesOTR", txtOTR.getText().toString());
                    datasharedpreferenced.putString("formpresalesUangMuka", txtUangMuka.getText().toString());
                    datasharedpreferenced.putString("formpresalesDiscount", txtDiscount.getText().toString());
                    datasharedpreferenced.putString("formpresalesLeasing", txtLeasing.getText().toString());
                    datasharedpreferenced.putString("formpresalesAngsuran", txtAngsuran.getText().toString());
                    datasharedpreferenced.putString("formpresalesTenor", txtTenor.getText().toString());
                    datasharedpreferenced.putString("formpresalesProgram", txtProgram.getText().toString());
                    datasharedpreferenced.commit();
                    Intent intent = new Intent(FormPresales.this,FormUploadImage.class);
                    startActivity(intent);
                    finish();
                }


//                SharedPreferences sharedpreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
//                String tokenusersharedpreferenced = sharedpreferences.getString("tokenuser","");
//                if(PaymentData.equals("")||txtOTR.getText().toString().equals("")||txtUangMuka.getText().toString().equals("")||txtDiscount.getText().toString().equals("")||txtLeasing.getText().toString().equals("")||txtAngsuran.getText().toString().equals("")||txtTenor.getText().toString().equals("")||txtProgram.getText().toString().equals("")){
//                    Toast.makeText(FormPresales.this, "Lengkapi Form", Toast.LENGTH_SHORT).show();
//                }else{
//                    Presale presale = new Presale("",PaymentData,txtOTR.getText().toString(),txtUangMuka.getText().toString(),txtDiscount.getText().toString(),txtLeasing.getText().toString(),txtAngsuran.getText().toString(),txtTenor.getText().toString(),txtProgram.getText().toString(),1,1,1,"1");
//                    Call<Presale> calll= userclient.InsertPresale(presale,tokenusersharedpreferenced);
//                    calll.enqueue(new Callback<Presale>() {
//                        @Override
//                        public void onResponse(Call<Presale> call, Response<Presale> response) {
//                            Toast.makeText(FormPresales.this, "Data berhasil di tambhkan", Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onFailure(Call<Presale> call, Throwable t) {
//                            Toast.makeText(FormPresales.this, "Error", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        if(i==R.id.presalesCheckBoxCash)
        {
            PaymentData="Cash";
            Toast.makeText(this, "Anda memilih 'cash'",
                    Toast.LENGTH_SHORT).show();

        }
        if(i==R.id.presalesCheckBoxCedit)
        {
            PaymentData="Kredit";
            Toast.makeText(this, "Anda Memilih 'kredit'", Toast.LENGTH_SHORT).show();
        }
    }
}
