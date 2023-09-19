package com.example.demo_sqllite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class ChiTietDonViActivity extends AppCompatActivity {

    Intent intent;
    Toolbar toolbar;
    TextView lbl_TenDV, lbl_MotaDV, lbl_NgayTao, lbl_NgayCapNhat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_vi);

        toolbar = findViewById(R.id.Toolbar);
        lbl_TenDV = findViewById(R.id.lb_TenDV);
        lbl_MotaDV = findViewById(R.id.lb_MoTaDV);
        lbl_NgayTao = findViewById(R.id.lb_NgayTao);
        lbl_NgayCapNhat = findViewById(R.id.lb_NgayCapNhat);
        intent = getIntent();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);
        Data_Binding_DonVi();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void Data_Binding_DonVi()
    {
        Bundle myBundle = intent.getBundleExtra("DuLieu_DonVi");
        int IDDV = myBundle.getInt("ID");
        String TenDV = myBundle.getString("TenDV");
        String MotaDV = myBundle.getString("MoTaDV");
        String NgayTao = myBundle.getString("NgayTao");
        String NgayCapNhat = myBundle.getString("NgayCapNhat");
        lbl_TenDV.setText(TenDV);
        lbl_MotaDV.setText(MotaDV);
        lbl_NgayTao.setText(NgayTao);
        lbl_NgayCapNhat.setText(NgayCapNhat);
        getSupportActionBar().setTitle("Xem chi tiết đơn vị - ID: " + IDDV+"");

    }
}