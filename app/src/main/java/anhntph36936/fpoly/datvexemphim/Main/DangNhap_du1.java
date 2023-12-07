package anhntph36936.fpoly.datvexemphim.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import anhntph36936.fpoly.datvexemphim.DAO.ThanhVien_Dao_du1;
import anhntph36936.fpoly.datvexemphim.DBHelper.DB_Helper_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class DangNhap_du1 extends AppCompatActivity {
    EditText edsdtDN, edPassDN;
    Button btnDangnhapDN, btnDangKiDN;
    CheckBox checkLuu;
    ThanhVien_Dao_du1 thanhVien_dao_du1;
    String loaiTK, tenTK;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap_du1);
        edsdtDN = findViewById(R.id.edsdtDN);
        edPassDN = findViewById(R.id.edPassDN);
        btnDangnhapDN = findViewById(R.id.btnDangnhapDN);
        btnDangKiDN = findViewById(R.id.btnDangkiDN);
        checkLuu = findViewById(R.id.cbLuumk);

        thanhVien_dao_du1 = new ThanhVien_Dao_du1(this);
        DB_Helper_du1 dbHelperDu1 = new DB_Helper_du1(this);
        SQLiteDatabase sqLiteDatabase = dbHelperDu1.getReadableDatabase();
        dbHelperDu1.close();

        SharedPreferences pref = getSharedPreferences("DN_FILE", MODE_PRIVATE);
        String sdt = pref.getString("SODIENTHOAI", "");
        String pass = pref.getString("PASSWORD", "");
        boolean rem = pref.getBoolean("REMEMBER", false);
        edsdtDN.setText(sdt);
        edPassDN.setText(pass);
        checkLuu.setChecked(rem);
        btnDangnhapDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edsdt = edsdtDN.getText().toString();
                String edPass = edPassDN.getText().toString();
                sharedPreferences = (DangNhap_du1.this).getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);

                if(edsdt.length() == 0 || edPass.length() == 0){
                    Toast.makeText(DangNhap_du1.this, "Vui lòng không để trống !", Toast.LENGTH_SHORT).show();
                } else {
                    if(thanhVien_dao_du1.checkDangNhap(edsdt, edPass)){
                        loaiTK = sharedPreferences.getString("loaitaikhoan", "");
                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công !", Toast.LENGTH_SHORT).show();
                        remmemberUser(edsdt, edPass, checkLuu.isChecked());
                        if (loaiTK.equalsIgnoreCase("admin")){
                            startActivity(new Intent(DangNhap_du1.this, Main_Adm_du1.class));
                        } else {
                            startActivity(new Intent(DangNhap_du1.this, Main_KhachHang_du1.class));
                        }
                    } else {
                        Toast.makeText(DangNhap_du1.this, "Đăng nhập không thành công !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnDangKiDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dangki = new Intent(DangNhap_du1.this, DangKi_du1.class);
                startActivity(dangki);
            }
        });
    }

    public void remmemberUser(String  u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("DN_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (!status) {
            editor.clear();
        } else {
            editor.putString("SODIENTHOAI", u);
            editor.putString("PASSWORD", p);
            editor.putBoolean("REMEMBER", status);
        }
        editor.commit();
    }
    @Override
    public void onBackPressed() {
        // Không cho phép quay lại
        // super.onBackPressed(); // Bỏ comment dòng này nếu muốn cho phép quay lại
    }
}