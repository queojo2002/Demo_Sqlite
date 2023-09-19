package com.example.demo_sqllite;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MySQLite extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "BHTS_DEMO.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "donvi";
    private String time_default = _GetTime();


    public MySQLite(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        System.out.println("TIME NE: "+time_default+"");

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE donvi (\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "TenDV TEXT (50)  NOT NULL,\n" +
                "MoTaDV TEXT (100),\n" +
                "NgayTao TEXT (20)  NOT NULL,\n" +
                "NgayCapNhat TEXT (20)\n" +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean _Add_DonVi(String TenDV, String MotaDV)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("TenDV", TenDV);
        cv.put("MoTaDV", MotaDV);
        cv.put("NgayTao", time_default);
        cv.put("NgayCapNhat", time_default);
        long result = db.insert(TABLE_NAME, null, cv);
        db.close();
        return result != -1;
    }

    public boolean _Edit_DonVi(DonVi dv)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("TenDV", dv.getTenDV());
        cv.put("MoTaDV", dv.getMotaDV());
        cv.put("NgayCapNhat", time_default);
        long result = db.update(TABLE_NAME, cv, "ID = ?", new String[]{String.valueOf(dv.getID())});
        db.close();
        return result != -1;
    }

    public boolean _Delete_DonVi(int ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("donvi", "id = ?", new String[]{ID+""});
        db.close();
        return result != -1;
    }


    public List<DonVi> _Select_DonVi(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<DonVi> list_donvi = new ArrayList<>();
        Cursor c = null;
        if (db != null)
        {
            c = db.rawQuery("SELECT * FROM donvi", null);
            while (c.moveToNext()){
                list_donvi.add(new DonVi(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4)));
            }
            c.close();
            db.close();
            return list_donvi;
        }else {
            return null;
        }
    }



    private String _GetTime()
    {
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");

        Calendar calendar = Calendar.getInstance(timeZone);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int ss = calendar.get(Calendar.SECOND);

        return day+"/"+month+"/"+year+" " + hour+":"+minute+":"+ss;

    }

}
