package anhntph36936.fpoly.datvexemphim.Frag_Adm_du1;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import anhntph36936.fpoly.datvexemphim.Adapter_Adm.Adm_ADT_QLTheLoai;
import anhntph36936.fpoly.datvexemphim.DAO.TheLoai_Dao_du1;
import anhntph36936.fpoly.datvexemphim.Model.ItemClick_TheLoai;
import anhntph36936.fpoly.datvexemphim.Model.TheLoai_model_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class Frag_Adm_QLTL_du1 extends Fragment {
    RecyclerView recyclerTheLoai;
    TheLoai_Dao_du1 theLoaiDAO;
    EditText edtTheLoai;
    Button btnThem, btnSua;
    int maloai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_adm_qltl_du1, container, false);
        recyclerTheLoai = view.findViewById(R.id.recyclerTheLoai);
        edtTheLoai =  view.findViewById(R.id.edtTheLoai);
        btnThem = view.findViewById(R.id.btnThem);
        btnSua = view.findViewById(R.id.btnSua);

        theLoaiDAO = new TheLoai_Dao_du1(getContext());
        loadData();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = edtTheLoai.getText().toString();

                if (tenloai.equals("")){
                    Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    if (theLoaiDAO.themTheLoai(tenloai)) {
                        loadData();
                        edtTheLoai.setText("");
                    } else {
                        Toast.makeText(getContext(), "Thêm thể loại thất bại thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = edtTheLoai.getText().toString();

                if (tenloai.equals("")){
                    Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    TheLoai_model_du1 theLoai = new TheLoai_model_du1(maloai,tenloai);
                    if (theLoaiDAO.capNhatTheLoai(theLoai)) {
                        loadData();
                        edtTheLoai.setText("");
                    } else {
                        Toast.makeText(getContext(), "Thay đổi thông tin thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    private void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerTheLoai.setLayoutManager(linearLayoutManager);
        ArrayList<TheLoai_model_du1> list_tl = theLoaiDAO.getDSTheLoai();

        Adm_ADT_QLTheLoai adm_adt_QL_theLoai = new Adm_ADT_QLTheLoai(new ItemClick_TheLoai() {
                    @Override
                    public void onClickTheLoai(TheLoai_model_du1 theLoai) {
                        edtTheLoai.setText(theLoai.getTenloai());
                        maloai = theLoai.getMaloai();
                    }
                }, getContext(), list_tl);
        recyclerTheLoai.setAdapter(adm_adt_QL_theLoai);
    }
}
