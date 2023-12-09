package anhntph36936.fpoly.datvexemphim.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import anhntph36936.fpoly.datvexemphim.DBHelper.DB_Helper_du1;
import anhntph36936.fpoly.datvexemphim.Model.Ve_model_du1;

public class Ve_Dao_du1 {
    DB_Helper_du1 dbHelperDu1;
    SharedPreferences sharedPreferences;

    public Ve_Dao_du1(Context context) {
        dbHelperDu1 = new DB_Helper_du1(context);
        sharedPreferences = context.getSharedPreferences("GIOHANG", Context.MODE_PRIVATE);
    }
    public boolean themVe(String soghe, String soluong, String gia, String ngay, String thoigian, String tenp, String anh){
        SQLiteDatabase sqLiteDatabase = dbHelperDu1.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("soghe", soghe);
        contentValues.put("soluong", soluong);
        contentValues.put("gia", gia);
        contentValues.put("ngay", ngay);
        contentValues.put("thoigian", thoigian);
        contentValues.put("tenp", tenp);
        contentValues.put("anh", anh);
//        contentValues.put("tenphim", tenphim);
//        contentValues.put("hinhanh", hinhanh);
//        contentValues.put("thoigianchieu", thoigianchieu);
//        contentValues.put("ngaychieu", ngaychieu);
        long check = sqLiteDatabase.insert("VE", null, contentValues);
        if (check == -1){
            return false;
        } else{
            return true;
        }
    }
    public boolean xoave(int mave){

        SQLiteDatabase sqLiteDatabase = dbHelperDu1.getWritableDatabase();

        long check = sqLiteDatabase.delete("VE", "mave = ? ", new String[]{String.valueOf(mave)});
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }
    public int tongtien(){
        int tongtien = 0;
        SQLiteDatabase sqLiteDatabase = dbHelperDu1.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(v.soluong * v.gia) as TONGTIEN FROM VE", null);
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            tongtien = cursor.getInt(0);
        }
        return tongtien;

    }
     public ArrayList<Ve_model_du1> getDSVe(){
        ArrayList<Ve_model_du1> list_ve = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelperDu1.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM VE", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list_ve.add(new Ve_model_du1(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)));
            } while (cursor.moveToNext());
        }
        return list_ve;
     }

}
