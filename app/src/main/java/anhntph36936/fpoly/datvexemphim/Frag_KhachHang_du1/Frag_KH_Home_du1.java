package anhntph36936.fpoly.datvexemphim.Frag_KhachHang_du1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import anhntph36936.fpoly.datvexemphim.Adapter_KH.KH_ADT_Home;
import anhntph36936.fpoly.datvexemphim.DAO.Phim_Dao_du1;
import anhntph36936.fpoly.datvexemphim.DAO.ThanhVien_Dao_du1;
import anhntph36936.fpoly.datvexemphim.Model.Phim_model_du1;
import anhntph36936.fpoly.datvexemphim.Model.ThanhVien_model_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class Frag_KH_Home_du1 extends Fragment {
    TextView tv_tenkh;
    RecyclerView rc_kh_home;
    Phim_Dao_du1 phimDao;
    SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_kh_home_du1, container, false);
        tv_tenkh = view.findViewById(R.id.tv_tenkh);
        rc_kh_home = view.findViewById(R.id.rc_kh_home);
        phimDao = new Phim_Dao_du1(getContext());
        ThanhVien_Dao_du1 thanhVien_dao_du1 = new ThanhVien_Dao_du1(getContext());
        ArrayList<ThanhVien_model_du1> list_tv = thanhVien_dao_du1.getDSThanhVien();
        sharedPreferences = getActivity().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String ten = sharedPreferences.getString("tentv", "");
        tv_tenkh.setText("Xin chào, "+ten);

        loadData();
        return view;
    }
    private void loadData() {
        ArrayList<Phim_model_du1> list = phimDao.getDSPhim();
        GridLayoutManager gridLayoutManager =new GridLayoutManager(getContext(), 2);
        rc_kh_home.setLayoutManager(gridLayoutManager);
        rc_kh_home.setFocusable(false);
        rc_kh_home.setNestedScrollingEnabled(false);
        KH_ADT_Home adt = new KH_ADT_Home(getContext(), list, phimDao);
        rc_kh_home.setAdapter(adt);
    }
}
