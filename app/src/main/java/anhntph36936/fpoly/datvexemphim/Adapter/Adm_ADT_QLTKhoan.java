package anhntph36936.fpoly.datvexemphim.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    SharedPreferences sharedPreferences;

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
        sharedPreferences = context.getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String matv = sharedPreferences.getString("matv", "");
        String sdt = sharedPreferences.getString("sodienthoai", "");
        String email = sharedPreferences.getString("email", "");
        String hoten = sharedPreferences.getString("hoten", "");
        String matkhau = sharedPreferences.getString("matkhau", "");
        String loaitaikhoan = sharedPreferences.getString("loaitaikhoan", "");

        holder.txtMaTV.setText(list_tv.get(position).getMatv());
        holder.txtSDT.setText("Số Điện Thoại: " + list_tv.get(position).getSdt());
        holder.txtEmail.setText("Email: " + list_tv.get(position).getEmail());
        holder.txtHoTen.setText("Họ Tên: " + list_tv.get(position).getTentv());
        holder.txtMatKhau.setText("Mật Khẩu: " + list_tv.get(position).getMatkhau());
        holder.txtLoaiTK.setText("Loại Tài Khoản: " + list_tv.get(position).getLoaitaikhoan());
        list_tv = thanhVien_dao_du1.getDSThanhVien();
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


        }
    }
}
