package com.example.demo_sqllite;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter_DonVi extends ArrayAdapter<DonVi> {

    Activity context;
    int IdLayout;
    List<DonVi> list_dv_1, list_dv_2;

    public Adapter_DonVi(Activity context_1, int IdLayout_1, List<DonVi> myDonVi_1) {
        super(context_1, IdLayout_1, myDonVi_1);
        this.context = context_1;
        this.IdLayout = IdLayout_1;
        this.list_dv_1 = myDonVi_1;
        this.list_dv_2 = myDonVi_1;
    }


    @Override
    public int getCount() {
        return list_dv_2.size();
    }


    @Override
    public DonVi getItem(int position) {
        return list_dv_2.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = context.getLayoutInflater().inflate(IdLayout, null);
        }
        TextView txt_ten = convertView.findViewById(R.id.txtTen);
        ImageView editButton = convertView.findViewById(R.id.edit);
        ImageView deleteButton = convertView.findViewById(R.id.delete);
        DonVi donvi = getItem(position);
        txt_ten.setText(donvi.getTenDV());
        final Context context = parent.getContext();
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SuaActivity.class);
                Bundle bd_donvi = new Bundle();
                bd_donvi.putInt("ID", donvi.getID());
                bd_donvi.putString("TenDV", donvi.getTenDV());
                bd_donvi.putString("MoTaDV", donvi.getMotaDV());
                intent.putExtra("DuLieu_DonVi", bd_donvi);
                context.startActivity(intent);

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Adapter_DonVi.this.getContext());
                builder.setTitle("Xóa Đơn vị - ID: " + donvi.getID());
                builder.setMessage("Bạn có chắc chắn muốn xóa đơn vị này?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MySQLite conn = new MySQLite(Adapter_DonVi.this.getContext());
                        conn._Delete_DonVi(donvi.getID());
                        ((MainActivity)context).onResume();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();


            }
        });
        return convertView;
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                if (constraint == null || constraint.length() == 0) {
                    results.count = list_dv_1.size();
                    results.values = list_dv_1;
                } else {
                    String txtSearch = constraint.toString().toLowerCase().trim();
                    List<DonVi> donvi_loc = new ArrayList<>();
                    for (DonVi donVi : list_dv_1) {
                        if (donVi.getTenDV().toLowerCase().contains(txtSearch)) {
                            donvi_loc.add(donVi);
                        }
                    }
                    results.count = donvi_loc.size();
                    results.values = donvi_loc;
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list_dv_2 = (List<DonVi>) results.values;
                notifyDataSetChanged();
            }
        };
    }



}
