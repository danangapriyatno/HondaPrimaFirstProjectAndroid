package com.example.prima.projectlaravelpresales;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;
import java.util.List;

import Api.ApiInterface;
import Customer.CustomerGetSet;
import Customer.CustomerReponse;
import Customer_Term.Customer_TermGetSet;
import ImageUpload.ImageGetSet;
import ImageUpload.ImageGetSetSecond;
import Presales.Presale;
import Vehicles.VehiclesGetSet;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FormUploadImage extends Activity  implements EasyPermissions.PermissionCallbacks{
    private static final String TAG = FormUploadImage.class.getSimpleName();
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private static final String SERVER_PATH = "http://192.168.45.31/api/";
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_upload_image);
        Button buttonsubmit = (Button)findViewById(R.id.formuploadimagesubmit);
        Button selectUploadButton = (Button)findViewById(R.id.uploadimageButtonUpload);
        selectUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                openGalleryIntent.setType("image/*");
                startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
            }
        });
        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuyerIdentification();
                FormSTNKdanBPKB();
                UnitIdentification();
                FormPresalessss();
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, FormUploadImage.this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK) {
            uri = data.getData();
            if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                String filePath = getRealPathFromURIPath(uri, FormUploadImage.this);
                File file = new File(filePath);
                Log.d(TAG, "Filename " + file.getName());
                //RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("image", file.getName(), mFile);
                RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(SERVER_PATH)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ApiInterface uploadImage = retrofit.create(ApiInterface.class);
                final SharedPreferences sharedpreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
                final String tokenusersharedpreferenced = sharedpreferences.getString("tokenuser","");
                RequestBody presale_no = RequestBody.create(MediaType.parse("text/plain"), "1");

                Call<ImageGetSet> fileUpload = uploadImage.uploadImageKelengkapan(filename, fileToUpload,presale_no,tokenusersharedpreferenced);
                //Call<ImageResponse> fileUpload = uploadImage.uploadFile( filename,fileToUpload);
                fileUpload.enqueue(new Callback<ImageGetSet>() {
                    @Override
                    public void onResponse(Call<ImageGetSet> call, Response<ImageGetSet> response) {
                        Toast.makeText(FormUploadImage.this, "Response " + response.raw().message(), Toast.LENGTH_LONG).show();


//                        Intent intent = new Intent(MainActivity.this,ShowImages.class);
//                        startActivity(intent);
//                        finish();
                    }

                    @Override
                    public void onFailure(Call<ImageGetSet> call, Throwable t) {
                        Toast.makeText(FormUploadImage.this, "Error CUK", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "io", Toast.LENGTH_SHORT).show();
//                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }
    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if(uri != null){
            String filePath = getRealPathFromURIPath(uri, FormUploadImage.this);
            File file = new File(filePath);
            RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("image", file.getName(), mFile);
            RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SERVER_PATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiInterface uploadImage = retrofit.create(ApiInterface.class);
            final SharedPreferences sharedpreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
            final String tokenusersharedpreferenced = sharedpreferences.getString("tokenuser","");
            RequestBody presale_no = RequestBody.create(MediaType.parse("text/plain"), "1");
            ImageGetSetSecond imageGetSetSecond = new ImageGetSetSecond("1");
            Call<ImageGetSet> fileUpload = uploadImage.uploadImageKelengkapan(filename, fileToUpload,presale_no,tokenusersharedpreferenced);
            //Call<ImageResponse> fileUpload = uploadImage.uploadFile( filename, fileToUpload);
            fileUpload.enqueue(new Callback<ImageGetSet>() {
                @Override
                public void onResponse(Call<ImageGetSet> call, Response<ImageGetSet> response) {
                    Toast.makeText(FormUploadImage.this, "Success " + response.message(), Toast.LENGTH_LONG).show();
                    Toast.makeText(FormUploadImage.this, "Success " + response.body().toString(), Toast.LENGTH_LONG).show();
                }
                @Override
                public void onFailure(Call<ImageGetSet> call, Throwable t) {
                    Log.d(TAG, "Error " + t.getMessage());
                }
            });
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");
    }
    public void onBackPressed() {
//        Intent intent = new Intent(MainActivity.this,MainMenuActivity.class);
//        startActivity(intent);
//        finish();
    }

    public void BuyerIdentification(){
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.45.31/api/").addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        ApiInterface userclient = retrofit.create(ApiInterface.class);
        SharedPreferences sharedpreferenced = getSharedPreferences("datas", Context.MODE_PRIVATE);
        String NIK = sharedpreferenced.getString("formbuyeridentificationNIK","");
        String Nama = sharedpreferenced.getString("formbuyeridentificationNama","");
        String Telepon = sharedpreferenced.getString("formbuyeridentificationTelepon","");
        String KotaKabupaten = sharedpreferenced.getString("formbuyeridentificationKota","");
        SharedPreferences sharedpreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
                    String tokenusersharedpreferenced = sharedpreferences.getString("tokenuser","");
                    CustomerGetSet customerGetSet = new CustomerGetSet("",NIK,Nama,Telepon,KotaKabupaten,"");
                    Call<CustomerGetSet> call= userclient.InsertCustomer(customerGetSet,tokenusersharedpreferenced);
                    call.enqueue(new Callback<CustomerGetSet>() {
                        @Override
                        public void onResponse(Call<CustomerGetSet> call, Response<CustomerGetSet> response) {
                            Toast.makeText(FormUploadImage.this, "Data berhasil di tambahkan", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<CustomerGetSet> call, Throwable t) {
                            Toast.makeText(FormUploadImage.this, "Error Connection", Toast.LENGTH_SHORT).show();
                        }
                    });
    }
    public void FormSTNKdanBPKB(){
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.45.31/api/").addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        final ApiInterface userclient = retrofit.create(ApiInterface.class);
        SharedPreferences sharedpreferenced = getSharedPreferences("datas", Context.MODE_PRIVATE);
        final String NIK = sharedpreferenced.getString("formNamaSTNKdanBPKBNIK","");
        final String Nama = sharedpreferenced.getString("formNamaSTNKdanBPKBNama","");
        final String Telepon = sharedpreferenced.getString("formNamaSTNKdanBPKBTelepon","");
        final String KotaKabupaten = sharedpreferenced.getString("formNamaSTNKdanBPKBKotaLabupaten","");
        SharedPreferences sharedpreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        final String tokenusersharedpreferenced = sharedpreferences.getString("tokenuser","");
                Call<CustomerReponse> call = userclient.GetCustomerData(tokenusersharedpreferenced.toString());
                call.enqueue(new Callback<CustomerReponse>() {
                    @Override
                    public void onResponse(Call<CustomerReponse> call, Response<CustomerReponse> response) {
                        List<CustomerGetSet>  customer = response.body().getData();

                        for (int ai=0;ai<=customer.size()-1;ai++){

                            if(String.valueOf(customer.get(ai).getNik()).equals(String.valueOf(NIK))){
                                String CustomerId = customer.get(ai).getId();
                                final SharedPreferences shared = getSharedPreferences("datas", Context.MODE_PRIVATE);
                                SharedPreferences.Editor datasharedpreferenced = shared.edit();
                                datasharedpreferenced.putString("customerid", CustomerId);
                                datasharedpreferenced.commit();
                                Toast.makeText(FormUploadImage.this,String.valueOf(customer.get(ai).getName()), Toast.LENGTH_SHORT).show();SharedPreferences sharedpreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
                                String tokenusersharedpreferenced = sharedpreferences.getString("tokenuser","");
                                Customer_TermGetSet customer_termGetSet = new Customer_TermGetSet("",NIK,Nama,Telepon,CustomerId,KotaKabupaten);
                                Call<Customer_TermGetSet> calll= userclient.InsertCustomer_Term(customer_termGetSet,tokenusersharedpreferenced);
                                calll.enqueue(new Callback<Customer_TermGetSet>() {
                                    @Override
                                    public void onResponse(Call<Customer_TermGetSet> call, Response<Customer_TermGetSet> response) {
                                        Toast.makeText(FormUploadImage.this, "Data Berhasil", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<Customer_TermGetSet> call, Throwable t) {
                                        Toast.makeText(FormUploadImage.this, "Error", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<CustomerReponse> call, Throwable t) {
                        Toast.makeText(FormUploadImage.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void UnitIdentification(){
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.45.31/api/").addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        ApiInterface userclient = retrofit.create(ApiInterface.class);
        SharedPreferences sharedpreferenced = getSharedPreferences("datas", Context.MODE_PRIVATE);
        final String Id = sharedpreferenced.getString("formunitidentificationId","");
        final String Colour = sharedpreferenced.getString("formunitidentificationColour","");
        final String Name = sharedpreferenced.getString("formunitidentificationName","");
        final String CodeStock = sharedpreferenced.getString("formunitidentificationCodeStock","");
        final String MachineNo = sharedpreferenced.getString("formunitidentificationmachineNo","");
        final String FrameNo = sharedpreferenced.getString("formunitidentificationFrameNo","");
        final String CustomerId = sharedpreferenced.getString("customerid","");
        final String TypeId = sharedpreferenced.getString("formunitidentificationTypeId","");
        Toast.makeText(this, Id, Toast.LENGTH_SHORT).show();
        SharedPreferences sharedpreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        String tokenusersharedpreferenced = sharedpreferences.getString("tokenuser","");
        Call<VehiclesGetSet> call= userclient.Updatedatavehicle(Integer.parseInt(Id),CodeStock,Name,Colour,FrameNo,"0",MachineNo,CustomerId,TypeId,tokenusersharedpreferenced);
        call.enqueue(new Callback<VehiclesGetSet>() {
            @Override
            public void onResponse(Call<VehiclesGetSet> call, Response<VehiclesGetSet> response) {
                Toast.makeText(FormUploadImage.this, "Berhasil", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<VehiclesGetSet> call, Throwable t) {
            }
        });
    }
    public void FormPresalessss(){
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.45.31/api/").addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        ApiInterface userclient = retrofit.create(ApiInterface.class);
        SharedPreferences sharedpreferenced = getSharedPreferences("datas", Context.MODE_PRIVATE);
        final String CashKredit = sharedpreferenced.getString("formpresalesCashKredit","");
        final String OTR = sharedpreferenced.getString("formpresalesOTR","");
        final String UangMuka = sharedpreferenced.getString("formpresalesUangMuka","");
        final String Discount = sharedpreferenced.getString("formpresalesDiscount","");
        final String Leasing = sharedpreferenced.getString("formpresalesLeasing","");
        final String Angsuran = sharedpreferenced.getString("formpresalesAngsuran","");
        final String Tenor = sharedpreferenced.getString("formpresalesTenor","");
        final String Program = sharedpreferenced.getString("formpresalesProgram","");
        final String Vehicle_id = sharedpreferenced.getString("formunitidentificationId","");
        final String CustomerId = sharedpreferenced.getString("customerid","");
        int VehicleIdInt = Integer.parseInt(Vehicle_id);
        int CustomerIdInt = Integer.parseInt(CustomerId);
        SharedPreferences sharedpreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        String tokenusersharedpreferenced = sharedpreferences.getString("tokenuser","");
        Presale presale = new Presale("",CashKredit,OTR,UangMuka,Discount,Leasing,Angsuran,Tenor,Program,1,VehicleIdInt,CustomerIdInt,"1");
        Call<Presale> calll= userclient.InsertPresale(presale,tokenusersharedpreferenced);
        calll.enqueue(new Callback<Presale>() {
            @Override
            public void onResponse(Call<Presale> call, Response<Presale> response) {
                Toast.makeText(FormUploadImage.this, "Data berhasil di tambhkan", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Presale> call, Throwable t) {
                Toast.makeText(FormUploadImage.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

