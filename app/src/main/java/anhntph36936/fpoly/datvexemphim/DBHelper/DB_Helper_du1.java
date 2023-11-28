package anhntph36936.fpoly.datvexemphim.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Helper_du1 extends SQLiteOpenHelper {
    static String DB_NAME = "du1_vexemphim";
    static int DB_VERSION = 2;

    public DB_Helper_du1(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_TheLoai = "create table THELOAI (maloai integer primary key autoincrement, tenloai text)";
        db.execSQL(tb_TheLoai);

        String tb_XuatChieu = "create table XUATCHIEU(maxuatchieu integer primary key autoincrement, ngaychieu text, thoigianchieu text)";
        db.execSQL(tb_XuatChieu);

        String tb_Phim = "create table PHIM(maphim integer primary key autoincrement, tenphim text, hinhanh text, maloai integer references THELOAI(maloai), maxuatchieu integer references XUATCHIEU(maxuatchieu))";
        db.execSQL(tb_Phim);

        String tb_ThanhVien = "create table THANHVIEN(matv integer primary key autoincrement,sodienthoai text, email text, tentv text, matkhau text, loaitaikhoan text)";
        db.execSQL(tb_ThanhVien);

        String tb_Ve = "create table VE(mave integer primary key autoincrement, soghe text,soluong text, giave text, thoigiandat text, ngaychieu text, maxuatchieu integer references XUATCHIEU(maxuatchieu), maphim integer references PHIM(maphim) )";
        db.execSQL(tb_Ve);

        String dl_ThanhVien = "INSERT INTO THANHVIEN VALUES (3, '3', 'nta@fpt', 'tienanh', 'a2', 'admin')";
//        (2,'2', 'nta@gmail.com', 'anhngo', 'a2','khachhang')" +
        db.execSQL(dl_ThanhVien);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS THELOAI");
            db.execSQL("DROP TABLE IF EXISTS PHIM");
            db.execSQL("DROP TABLE IF EXISTS XUATCHIEU");
            db.execSQL("DROP TABLE IF EXISTS THANHVIEN");
            db.execSQL("DROP TABLE IF EXISTS VE");
            onCreate(db);
        }
    }
}
