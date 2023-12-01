package anhntph36936.fpoly.datvexemphim.Frag_Adm_du1;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;

import anhntph36936.fpoly.datvexemphim.Adapter.Adm_ADT_TrangChu;
import anhntph36936.fpoly.datvexemphim.DAO.Phim_Dao_du1;
import anhntph36936.fpoly.datvexemphim.DAO.TheLoai_Dao_du1;
import anhntph36936.fpoly.datvexemphim.DAO.XuatChieu_Dao_du1;
import anhntph36936.fpoly.datvexemphim.Model.Phim_model_du1;
import anhntph36936.fpoly.datvexemphim.Model.TheLoai_model_du1;
import anhntph36936.fpoly.datvexemphim.Model.XuatChieu_Model_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class Frag_Adm_Trangchu_du1 extends Fragment {
    int maxuatchieu;
    Spinner spinnerTheLoai, spnGio1,spnNgay;
    Phim_Dao_du1 phimDAO;
    RecyclerView recyclerPhim;
    ArrayList<Phim_model_du1> list_phim;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    ImageView edthinhanh;
    StorageReference storageRef;
    String hinhAnh = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_adm_trangchu_du1, container, false);
        storage = FirebaseStorage.getInstance("gs://du1_vexemphim.appspot.com");
        storageRef = storage.getReference();
        phimDAO = new Phim_Dao_du1(getContext());
        recyclerPhim = view.findViewById(R.id.RecyclerView);
        list_phim = phimDAO.getDSPhim();
        loadData();
        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatAdd);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog_them();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    private void showdialog_them(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_adm_themphim_du1, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();

        EditText edtTenPhim = view.findViewById(R.id.edtTenPhim);
        edthinhanh = view.findViewById(R.id.ivHinhAnh);
        Button btnHuy_them = view.findViewById(R.id.btnHuy_them);
        Button bntThem_them = view.findViewById(R.id.btnThem_them);

        spinnerTheLoai = view.findViewById(R.id.spnTheLoai);
        SimpleAdapter simpleAdapterLoai = new SimpleAdapter(
                getContext(),
                getDSLoai(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},
                new int[]{android.R.id.text1});
        spinnerTheLoai.setAdapter(simpleAdapterLoai);

        spnGio1 = view.findViewById(R.id.spnGio1);
        SimpleAdapter simpleAdapterGio = new SimpleAdapter(
                getContext(),
                getDSGio(),
                android.R.layout.simple_list_item_1,
                new String[]{"thoigianchieu"},
                new int[]{android.R.id.text1}
        );
        spnGio1.setAdapter(simpleAdapterGio);

        spnNgay = view.findViewById(R.id.spnNgay);
        SimpleAdapter simpleAdapterNgay = new SimpleAdapter(
                getContext(),
                getDSGio(),
                android.R.layout.simple_list_item_1,
                new String[]{"ngaychieu"},
                new int[]{android.R.id.text1}
        );
        spnNgay.setAdapter(simpleAdapterNgay);

        edthinhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnHuy_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }




    private void loadData(){
        ArrayList<Phim_model_du1> list = phimDAO.getDSPhim();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext() , 2);
        recyclerPhim.setLayoutManager(gridLayoutManager);
        recyclerPhim.setFocusable(false);
        recyclerPhim.setNestedScrollingEnabled(false);


        Adm_ADT_TrangChu adapter = new Adm_ADT_TrangChu(getContext(), list, getDS(), phimDAO);
        recyclerPhim.setAdapter(adapter);


    }

    private ArrayList<HashMap<String , Object>> getDS(){
        ArrayList<Phim_model_du1> list = phimDAO.getDSPhim();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (Phim_model_du1 phim : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maphim", phim.getMaphim());
            hs.put("tenphim", phim.getTenphim());
            hs.put("theloai", phim.getMaloai());
            listHM.add(hs);
        }
        return listHM;
    }
    private ArrayList<HashMap<String, Object>> getDSLoai(){
        TheLoai_Dao_du1 theLoaiDAO = new TheLoai_Dao_du1(getContext());
        ArrayList<TheLoai_model_du1> list = theLoaiDAO.getDSTheLoai();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (TheLoai_model_du1 loai : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maloai", loai.getMaloai());
            hs.put("tenloai", loai.getTenloai());
            listHM.add(hs);
        }
        return listHM;
    }
    private ArrayList<HashMap<String, Object>> getDSGio(){
        XuatChieu_Dao_du1 xuatChieuDAO = new XuatChieu_Dao_du1(getContext());
        ArrayList<XuatChieu_Model_du1> list = xuatChieuDAO.getDSXuatChieu();
        ArrayList<HashMap<String, Object>> listHM1 = new ArrayList<>();
        for (XuatChieu_Model_du1 xuatChieu : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maxuatchieu", xuatChieu.getMaxuatchieu());
            hs.put("thoigianchieu", xuatChieu.getThoigianchieu());
            hs.put("ngaychieu", xuatChieu.getNgaychieu());
            listHM1.add(hs);
        }
        return  listHM1;
    }

}
