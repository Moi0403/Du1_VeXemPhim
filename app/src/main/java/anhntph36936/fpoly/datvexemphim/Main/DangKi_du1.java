package anhntph36936.fpoly.datvexemphim.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import anhntph36936.fpoly.datvexemphim.DAO.ThanhVien_Dao_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class DangKi_du1 extends AppCompatActivity {
    EditText edEmailDK, edSdtDK, edUserDK, edPassDK;
    Button btnDangKiDK, btnTroVe;
    ThanhVien_Dao_du1 thanhVien_dao_du1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki_du1);
        edEmailDK = findViewById(R.id.edEmailDK);
        edSdtDK = findViewById(R.id.edSdtDK);
        edUserDK = findViewById(R.id.edUserDK);
        edPassDK = findViewById(R.id.edPassDK);
        btnDangKiDK = findViewById(R.id.btnDangkiDK);
        btnTroVe = findViewById(R.id.btnTroveDK);

        thanhVien_dao_du1 = new ThanhVien_Dao_du1(this);

        btnDangKiDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmailDK.getText().toString();
                String sdt = edSdtDK.getText().toString();
                String user = edUserDK.getText().toString();
                String pass = edPassDK.getText().toString();
                checkRong(edEmailDK);
                checkRong(edSdtDK);
                checkRong(edUserDK);
                checkRong(edPassDK);

                boolean check = thanhVien_dao_du1.themThanhVien(email, sdt, user, pass, "khachhang");
                if(check == true){
                    Toast.makeText(DangKi_du1.this, "Đăng kí thành công !", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DangKi_du1.this, "Đăng kí không thành công !", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangKi_du1.this, DangNhap_du1.class));
            }
        });
    }
    private boolean checkRong(EditText editText){
        if(editText.getText().toString().trim().length() > 0){
            return true;
        } else {
            editText.setError("Vui lòng không để trống !");
        }
        return  false;
    }
}