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

import anhntph36936.fpoly.datvexemphim.Adapter.Adm_ADT_QLXC;
import anhntph36936.fpoly.datvexemphim.DAO.XuatChieu_Dao_du1;
import anhntph36936.fpoly.datvexemphim.Model.ItemClick_XuatChieu;
import anhntph36936.fpoly.datvexemphim.Model.XuatChieu_Model_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class Frag_Adm_QLXC_du1 extends Fragment {
    RecyclerView recyclerXuatChieu;
    XuatChieu_Dao_du1 xuatChieuDAO;
    EditText edtNgayChieu, edtThoiGianChieu;
    Button btnThem, btnSua;
    int maxuatchieu;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_adm_qlxc_du1, container, false);
        recyclerXuatChieu = view.findViewById(R.id.recyclerXuatChieu);
        edtNgayChieu = view.findViewById(R.id.edtNgayChieu);
        edtThoiGianChieu = view.findViewById(R.id.edtThoiGianChieu);
        btnThem = view.findViewById(R.id.btnThem);
        btnSua = view.findViewById(R.id.btnSua);

        xuatChieuDAO = new XuatChieu_Dao_du1(getContext());
        loadData();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ngaychieu = edtNgayChieu.getText().toString();
                String thoigianchieu = edtThoiGianChieu.getText().toString();
                if (ngaychieu.equals("")||thoigianchieu.equals("")){
                    Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    if (xuatChieuDAO.themXuatChieu(ngaychieu, thoigianchieu)) {
                        loadData();
                        edtNgayChieu.setText("");
                        edtThoiGianChieu.setText("");
                    } else {
                        Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ngaychieu = edtNgayChieu.getText().toString();
                String thoigianchieu = edtThoiGianChieu.getText().toString();
                if (ngaychieu.equals("")||thoigianchieu.equals("")){
                    Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    XuatChieu_Model_du1 xuatChieu = new XuatChieu_Model_du1(maxuatchieu, ngaychieu, thoigianchieu);
                    if (xuatChieuDAO.capNhatXuatChieu(xuatChieu)) {
                        edtNgayChieu.setText("");
                        edtThoiGianChieu.setText("");
                        //Toast.makeText(getContext(), ""+ tenloai, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Thay đổi thông tin thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;

    }

    private void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerXuatChieu.setLayoutManager(linearLayoutManager);
        ArrayList<XuatChieu_Model_du1> list = xuatChieuDAO.getDSXuatChieu();
        Adm_ADT_QLXC adapter = new Adm_ADT_QLXC(getContext(), list, new ItemClick_XuatChieu(){
            @Override
            public void onClickXuatChieu(XuatChieu_Model_du1 xuatChieu) {
                edtNgayChieu.setText(xuatChieu.getNgaychieu());
                edtThoiGianChieu.setText(xuatChieu.getThoigianchieu());
                maxuatchieu = xuatChieu.getMaxuatchieu();
            }
        });
        recyclerXuatChieu.setAdapter(adapter);
    }
}
