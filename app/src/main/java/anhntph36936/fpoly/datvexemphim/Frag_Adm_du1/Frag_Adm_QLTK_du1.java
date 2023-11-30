package anhntph36936.fpoly.datvexemphim.Frag_Adm_du1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import anhntph36936.fpoly.datvexemphim.Adapter.Adm_ADT_QLTKhoan;
import anhntph36936.fpoly.datvexemphim.DAO.ThanhVien_Dao_du1;
import anhntph36936.fpoly.datvexemphim.Model.ThanhVien_model_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class Frag_Adm_QLTK_du1 extends Fragment {
    ThanhVien_Dao_du1 thanhVien_dao_du1;
    RecyclerView recyclerViewTK;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_adm_qltk_du1, container, false);
        recyclerViewTK = view.findViewById(R.id.recyclerThanhVien);

        thanhVien_dao_du1 = new ThanhVien_Dao_du1(getContext());
        loadData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    private void loadData() {
        ArrayList<ThanhVien_model_du1> list = thanhVien_dao_du1.getDSThanhVien();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewTK.setLayoutManager(linearLayoutManager);
        Adm_ADT_QLTKhoan adapter = new Adm_ADT_QLTKhoan(getContext(), list, thanhVien_dao_du1);
        recyclerViewTK.setAdapter(adapter);
    }

}
