package anhntph36936.fpoly.datvexemphim.Frag_KhachHang_du1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import anhntph36936.fpoly.datvexemphim.Adapter_KH.KH_ADT_VE;
import anhntph36936.fpoly.datvexemphim.DAO.Ve_Dao_du1;
import anhntph36936.fpoly.datvexemphim.Model.Ve_model_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class Frag_KH_VE_du1 extends Fragment {
    Ve_Dao_du1 vedao;
    RecyclerView rc_kh_ve;
    public static TextView txtGia_vett, tv_sl_tt;
    ArrayList<Ve_model_du1> list_ve;
    Button btn_thanhtoan_ve;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_kh_ve_du1, container, false);
        rc_kh_ve = view.findViewById(R.id.rc_kh_ve);
        txtGia_vett = view.findViewById(R.id.txtGia_vett);
        btn_thanhtoan_ve = view.findViewById(R.id.btn_thanhtoan_ve);
        vedao = new Ve_Dao_du1(getContext());
        loadData();

        btn_thanhtoan_ve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_thanhtoan, null);
                builder.setView(view);
                builder.setCancelable(false);
                AlertDialog dialog =  builder.create();

                TextView tv_tt_tt = view.findViewById(R.id.tv_tt_tt);
                Button btn_huy_tt = view.findViewById(R.id.btn_huy_tt);
                Button btnxn_tt = view.findViewById(R.id.btn_xn_tt);


                tv_tt_tt.setText("Tổng tiền:" +txtGia_vett.getText());

                btnxn_tt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
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
        });
        return view;
    }
    private void loadData() {
        ArrayList<Ve_model_du1> list = vedao.getDSVe();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        int tongtien = 0;
        String outTongtien = String.format("" + tongtien);
        txtGia_vett.setText(outTongtien + "");

        rc_kh_ve.setLayoutManager(linearLayoutManager);
        KH_ADT_VE adt = new KH_ADT_VE(getContext(), list, vedao);
        rc_kh_ve.setAdapter(adt);
    }
}
