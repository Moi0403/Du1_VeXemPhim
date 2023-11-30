package anhntph36936.fpoly.datvexemphim.Frag_Adm_du1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import anhntph36936.fpoly.datvexemphim.DAO.ThanhVien_Dao_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class Frag_Adm_DMK_du1 extends Fragment {
    EditText edtOldPass, edtNewPass, edtReNewPass;
    Button btnCapNhat;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_dmk_du1, container, false);
        edtOldPass = view.findViewById(R.id.edtOldPass);
        edtNewPass = view.findViewById(R.id.edtNewPass);
        edtReNewPass = view.findViewById(R.id.edtReNewPass);
        btnCapNhat = view.findViewById(R.id.btnCapNhat);

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = edtOldPass.getText().toString();
                String newPass = edtNewPass.getText().toString();
                String reNewPass = edtReNewPass.getText().toString();

                if (newPass.equals(reNewPass)){
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
                    String sdt = sharedPreferences.getString("sodienthoai", "");

                    ThanhVien_Dao_du1 thanhVien_dao_du1 = new ThanhVien_Dao_du1(getContext());
                    boolean check = thanhVien_dao_du1.capNhatMatKhau(sdt, oldPass, newPass);
                    if (check == true){
                        Toast.makeText(getContext(), "Đổi mật khẩu thành công !", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Đổi mật khẩu không thàng công !", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Nhập mật khẩu không khớp !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
