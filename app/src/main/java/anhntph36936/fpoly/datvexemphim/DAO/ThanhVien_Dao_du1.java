package anhntph36936.fpoly.datvexemphim.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import anhntph36936.fpoly.datvexemphim.DBHelper.DB_Helper_du1;
import anhntph36936.fpoly.datvexemphim.Model.ThanhVien_model_du1;

public class ThanhVien_Dao_du1 {
    DB_Helper_du1 dbHelperDu1;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public ThanhVien_Dao_du1(Context context){
        dbHelperDu1 = new DB_Helper_du1(context);
        sharedPreferences = context.getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
    }
    public boolean checkDangNhap(String SDT, String matkhau) {
        SQLiteDatabase sqLiteDatabase = dbHelperDu1.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THANHVIEN WHERE sodienthoai = ? And matkhau = ?", new String[]{SDT, matkhau});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("matv", String.valueOf(cursor.getInt(0)));
            editor.putString("sodienthoai", cursor.getString(1));
            editor.putString("email", cursor.getString(2));
            editor.putString("hoten", cursor.getString(3));
            editor.putString("matkhau", cursor.getString(4));
            editor.putString("loaitaikhoan", cursor.getString(5));
            editor.commit();

            return true;
        } else {
            return false;
        }
    }
    public boolean themThanhVien(String SDT, String Email, String HoTen, String MatKhau, String LoaiTaiKhoan) {
        SQLiteDatabase sqLiteDatabase = dbHelperDu1.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sodienthoai", SDT);
        contentValues.put("email", Email);
        contentValues.put("tentv", HoTen);
        contentValues.put("matkhau", MatKhau);
        contentValues.put("loaitaikhoan", LoaiTaiKhoan);

        editor = sharedPreferences.edit();
        editor.putString("tentv", HoTen);
        editor.putString("sodienthoai", SDT);
        editor.putString("email", Email);
        editor.commit();

        long check = sqLiteDatabase.insert("THANHVIEN", null, contentValues);
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean xoaThanhVien(int matv){
        SQLiteDatabase sqLiteDatabase = dbHelperDu1.getWritableDatabase();
        long check = sqLiteDatabase.delete("THANHVIEN", "matv = ?", new String[]{String.valueOf(matv)});
        if(check == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean capNhatThanhVien(String Matv,String SDT, String Email, String HoTen, String MatKhau, String LoaiTaiKhoan){
        SQLiteDatabase sqLiteDatabase = dbHelperDu1.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("sodienthoai", SDT);
        contentValues.put("Email", Email);
        contentValues.put("tentv", HoTen);
        contentValues.put("matkhau", MatKhau);
        contentValues.put("loaitaikhoan", LoaiTaiKhoan);

        long check = sqLiteDatabase.update("THANHVIEN", contentValues, "matv = ?", new String[]{Matv});
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean capNhatMatKhau(String sdt, String oldPass, String newPass){
        SQLiteDatabase sqLiteDatabase = dbHelperDu1.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT sodienthoai, matkhau FROM THANHVIEN WHERE sodienthoai = ? AND matkhau = ?", new String[]{sdt, oldPass});
        if (cursor.getCount() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("matkhau", newPass);
            long check = sqLiteDatabase.update("THANHVIEN", contentValues, "sodienthoai = ?", new String[]{sdt});
            if (check == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }
    public ArrayList<ThanhVien_model_du1> getDSThanhVien(){
        ArrayList<ThanhVien_model_du1> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelperDu1.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THANHVIEN", null );
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new ThanhVien_model_du1(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
