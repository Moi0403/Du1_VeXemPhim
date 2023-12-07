package anhntph36936.fpoly.datvexemphim.Adapter_KH;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import anhntph36936.fpoly.datvexemphim.DAO.Ve_Dao_du1;
import anhntph36936.fpoly.datvexemphim.Model.Ve_model_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class KH_ADT_VE extends RecyclerView.Adapter<KH_ADT_VE.ViewHolder> {
    Context context;
    ArrayList<Ve_model_du1> list_ve;
    Ve_Dao_du1 vedao;
    int sl =0;
    TextView txtgia;
    CheckBox cktt;

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
        Ve_model_du1 ve_model_du1 = list_ve.get(position);

        Picasso.get()
                .load(ve_model_du1.getAnh())
                .into(holder.img_avt_ve);
        holder.txtTenP.setText(ve_model_du1.getTenp());
        holder.txtSoLuong_ve.setText(ve_model_du1.getSoluong());
        holder.txtGhe_ve.setText(ve_model_du1.getSoghe());
        holder.txtThoiGian_ve.setText(ve_model_du1.getThoigian());
        holder.txtNgay_ve.setText(ve_model_du1.getNgay());
        holder.txtGia_ve.setText("Giá:" +ve_model_du1.getGia() +".000 đ");

        holder.img_del_ve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vedao = new Ve_Dao_du1(context);
                boolean check = vedao.xoave(list_ve.get(holder.getAdapterPosition()).getMave());
                if (check == true){
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    list_ve.clear();
                    list_ve = vedao.getDSVe();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.cbtt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int tien = list_ve.get(position).getGia();
                checkgia(tien);
            }
        });


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
    public void checkgia(int tien){
        int i = 0;
        String gia = list_ve.get(i).getGia() +".000 đ";
        Toast.makeText(context ,"" +gia, Toast.LENGTH_SHORT).show();
    }
    public void tang(){
        ++sl;
        checkgia(sl);
    }
    public void giam(){
        --sl;
        checkgia(sl);
    }
}
