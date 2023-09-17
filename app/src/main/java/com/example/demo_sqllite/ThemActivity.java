package com.example.demo_sqllite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThemActivity extends AppCompatActivity {


    EditText txtTen, txtMoTa;
    Button btnAdd;
    Toolbar toolbar;
    Intent intent_1;
    MySQLite conn = new MySQLite(ThemActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        intent_1 = getIntent();
        txtTen = findViewById(R.id.txtTenDV);
        txtMoTa = findViewById(R.id.txtMotaDV);
        btnAdd = findViewById(R.id.btnThem);
        toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(txtTen.getText().toString())) {
                    Toast.makeText(ThemActivity.this, "Bạn chưa nhập Tên đơn vị...",  Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(txtMoTa.getText().toString())) {
                    Toast.makeText(ThemActivity.this, "Bạn chưa nhập Mô tả đơn vị....",  Toast.LENGTH_SHORT).show();
                }else {
                    String txtTenDVS = txtTen.getText().toString();
                    String txtMotaDVS = txtMoTa.getText().toString();
                    Boolean Add_DonVi = conn._Add_DonVi(txtTenDVS, txtMotaDVS);
                    if (Add_DonVi)
                    {
                        setResult(33, intent_1);
                        finish();
                    }else {
                        Toast.makeText(ThemActivity.this, "Thêm đơn vị thất bại......", 0);
                    }
                }




            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            setResult(33, intent_1);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}