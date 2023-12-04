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
    ArrayList<Ve_model_du1> list;

    public Ve_Dao_du1(Context context){
        dbHelperDu1 = new DB_Helper_du1(context);
        sharedPreferences = context.getSharedPreferences("GIOHANG", context.MODE_PRIVATE);
    }
    public ArrayList<Ve_model_du1> getDSVe(){
        list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelperDu1.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT v.soghe, v.soluong, v.gia, p.tenphim, p.hinhanh, v.thoigiandat, v.ngaychieu FROM VE v, PHIM p WHERE v.maphim = p.maphim", null);
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            do {
                list.add(new Ve_model_du1(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4) ,
                        cursor.getString(5),
                        cursor.getString(6)));
            }while (cursor.moveToNext());
    }
        return list;
}
    public boolean themve(String Ghe, String SoLuong, String Gia, String TenPhim, String ThoiGianDat, String NgayChieu){
        SQLiteDatabase sqLiteDatabase = dbHelperDu1.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT v.soghe, v.soluong, v.gia, p.tenphim, p.hinhanh, v.thoigiandat, v.ngaychieu FROM VE v, PHIM p WHERE v.maphim = p.maphim ", null);
        ContentValues contentValues = new ContentValues();
        contentValues.put("soghe", Ghe);
        contentValues.put("soluong", SoLuong);
        contentValues.put("gia", Gia);
        contentValues.put("tenphim", TenPhim);
        contentValues.put("thoigiandat", ThoiGianDat);
        contentValues.put("ngaychieu", NgayChieu);


        long check = sqLiteDatabase.insert("VE",null, contentValues);
        if (check == -1){
            return false;
        } else {
            return true;
        }
    }
    public boolean xoave(int mave){
        SQLiteDatabase sqLiteDatabase= dbHelperDu1.getWritableDatabase();
        long check = sqLiteDatabase.delete("VE", "mave = ? ", new String[]{String.valueOf(mave)});
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }

}
