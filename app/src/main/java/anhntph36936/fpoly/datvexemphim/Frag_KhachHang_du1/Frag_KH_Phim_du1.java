package anhntph36936.fpoly.datvexemphim.Frag_KhachHang_du1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import anhntph36936.fpoly.datvexemphim.Adapter_KH.KH_ADT_Phim;
import anhntph36936.fpoly.datvexemphim.DAO.Phim_Dao_du1;
import anhntph36936.fpoly.datvexemphim.Model.Phim_model_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class Frag_KH_Phim_du1 extends Fragment {
    Context context;
    Phim_Dao_du1 phimDAO;
    RecyclerView rc_kh_phim;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_kh_phim_du1, container, false);
        rc_kh_phim = view.findViewById(R.id.rc_kh_phim);
        phimDAO = new Phim_Dao_du1(getContext());
        loadData();
        return view;
    }
    private void loadData() {
        ArrayList<Phim_model_du1> list = phimDAO.getDSPhim();
        GridLayoutManager gridLayoutManager =new GridLayoutManager(getContext(), 1);
        rc_kh_phim.setLayoutManager(gridLayoutManager);
        rc_kh_phim.setFocusable(false);
        rc_kh_phim.setNestedScrollingEnabled(false);
        KH_ADT_Phim adt = new KH_ADT_Phim(getContext(), list, phimDAO);
        rc_kh_phim.setAdapter(adt);
    }

}
