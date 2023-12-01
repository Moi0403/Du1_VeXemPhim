package anhntph36936.fpoly.datvexemphim.Frag_Adm_du1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import anhntph36936.fpoly.datvexemphim.DAO.Phim_Dao_du1;
import anhntph36936.fpoly.datvexemphim.Model.Phim_model_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class Frag_Adm_Trangchu_du1 extends Fragment {
    int maxuatchieu;
    Spinner spinnerTheLoai, spnGio1,spnNgay;
    Phim_Dao_du1 phimDAO;
    RecyclerView recyclerPhim;
    ArrayList<Phim_model_du1> list_phim;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    EditText edAnhPhim, edTenPhim;
    Spinner tenloai, thoiGianChieu, ngayChieu;
    int maPhim, maXuatChieu, maloai;
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
//        storage = FirebaseStorage.getInstance("gs://du1_vexemphim.appspot.com");
//        storageRef = storage.getReference();
        phimDAO = new Phim_Dao_du1(getContext());
        recyclerPhim = view.findViewById(R.id.Recycler_trangchu);
        list_phim = phimDAO.getDSPhim();
//        loadData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
//    private void loadData(){
//        ArrayList<Phim_model_du1> list = phimDAO.getDSPhim();
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext() , 2);
//        recyclerPhim.setLayoutManager(gridLayoutManager);
//        recyclerPhim.setFocusable(false);
//        recyclerPhim.setNestedScrollingEnabled(false);
//
//        Adm_ADT_TrangChu adapter = new Adm_ADT_TrangChu(getContext(), list, getDS(), phimDAO);
//        recyclerPhim.setAdapter(adapter);
//    }



}
