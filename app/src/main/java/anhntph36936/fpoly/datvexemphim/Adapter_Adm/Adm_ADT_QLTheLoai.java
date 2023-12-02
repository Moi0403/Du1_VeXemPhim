package anhntph36936.fpoly.datvexemphim.Adapter_Adm;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import anhntph36936.fpoly.datvexemphim.DAO.TheLoai_Dao_du1;
import anhntph36936.fpoly.datvexemphim.Model.ItemClick_TheLoai;
import anhntph36936.fpoly.datvexemphim.Model.TheLoai_model_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class Adm_ADT_QLTheLoai extends RecyclerView.Adapter<Adm_ADT_QLTheLoai.ViewHolder>{

    private ItemClick_TheLoai itemClickTheLoai;
    private Context context;
    private ArrayList<TheLoai_model_du1> list_tl;

    public Adm_ADT_QLTheLoai(ItemClick_TheLoai itemClickTheLoai, Context context, ArrayList<TheLoai_model_du1> list_tl) {
        this.itemClickTheLoai = itemClickTheLoai;
        this.context = context;
        this.list_tl = list_tl;
    }

    public Adm_ADT_QLTheLoai(List<TheLoai_model_du1> list_tl, Context context) {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_theloai_du1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaLoai.setText(list_tl.get(position).getMaloai() + "");
        holder.txtTheLoai.setText("Thể Loại: " + list_tl.get(position).getTenloai());
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TheLoai_Dao_du1 theLoaiDAO = new TheLoai_Dao_du1(context);
                boolean check = theLoaiDAO.xoaTheLoai(list_tl.get(holder.getAdapterPosition()).getMaloai());
                if (check == true){
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    list_tl.clear();
                    list_tl = theLoaiDAO.getDSTheLoai();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickTheLoai.onClickTheLoai(list_tl.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_tl.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTheLoai, txtMaLoai;
        ImageView ivDel, ivEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaLoai = itemView.findViewById(R.id.txtMaLoai);
            txtTheLoai = itemView.findViewById(R.id.txtTenLoai);
            ivDel = itemView.findViewById(R.id.ivDel);
            ivEdit = itemView.findViewById(R.id.ivEdit);
        }
    }
}
