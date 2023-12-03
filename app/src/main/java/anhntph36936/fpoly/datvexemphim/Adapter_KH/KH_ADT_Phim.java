package anhntph36936.fpoly.datvexemphim.Adapter_KH;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import anhntph36936.fpoly.datvexemphim.DAO.Phim_Dao_du1;
import anhntph36936.fpoly.datvexemphim.Model.Phim_model_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class KH_ADT_Phim extends RecyclerView.Adapter<KH_ADT_Phim.ViewHolder>{
    ArrayList<Phim_model_du1> list;
    Context context;
    Phim_Dao_du1 phimDAO;
    ArrayList<HashMap<String, Object>> ds;

    public KH_ADT_Phim(ArrayList<Phim_model_du1> list, Context context, Phim_Dao_du1 phimDAO, ArrayList<HashMap<String, Object>> ds) {
        this.list = list;
        this.context = context;
        this.phimDAO = phimDAO;
        this.ds = ds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view= inflater.inflate(R.layout.item_kh_phim, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Phim_model_du1 imageView = list.get(position);
        Picasso.get()
                .load(imageView.getHinhanh())
                .into(holder.ivHinhAnh);
        holder.txtTenPhim.setText("" + list.get(position).getTenphim());
        holder.txtTheLoai.setText("" + list.get(position).getTenloai());
        holder.txtGio.setText("" + list.get(position).getThoigianchieu());
        holder.txtNgay.setText("" + list.get(position).getNgaychieu());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenPhim,txtTheLoai,txtGio,txtNgay;
        ImageView ivHinhAnh;
        Button btnDatVe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTheLoai = itemView.findViewById(R.id.TheLoai);
            txtGio = itemView.findViewById(R.id.txtGio);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            txtTenPhim = itemView.findViewById(R.id.TenPhim);
            ivHinhAnh = itemView.findViewById(R.id.ivHinhAnh);
            btnDatVe = itemView.findViewById(R.id.btnDatVe);
        }
    }
}
