package anhntph36936.fpoly.datvexemphim.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import anhntph36936.fpoly.datvexemphim.DBHelper.DB_Helper_du1;
import anhntph36936.fpoly.datvexemphim.Model.XuatChieu_Model_du1;

public class XuatChieu_Dao_du1 {
    DB_Helper_du1 dbHelperDu1;

    public XuatChieu_Dao_du1(Context context) {
        dbHelperDu1 =new DB_Helper_du1(context);
    }
    public boolean themXuatChieu(String ngaychieu, String thoigianchieu){
        SQLiteDatabase sqLiteDatabase = dbHelperDu1.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ngaychieu", ngaychieu);
        contentValues.put("thoigianchieu", thoigianchieu);
        long check = sqLiteDatabase.insert("XUATCHIEU", null, contentValues);
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean capNhatXuatChieu(XuatChieu_Model_du1 xuatChieu_model_du1){
        SQLiteDatabase sqLiteDatabase = dbHelperDu1.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ngaychieu", xuatChieu_model_du1.getNgaychieu());
        contentValues.put("thoigianchieu", xuatChieu_model_du1.getThoigianchieu());

        long check = sqLiteDatabase.update("XUATCHIEU", contentValues, "maxuatchieu = ?", new String[]{String.valueOf(xuatChieu_model_du1.getMaxuatchieu())});
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean xoaXuatChieu(int maxuatchieu){
        SQLiteDatabase sqLiteDatabase = dbHelperDu1.getWritableDatabase();
        long check = sqLiteDatabase.delete("XUATCHIEU", "maxuatchieu = ?", new String[]{String.valueOf(maxuatchieu)});
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }
    public ArrayList<XuatChieu_Model_du1> getDSXuatChieu(){
        ArrayList<XuatChieu_Model_du1> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelperDu1.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM XUATCHIEU", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new XuatChieu_Model_du1(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
