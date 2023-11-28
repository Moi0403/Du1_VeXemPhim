package anhntph36936.fpoly.datvexemphim.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import anhntph36936.fpoly.datvexemphim.DBHelper.DB_Helper_du1;
import anhntph36936.fpoly.datvexemphim.Model.TheLoai_model_du1;

public class TheLoai_Dao_du1 {
    DB_Helper_du1 dbHelperDu1;

    public TheLoai_Dao_du1(Context context){
        dbHelperDu1 = new DB_Helper_du1(context);
    }
    public boolean themTheLoai(String tenloai){
        SQLiteDatabase sqLiteDatabase = dbHelperDu1.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai",tenloai );
        long check = sqLiteDatabase.insert("THELOAI", null, contentValues);
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }public boolean capNhatTheLoai(TheLoai_model_du1 theLoai){
        SQLiteDatabase sqLiteDatabase = dbHelperDu1.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai", theLoai.getTenloai());
        long check = sqLiteDatabase.update("THELOAI",contentValues, "maloai = ?", new String[]{String.valueOf(theLoai.getMaloai())});
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean xoaTheLoai(int maloai){
        SQLiteDatabase sqLiteDatabase = dbHelperDu1.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THELOAI WHERE maloai =?", new String[]{String.valueOf(maloai)});

        long check = sqLiteDatabase.delete("THELOAI", "maloai = ?", new String[]{String.valueOf(maloai)});
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }
    public ArrayList<TheLoai_model_du1> getDSTheLoai(){
        ArrayList<TheLoai_model_du1> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelperDu1.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THELOAI", null);

        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new TheLoai_model_du1(cursor.getInt(0), cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
