package anhntph36936.fpoly.datvexemphim.Frag_KhachHang_du1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import anhntph36936.fpoly.datvexemphim.Adapter_Adm.Adm_ADT_QLTKhoan;
import anhntph36936.fpoly.datvexemphim.DAO.ThanhVien_Dao_du1;
import anhntph36936.fpoly.datvexemphim.Main.DangNhap_du1;
import anhntph36936.fpoly.datvexemphim.Model.ThanhVien_model_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class Frag_KH_USER_du1 extends Fragment {
    Context context;
    ThanhVien_Dao_du1 thanhVien_dao_du1;
    SharedPreferences sharedPreferences;
    LinearLayout ln_ttcn, ln_dmk, ln_exit;
    ArrayList<ThanhVien_model_du1> list_tv;
    Adm_ADT_QLTKhoan adt;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_kh_user_du1, container, false);
        ln_ttcn = view.findViewById(R.id.ln_ttcn);
        ln_dmk = view.findViewById(R.id.ln_dmk);
        ln_exit = view.findViewById(R.id.ln_exit);

        ln_ttcn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTT();
            }
        });
        ln_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExit();
            }
        });

        ln_dmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDMK();
            }
        });
        return view;
    }
    private void showTT(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_kh_thongtin_du1, null);
        builder.setView(view);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();

        EditText edSDT_tt = view.findViewById(R.id.edSdt_tt);
        EditText edEmail_tt = view.findViewById(R.id.edEmai_tt);
        EditText edUser_tt = view.findViewById(R.id.edUser_tt);
        Button btn_huy_tt = view.findViewById(R.id.btn_huy_tt);
        Button btn_Up_tt = view.findViewById(R.id.btn_Up_tt);

        thanhVien_dao_du1 = new ThanhVien_Dao_du1(getContext());
        sharedPreferences = getActivity().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String sdt = sharedPreferences.getString("sodienthoai", "");
        String email = sharedPreferences.getString("email", "");
        String ten = sharedPreferences.getString("tentv", "");
        edSDT_tt.setText(sdt);
        edEmail_tt.setText(email);
        edUser_tt.setText(ten);

        btn_Up_tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdt = edSDT_tt.getText().toString();
                String email = edEmail_tt.getText().toString();
                String user = edUser_tt.getText().toString();
                ThanhVien_model_du1 tv = new ThanhVien_model_du1();
                tv.setMatv(tv.getMatv());
                tv.setSdt(sdt);
                tv.setEmail(email);
                tv.setTentv(user);
                int check = thanhVien_dao_du1.capNhatThanhVien(tv);
                if (check > 0){
                    list_tv.clear();
                    list_tv.addAll(thanhVien_dao_du1.getDSThanhVien());
                    adt.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_huy_tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void showDMK(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_kh_dmk_du1, null);
        builder.setView(view);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();

        EditText edOPass_dmk = view.findViewById(R.id.edOPass_dmk);
        EditText edNPass_dmk = view.findViewById(R.id.edNPass_dmk);
        EditText edRNPass_dmk = view.findViewById(R.id.edtRNPass_dmk);
        Button btn_huy_dmk = view.findViewById(R.id.btn_huy_dmk);
        Button btn_doi_dmk = view.findViewById(R.id.btn_doi_dmk);

        btn_doi_dmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String opass = edOPass_dmk.getText().toString();
                String npass = edNPass_dmk.getText().toString();
                String rnpass = edRNPass_dmk.getText().toString();

                if (npass.equals(rnpass)){
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
                    String sdt = sharedPreferences.getString("sodienthoai", "");

                    ThanhVien_Dao_du1 thanhVien_dao_du1 = new ThanhVien_Dao_du1(getContext());
                    boolean check = thanhVien_dao_du1.capNhatMatKhau(sdt, opass, npass);
                    if (check == true){
                        Toast.makeText(getContext(), "Đổi mật khẩu thành công !", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(getContext(), "Đổi mật khẩu không thàng công !", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Nhập mật khẩu không khớp !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_huy_dmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void showExit(){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getContext());
        builder.setTitle("Exit !");
        builder.setIcon(R.drawable.errol);
        builder.setMessage("Bạn muốn đăng xuất ?");
        builder.setCancelable(false);

        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getContext(), DangNhap_du1.class));
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        androidx.appcompat.app.AlertDialog dialog =builder.create();
        dialog.show();
    }
}
