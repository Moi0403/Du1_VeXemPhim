package anhntph36936.fpoly.datvexemphim.Frag_KhachHang_du1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import anhntph36936.fpoly.datvexemphim.R;

public class Frag_KH_USER_du1 extends Fragment {
    Context context;
    SharedPreferences sharedPreferences;
    TextView txtTen, txtSoDienThoai, txtEmail, txtDoiMK, txtDangXuat,txtLienhe;
    View view;
    EditText edtOldPass, edtNewPass, edtReNewPass;
    EditText edthoten , edtemail , edtnoidung;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_kh_user_du1,container,false);
    }
}
