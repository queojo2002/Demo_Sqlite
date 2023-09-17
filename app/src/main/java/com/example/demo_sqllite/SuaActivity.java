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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SuaActivity extends AppCompatActivity {
    Toolbar toolbar;
    Intent intent;
    int IDDonVi;
    EditText txtTenDV_Edit, txtMoTa_Edit;
    MySQLite conn = new MySQLite(SuaActivity.this);
    Button btnSua;
    private final String time_default = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(Calendar.getInstance().getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua);
        toolbar = findViewById(R.id.Toolbar);
        intent = getIntent();
        txtTenDV_Edit = findViewById(R.id.txtTenDV_edit);
        txtMoTa_Edit = findViewById(R.id.txtMotaDV_edit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);
        Data_Binding_DonVi();
        btnSua = findViewById(R.id.btnSua);


        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(txtTenDV_Edit.getText().toString())) {
                    Toast.makeText(SuaActivity.this, "Bạn chưa nhập Tên đơn vị...",  Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(txtMoTa_Edit.getText().toString())) {
                    Toast.makeText(SuaActivity.this, "Bạn chưa nhập Mô tả đơn vị....",  Toast.LENGTH_SHORT).show();
                }else {
                    String TenDV_S = txtTenDV_Edit.getText().toString();
                    String MotaDV_S = txtMoTa_Edit.getText().toString();
                    Boolean sua_donvi = conn._Edit_DonVi(new DonVi(IDDonVi, TenDV_S, MotaDV_S, time_default, time_default));
                    if (sua_donvi)
                    {
                        Toast.makeText(SuaActivity.this, "Sửa đơn vị thành công !!!!",  Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SuaActivity.this, "Sửa đơn vị thất bại....",  Toast.LENGTH_SHORT).show();
                    }


                }



            }
        });



    }

    public void Data_Binding_DonVi()
    {
        Bundle myBundle = intent.getBundleExtra("DuLieu_DonVi");
        int IDDV = myBundle.getInt("ID");
        String TenDV = myBundle.getString("TenDV");
        String MotaDV = myBundle.getString("MoTaDV");
        IDDonVi = IDDV;
        txtTenDV_Edit.setText(TenDV);
        txtMoTa_Edit.setText(MotaDV);
        getSupportActionBar().setTitle("Sửa đơn vị - ID: " + IDDV+"");

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}