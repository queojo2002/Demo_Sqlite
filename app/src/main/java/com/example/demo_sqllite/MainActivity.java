package com.example.demo_sqllite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MySQLite mySQLite = new MySQLite(MainActivity.this);
    Adapter_DonVi adapter_donVi;
    ListView lv;
    FloatingActionButton fab_add;
    ImageView edit_1, delete_1;
    SearchView SV;
    List<DonVi> myDonVi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv_1);
        fab_add = findViewById(R.id.fab_add);
        SV = findViewById(R.id.txtSearch);

        Show_Data();



        SV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter_donVi.getFilter().filter(s);
                return false;
            }
        });
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThemActivity.class);
                startActivityForResult(intent, 99);

            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent_chitiet = new Intent(MainActivity.this, ChiTietDonViActivity.class);
                Object item = adapterView.getItemAtPosition(i);
                if (item != null && item instanceof DonVi) {
                    DonVi donvi = (DonVi) item;
                    Bundle bd_donvi = new Bundle();
                    bd_donvi.putInt("ID", donvi.getID());
                    bd_donvi.putString("TenDV", donvi.getTenDV());
                    bd_donvi.putString("MoTaDV", donvi.getMotaDV());
                    bd_donvi.putString("NgayTao", donvi.getNgayTao());
                    bd_donvi.putString("NgayCapNhat", donvi.getNgayCapNhat());
                    intent_chitiet.putExtra("DuLieu_DonVi", bd_donvi);
                }
                startActivity(intent_chitiet);
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Load lại nè - Resume");
        Show_Data();
    }

    void confirmDialog(int ID)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa Đơn vị - ID: " + ID);
        builder.setMessage("Bạn có chắc chắn muốn xóa đơn vị này?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MySQLite conn = new MySQLite(MainActivity.this);
                conn._Delete_DonVi(ID);
            }
        });
    }

    void Show_Data()
    {
        myDonVi = mySQLite._Select_DonVi();
        adapter_donVi = new Adapter_DonVi(MainActivity.this, R.layout.listview_layout, myDonVi);
        lv.setAdapter(adapter_donVi);



    }


}