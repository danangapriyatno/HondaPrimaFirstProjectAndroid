package com.example.prima.projectlaravelpresales;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Api.ApiInterface;
import Customer.CustomerGetSet;
import Customer.CustomerReponse;
import Vehicles.VehiclesGetSet;
import Vehicles.VehiclesReponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormUnitIdentification extends Activity  {
    Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.45.31/api/").addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    ApiInterface userclient = retrofit.create(ApiInterface.class);
    int itemselectemposition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_unit_identification);

//=================================insialisasi===============================================================//
        final TextView txtCodeStock = (TextView) findViewById(R.id.unitidentificationTxtCodeStock);
        final TextView txtName = (TextView) findViewById(R.id.unitidentificationTxtname);
        final TextView txtColour = (TextView) findViewById(R.id.unitidentificationTxtColour);
        final TextView txtMachineNo = (TextView) findViewById(R.id.unitidentificationTxtmachineno);
        final TextView txtFrameNo = (TextView) findViewById(R.id.unitidentificationTxtFrameno);
        final Button buttonsummon = (Button) findViewById(R.id.unitidentificationButtonNext);
        txtCodeStock.setText("");
        txtName.setText("");
        txtColour.setText("");
        txtMachineNo.setText("");
        txtFrameNo.setText("");
        final Spinner spinner = (Spinner)findViewById(R.id.unitidentificationSpinner);
        SharedPreferences sharedpreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
//==========================================================================================================//
        final String tokenusersharedpreferenced = sharedpreferences.getString("tokenuser","");
        Call<VehiclesReponse> call = userclient.GetVehicle(tokenusersharedpreferenced.toString());
        call.enqueue(new Callback<VehiclesReponse>() {
            @Override
            public void onResponse(Call<VehiclesReponse> call, Response<VehiclesReponse> response) {
                final List<VehiclesGetSet> dataspiner = response.body().getData();
                final String[] datagetId = new String[dataspiner.size()];
                final String[] datagetName = new String[dataspiner.size()];
                final String[] datagetCodeStock = new String[dataspiner.size()];
                final String[] datagetMachineNo = new String[dataspiner.size()];
                final String[] datagetFrameNo = new String[dataspiner.size()];
                final String[] datagetColour = new String[dataspiner.size()];
                final String[] datagetCustomerid = new String[dataspiner.size()];
                final String[] datagetVehiclesTypeId = new String[dataspiner.size()];
                for (int ai=0;ai<=dataspiner.size()-1;ai++){
                    datagetId[ai] =String.valueOf(dataspiner.get(ai).getId());
                    datagetColour[ai] =String.valueOf(dataspiner.get(ai).getColour());
                    datagetName[ai] = String.valueOf(dataspiner.get(ai).getName());
                    datagetCodeStock[ai] = String.valueOf(dataspiner.get(ai).getCode_stock());
                    datagetMachineNo[ai] = String.valueOf(dataspiner.get(ai).getMachine_no());
                    datagetFrameNo[ai] = String.valueOf(dataspiner.get(ai).getFrame_no());
                    datagetCustomerid[ai] = String.valueOf(dataspiner.get(ai).getCustomer_id());
                    datagetVehiclesTypeId[ai] = String.valueOf(dataspiner.get(ai).getVehicles_type_id());


                }
                ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(FormUnitIdentification.this,android.R.layout.simple_spinner_item, datagetName);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spinnerArrayAdapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        itemselectemposition=i;
                        txtCodeStock.setText("Kode Stock    = "+String.valueOf(datagetCodeStock[i]));
                        txtName.setText     ("Tipe Motor    = "+String.valueOf(datagetName[i]));
                        txtColour.setText   ("Warna         = "+String.valueOf(datagetColour[i]));
                        txtFrameNo.setText  ("Nomor Rangka  = "+String.valueOf(datagetFrameNo[i]));
                        txtMachineNo.setText("Nomor Mesin   = "+String.valueOf(datagetMachineNo[i]));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                buttonsummon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final SharedPreferences shared = getSharedPreferences("datas", Context.MODE_PRIVATE);
                        SharedPreferences.Editor datasharedpreferenced = shared.edit();
                        datasharedpreferenced.putString("formunitidentificationId", String.valueOf(datagetId[itemselectemposition]));
                        datasharedpreferenced.putString("formunitidentificationColour", String.valueOf(datagetColour[itemselectemposition]));
                        datasharedpreferenced.putString("formunitidentificationName", String.valueOf(datagetName[itemselectemposition]));
                        datasharedpreferenced.putString("formunitidentificationCodeStock", String.valueOf(datagetCodeStock[itemselectemposition]));
                        datasharedpreferenced.putString("formunitidentificationmachineNo", String.valueOf(datagetMachineNo[itemselectemposition]));
                        datasharedpreferenced.putString("formunitidentificationFrameNo", String.valueOf(datagetFrameNo[itemselectemposition]));
                        datasharedpreferenced.putString("formunitidentificationCustomerId", String.valueOf(datagetCustomerid[itemselectemposition]));
                        datasharedpreferenced.putString("formunitidentificationTypeId", String.valueOf(datagetVehiclesTypeId[itemselectemposition]));
                        datasharedpreferenced.commit();
                        Intent intent = new Intent(FormUnitIdentification.this,FormPresales.class);
                        startActivity(intent);
                        finish();
//                        SharedPreferences sharedpreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
//                        String tokenusersharedpreferenced = sharedpreferences.getString("tokenuser","");
//                        Call<VehiclesGetSet> call= userclient.Updatedatavehicle(Integer.parseInt(datagetId[itemselectemposition]),String.valueOf(datagetCodeStock[itemselectemposition]),String.valueOf(datagetName[itemselectemposition]),String.valueOf(datagetColour[itemselectemposition]),String.valueOf(datagetFrameNo[itemselectemposition]),"0",String.valueOf(datagetMachineNo[itemselectemposition]),String.valueOf(datagetCustomerid[itemselectemposition]),String.valueOf(datagetVehiclesTypeId[itemselectemposition]),tokenusersharedpreferenced);
//                        call.enqueue(new Callback<VehiclesGetSet>() {
//                            @Override
//                            public void onResponse(Call<VehiclesGetSet> call, Response<VehiclesGetSet> response) {
//                                Toast.makeText(FormUnitIdentification.this, String.valueOf(datagetId[itemselectemposition]), Toast.LENGTH_SHORT).show();
//
//                            }
//
//                            @Override
//                            public void onFailure(Call<VehiclesGetSet> call, Throwable t) {
//
//                            }
//                        });
                    }
                });

            }


            @Override
            public void onFailure(Call<VehiclesReponse> call, Throwable t) {

            }
        });
    }
}
