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

import anhntph36936.fpoly.datvexemphim.DAO.ThanhVien_Dao_du1;
import anhntph36936.fpoly.datvexemphim.Model.ThanhVien_model_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class Adm_ADT_QLTKhoan extends RecyclerView.Adapter<Adm_ADT_QLTKhoan.ViewHolder> {
    private Context context;
    private ArrayList<ThanhVien_model_du1> list_tv;
    private ThanhVien_Dao_du1 thanhVien_dao_du1;

    public Adm_ADT_QLTKhoan(Context context, ArrayList<ThanhVien_model_du1> list, ThanhVien_Dao_du1 thanhVienDAO) {
        this.context = context;
        this.list_tv = list;
        this.thanhVien_dao_du1 = thanhVienDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thanhvien_du1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaTV.setText(list_tv.get(position).getMatv() +"");
        holder.txtSDT.setText("Số Điện Thoại: " + list_tv.get(position).getSdt());
        holder.txtEmail.setText("Email: " + list_tv.get(position).getEmail());
        holder.txtHoTen.setText("Họ Tên: " + list_tv.get(position).getTentv());
        holder.txtMatKhau.setText("Mật Khẩu: " + list_tv.get(position).getMatkhau());
        holder.txtLoaiTK.setText("Loại Tài Khoản: " + list_tv.get(position).getLoaitaikhoan());
        list_tv = thanhVien_dao_du1.getDSThanhVien();

        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thanhVien_dao_du1 = new ThanhVien_Dao_du1(context);
                boolean check = thanhVien_dao_du1.xoaThanhVien(list_tv.get(holder.getAdapterPosition()).getMatv());
                if (check == true){
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    list_tv.clear();
                    list_tv = thanhVien_dao_du1.getDSThanhVien();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_tv.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaTV, txtSDT, txtEmail, txtHoTen, txtMatKhau, txtLoaiTK;
        ImageView ivEdit, ivDel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMaTV = itemView.findViewById(R.id.txtMaTV);
            txtSDT = itemView.findViewById(R.id.txtSDT);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtHoTen = itemView.findViewById(R.id.txtHoTen);
            txtMatKhau = itemView.findViewById(R.id.txtMatKhau);
            txtLoaiTK = itemView.findViewById(R.id.txtLoaiTaiKhoan);
            ivDel = itemView.findViewById(R.id.ivDel_tk);

        }
    }
}
