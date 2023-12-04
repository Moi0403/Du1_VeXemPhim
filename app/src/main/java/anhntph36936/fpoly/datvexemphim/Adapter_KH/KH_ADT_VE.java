package anhntph36936.fpoly.datvexemphim.Adapter_KH;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import anhntph36936.fpoly.datvexemphim.DAO.Ve_Dao_du1;
import anhntph36936.fpoly.datvexemphim.Model.Phim_model_du1;
import anhntph36936.fpoly.datvexemphim.Model.Ve_model_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class KH_ADT_VE extends RecyclerView.Adapter<KH_ADT_VE.ViewHolder> {
    Context context;
    ArrayList<Ve_model_du1> list_ve;
    ArrayList<Phim_model_du1> list;
    Ve_Dao_du1 vedao;

    public KH_ADT_VE(Context context, ArrayList<Ve_model_du1> list_ve, Ve_Dao_du1 vedao) {
        this.context = context;
        this.list_ve = list_ve;
        this.vedao = vedao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_ve_du1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Phim_model_du1 img = list.get(position);
//        Picasso.get()
//                .load(img.getHinhanh())
//                .into(holder.img_avt_ve);
    holder.txtTenP.setText("" +list_ve.get(position).getTenphim());
    holder.txtSoLuong_ve.setText("" +list_ve.get(position).getSoluong());
    holder.txtGhe_ve.setText("" +list_ve.get(position).getSoghe());
    holder.txtThoiGian_ve.setText("" +list_ve.get(position).getThoigiandat());
    holder.txtNgay_ve.setText("" +list_ve.get(position).getNgaychieu());
    holder.txtGia_ve.setText("" +list_ve.get(position).getGiave());

    }

    @Override
    public int getItemCount() {
        return list_ve.size();
    }

    public class  ViewHolder extends  RecyclerView.ViewHolder{
        ImageView img_avt_ve, img_del_ve;
        TextView txtTenP, txtSoLuong_ve, txtGhe_ve, txtThoiGian_ve, txtNgay_ve, txtGia_ve;
        CheckBox cbtt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_avt_ve = itemView.findViewById(R.id.img_avt_ve);
            img_del_ve = itemView.findViewById(R.id.img_del_ve);
            txtTenP = itemView.findViewById(R.id.txtTenP_ve);
            txtSoLuong_ve = itemView.findViewById(R.id.txtSoLuong_ve);
            txtGhe_ve = itemView.findViewById(R.id.txtGhe_ve);
            txtThoiGian_ve = itemView.findViewById(R.id.txtThoiGian_ve);
            txtNgay_ve = itemView.findViewById(R.id.txtNgay_ve);
            txtGia_ve = itemView.findViewById(R.id.txtGia_ve);
            cbtt = itemView.findViewById(R.id.cbtt_ve);

        }
    }
}
