package anhntph36936.fpoly.datvexemphim.Frag_Adm_du1;

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

import anhntph36936.fpoly.datvexemphim.Adapter_Adm.Adm_ADT_TrangChu;
import anhntph36936.fpoly.datvexemphim.DAO.Phim_Dao_du1;
import anhntph36936.fpoly.datvexemphim.Model.Phim_model_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class Frag_Adm_Trangchu_du1 extends Fragment {
    RecyclerView recycler_trangchu;
    Phim_Dao_du1 phimDao;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_adm_trangchu_du1, container, false);
        recycler_trangchu = view.findViewById(R.id.Recycler_trangchu);
        phimDao = new Phim_Dao_du1(getContext());
        loadData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    private void loadData() {
            ArrayList<Phim_model_du1> list = phimDao.getDSPhim();
            GridLayoutManager gridLayoutManager =new GridLayoutManager(getContext(), 2);
            recycler_trangchu.setLayoutManager(gridLayoutManager);
            recycler_trangchu.setFocusable(false);
            recycler_trangchu.setNestedScrollingEnabled(false);
            Adm_ADT_TrangChu adt = new Adm_ADT_TrangChu(getContext(), list, phimDao);
            recycler_trangchu.setAdapter(adt);
        }


}
