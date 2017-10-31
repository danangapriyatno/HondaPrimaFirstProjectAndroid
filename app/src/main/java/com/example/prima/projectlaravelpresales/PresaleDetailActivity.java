package com.example.prima.projectlaravelpresales;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PresaleDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presale_detail);

        TextView presales_no = (TextView) findViewById(R.id.presaleactivitypresalesno);
        presales_no.setText(getIntent().getStringExtra("presales_no"));

        TextView installment = (TextView) findViewById(R.id.presaleactivityinstallment);
        installment.setText(getIntent().getStringExtra("installment"));

        TextView payment = (TextView) findViewById(R.id.presaleactivitypayment);
        payment.setText(getIntent().getStringExtra("payment"));

        TextView down_payment = (TextView) findViewById(R.id.presaleactivitydownpayment);
        down_payment.setText(getIntent().getStringExtra("down_payment"));

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PresaleDetailActivity.this,MenuUtamaActivity.class);
        startActivity(intent);
        finish();

    }
}
