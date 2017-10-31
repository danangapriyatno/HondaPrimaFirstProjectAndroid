package com.example.prima.projectlaravelpresales;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import Api.ApiInterface;
import PembuatanToken.Login;
import PembuatanToken.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends Activity {
    Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.45.31/api/v1/").addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    ApiInterface userclient = retrofit.create(ApiInterface.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button tombollogin = (Button) findViewById(R.id.loginactivitybuttonlogin);

        tombollogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    private static  String token;
    private void login(){
        final ProgressBar simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);
        simpleProgressBar.setVisibility(View.VISIBLE);
        final SharedPreferences sharedpreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        EditText emailedittext = (EditText) findViewById(R.id.loginactivityediitextemail);
        EditText passwordedittext = (EditText) findViewById(R.id.loginactivityediitextpassword);
        Login login = new Login(emailedittext.getText().toString(),passwordedittext.getText().toString());
        Call<User> call= userclient.login(login);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
                    token = response.body().getToken();
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("tokenuser", token);
                    editor.commit();
                    simpleProgressBar.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(LoginActivity.this,FormBuyerIdentificationSearching.class);
                    startActivity(intent);
                    finish();

                }else{
                    simpleProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(LoginActivity.this, "Username atau Password salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                simpleProgressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(LoginActivity.this, "Koneksi Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private  void getsecret(){
        Call<ResponseBody> call = userclient.getToken(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try{
                        Toast.makeText(LoginActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(LoginActivity.this, "gagal register token", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
