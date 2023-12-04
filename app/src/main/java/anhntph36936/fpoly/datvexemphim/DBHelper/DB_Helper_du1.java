package anhntph36936.fpoly.datvexemphim.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Helper_du1 extends SQLiteOpenHelper {
    static String DB_NAME = "du1_vexemphim";
    static int DB_VERSION = 22;

    public DB_Helper_du1(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_TheLoai = "create table THELOAI (maloai integer primary key autoincrement, tenloai text)";
        db.execSQL(tb_TheLoai);

        String tb_XuatChieu = "create table XUATCHIEU(maxuatchieu integer primary key autoincrement, ngaychieu text, thoigianchieu text)";
        db.execSQL(tb_XuatChieu);

        String tb_Phim = "create table PHIM(maphim integer primary key autoincrement, tenphim text not null, hinhanh text not null, giaphim integer, maloai integer references THELOAI(maloai), maxuatchieu integer references XUATCHIEU(maxuatchieu))";
        db.execSQL(tb_Phim);

        String tb_ThanhVien = "create table THANHVIEN(" +
                "matv integer primary key autoincrement not null," +
                "sodienthoai text not null, " +
                "email text not null, " +
                "tentv text not null, " +
                "matkhau text not null, " +
                "loaitaikhoan text not null)";
        db.execSQL(tb_ThanhVien);

        String tb_Ve = "create table VE(mave integer primary key autoincrement," +
                "soghe text," +
                "soluong text," +
                "gia text," +
                "thoigiandat text," +
                "ngaychieu text," +
                "maxuatchieu integer references XUATCHIEU(maxuatchieu)," +
                "maphim integer references PHIM(maphim)," +
                "tenphim text references PHIM(tenphim))";
        db.execSQL(tb_Ve);

        db.execSQL("INSERT INTO THELOAI VALUES (1,'Kinh dị') , (2 , 'Hành động')");
        db.execSQL("INSERT INTO THANHVIEN VALUES (1,'1', 'Anh@gmail.com', 'Anh', '1', 'admin'), (2,'2', 'anhnt@gmail.com', 'Anhnt', '2', 'khachhang')");
        db.execSQL("INSERT INTO XUATCHIEU VALUES (1,'12/12/2023', '9h30') , (2 , '11/12/2023' , '10h')");
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
