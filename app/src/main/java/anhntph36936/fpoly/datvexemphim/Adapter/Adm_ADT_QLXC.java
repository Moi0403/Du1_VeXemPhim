package anhntph36936.fpoly.datvexemphim.Adapter;

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

import anhntph36936.fpoly.datvexemphim.DAO.XuatChieu_Dao_du1;
import anhntph36936.fpoly.datvexemphim.Model.ItemClick_XuatChieu;
import anhntph36936.fpoly.datvexemphim.Model.XuatChieu_Model_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class Adm_ADT_QLXC extends RecyclerView.Adapter<Adm_ADT_QLXC.ViewHolder> {
    private Context context;
    private ArrayList<XuatChieu_Model_du1> list_xc;
    private ItemClick_XuatChieu itemClickXuatChieu;

    public Adm_ADT_QLXC(Context context, ArrayList<XuatChieu_Model_du1> list_xc, ItemClick_XuatChieu itemClickXuatChieu) {
        this.context = context;
        this.list_xc = list_xc;
        this.itemClickXuatChieu = itemClickXuatChieu;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_xuatchieu_du1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtNgayChieu.setText("Ngày Chiếu: " + list_xc.get(position).getNgaychieu());
        holder.txtThoiGianChieu.setText("Thời Gian Chiếu: " + list_xc.get(position).getThoigianchieu());
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuatChieu_Dao_du1 xuatChieuDAO = new XuatChieu_Dao_du1(context);
                boolean check = xuatChieuDAO.xoaXuatChieu(list_xc.get(holder.getAdapterPosition()).getMaxuatchieu());
                if (check == true){
                    list_xc.clear();
                    list_xc = xuatChieuDAO.getDSXuatChieu();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickXuatChieu.onClickXuatChieu(list_xc.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_xc.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtNgayChieu, txtThoiGianChieu;
        ImageView ivDel, ivEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNgayChieu = itemView.findViewById(R.id.txtNgayChieu);
            txtThoiGianChieu = itemView.findViewById(R.id.txtThoiGianChieu);
            ivDel = itemView.findViewById(R.id.ivDel);
            ivEdit = itemView.findViewById(R.id.ivEdit);
        }
    }
}
