package anhntph36936.fpoly.datvexemphim.Adapter_KH;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import anhntph36936.fpoly.datvexemphim.DAO.Phim_Dao_du1;
import anhntph36936.fpoly.datvexemphim.DAO.Ve_Dao_du1;
import anhntph36936.fpoly.datvexemphim.DAO.XuatChieu_Dao_du1;
import anhntph36936.fpoly.datvexemphim.Model.Phim_model_du1;
import anhntph36936.fpoly.datvexemphim.Model.Ve_model_du1;
import anhntph36936.fpoly.datvexemphim.Model.XuatChieu_Model_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class KH_ADT_Phim extends RecyclerView.Adapter<KH_ADT_Phim.ViewHolder>{
    ArrayList<Phim_model_du1> list_p;
    Phim_Dao_du1 phimdao;
    ArrayList<XuatChieu_Model_du1> list_xc;
    XuatChieu_Dao_du1 xcdao;
    Context context;
    Phim_Dao_du1 phimDAO;
    ArrayList<HashMap<String, Object>> ds;
    SharedPreferences sharedPreferences;
    Ve_Dao_du1 vedao;
    ArrayList<Ve_model_du1> list_ve;
    ArrayList<String> list_st;
    int sl = 0;
    String tenphim, theloai, ngay, gio,hinhanh;
    CheckBox A1,A2,A3,A4;
    CheckBox B1,B2,B3,B4;
    CheckBox C1,C2,C3,C4;
    CheckBox D1,D2,D3,D4;
    TextView txtgia, txtsl;


    public KH_ADT_Phim(Context context, ArrayList<Phim_model_du1> list, Phim_Dao_du1 phimDAO) {
        this.list_p = list;
        this.context = context;
        this.phimDAO = phimDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view= inflater.inflate(R.layout.item_kh_phim, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Phim_model_du1 imageView = list_p.get(position);
        Picasso.get()
                .load(imageView.getHinhanh())
                .into(holder.ivHinhAnh);
        holder.txtTenPhim.setText("" + list_p.get(position).getTenphim());
        holder.txtTheLoai.setText("" + list_p.get(position).getTenloai());
        holder.txtGiaVe.setText("" +list_p.get(position).getGiaphim() +".000 đ");
        holder.txtGio.setText("" + list_p.get(position).getThoigianchieu());
        holder.txtNgay.setText("" + list_p.get(position).getNgaychieu());
        holder.btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_kh_datve_du1, null);
                builder.setView(view);
                builder.setCancelable(false);
                AlertDialog dialog = builder.create();

                ImageView img_APhim = view.findViewById(R.id.img_anhphim_dv);
                EditText edAp = view.findViewById(R.id.ed_anhp);
                TextView txtTenPhim = view.findViewById(R.id.txtTenPhim);
                TextView txtTheLoai = view.findViewById(R.id.txtTheLoai);
                TextView txtTime = view.findViewById(R.id.txtTim);
                TextView txtSelectday = view.findViewById(R.id.txtSelectday);
                txtgia = view.findViewById(R.id.txtgia);
                txtsl = view.findViewById(R.id.txtsl);
                A1 = view.findViewById(R.id.A1);
                A2 = view.findViewById(R.id.A2);
                A3 = view.findViewById(R.id.A3);
                A4 = view.findViewById(R.id.A4);
                B1 = view.findViewById(R.id.B1);
                B2 = view.findViewById(R.id.B2);
                B3 = view.findViewById(R.id.B3);
                B4 = view.findViewById(R.id.B4);
                C1 = view.findViewById(R.id.C1);
                C2 = view.findViewById(R.id.C2);
                C3 = view.findViewById(R.id.C3);
                C4 = view.findViewById(R.id.C4);
                D1 = view.findViewById(R.id.D1);
                D2 = view.findViewById(R.id.D2);
                D3 = view.findViewById(R.id.D3);
                D4 = view.findViewById(R.id.D4);
                Button btn_dve_dv= view.findViewById(R.id.btnDatVe_dv);
                Button btn_huy_d=view.findViewById(R.id.btnhuy_dv);

                Phim_model_du1 img_Ap_dv = list_p.get(position);
                Picasso.get()
                        .load(img_Ap_dv.getHinhanh())
                        .into(img_APhim);
                edAp.setText("" +list_p.get(position).getHinhanh());
                txtTenPhim.setText("Phim:" +list_p.get(position).getTenphim());
                txtTheLoai.setText("Thể loại:" +list_p.get(position).getTenloai());
                txtTime.setText("" +list_p.get(position).getThoigianchieu());
                txtSelectday.setText("" +list_p.get(position).getNgaychieu());

//                ghechecked();
                Listener();

                btn_dve_dv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vedao = new Ve_Dao_du1(context);
                        StringBuilder stringBuilder = new StringBuilder();
                        for (String a: list_st){
                            stringBuilder.append(a).append("\t");
                            btn_dve_dv.setEnabled(false);
                        }
                        String ghe = stringBuilder.toString();
                        String sluong = txtsl.getText().toString();
                        String gia = txtgia.getText().toString();
                        String gio = txtTime.getText().toString();
                        String ngay = txtSelectday.getText().toString();
                        String tenp = txtTenPhim.getText().toString();
                        String anh = edAp.getText().toString();


                        boolean check = false;
                        if (ghe.equalsIgnoreCase("") || gio.equalsIgnoreCase("")){
                            Toast.makeText(context, "Chưa đủ thông tin !", Toast.LENGTH_SHORT).show();
                        } else{
                            check = vedao.themVe(ghe, sluong, gia, ngay, gio, tenp, anh);
                        }

                        if (check == true){
                            btn_dve_dv.setEnabled(false);
                            Toast.makeText(context, "Đặt thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Đặt không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btn_huy_d.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_p.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenPhim,txtTheLoai,txtGio,txtNgay, txtGiaVe;
        ImageView ivHinhAnh;
        Button btnDatVe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTheLoai = itemView.findViewById(R.id.TheLoai);
            txtGio = itemView.findViewById(R.id.txtGio);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            txtTenPhim = itemView.findViewById(R.id.TenPhim);
            txtGiaVe = itemView.findViewById(R.id.txtGiaVe);
            ivHinhAnh = itemView.findViewById(R.id.ivHinhAnh);
            btnDatVe = itemView.findViewById(R.id.btnDatVe);
        }
    }
    public void checkgia(int slve){
        int i = 0;
        txtgia.setText(list_p.get(i).getGiaphim() * slve + ".000 đ");
    }
    public void tang(){
        ++sl;
        checkgia(sl);
        txtsl.setText(sl + "");
    }
    public void giam(){
        --sl;
        checkgia(sl);
        txtsl.setText(sl+"");
    }
    public void ghechecked() {
        vedao = new Ve_Dao_du1(context);
        list_ve = vedao.getDSVe();
        ArrayList<String> listghe = new ArrayList<>();

//        for (Ve_model_du1 veModeldu1 : list_ve) {
//            listghe.add(String.format("%s", veModeldu1.getSoghe()) + veModeldu1.getThoigian());
//        }
        for (int i = 0; i < listghe.size(); i++) {
            if (listghe.get(i).contains("A1") && listghe.get(i).contains(gio)) {
                A1.setEnabled(false);
                A1.setTextColor(Color.RED);
                A1.setButtonTintList(ColorStateList.valueOf(Color.RED));
            } else {
                A1.setEnabled(true);
            }
        }
        for (int i = 0; i < listghe.size(); i++) {
            if (listghe.get(i).contains("A2") && listghe.get(i).contains(gio)) {
                A2.setEnabled(false);
                A2.setTextColor(Color.RED);
                A2.setButtonTintList(ColorStateList.valueOf(Color.RED));
            } else {
                A2.setEnabled(true);
            }
        }
        for (int i = 0; i < listghe.size(); i++) {
            if (listghe.get(i).contains("A3") && listghe.get(i).contains(gio)) {
                A3.setEnabled(false);
                A3.setTextColor(Color.RED);
                A3.setButtonTintList(ColorStateList.valueOf(Color.RED));
            } else {
                A3.setEnabled(true);
            }
        }
        for (int i = 0; i < listghe.size(); i++) {
            if (listghe.get(i).contains("A4") && listghe.get(i).contains(gio)) {
                A4.setEnabled(false);
                A4.setTextColor(Color.RED);
                A4.setButtonTintList(ColorStateList.valueOf(Color.RED));
            } else {
                A4.setEnabled(true);
            }
        }
        for (int i = 0; i < listghe.size(); i++) {
            if (listghe.get(i).contains("B1") && listghe.get(i).contains(gio)) {
                B1.setEnabled(false);
                B1.setTextColor(Color.RED);
                B1.setButtonTintList(ColorStateList.valueOf(Color.RED));
            } else {
                B1.setEnabled(true);
            }
        }
        for (int i = 0; i < listghe.size(); i++) {
            if (listghe.get(i).contains("B2") && listghe.get(i).contains(gio)) {
                B2.setEnabled(false);
                B2.setTextColor(Color.RED);
                B2.setButtonTintList(ColorStateList.valueOf(Color.RED));
            } else {
                B2.setEnabled(true);
            }
        }
        for (int i = 0; i < listghe.size(); i++) {
            if (listghe.get(i).contains("B3") && listghe.get(i).contains(gio)) {
                B3.setEnabled(false);
                B3.setTextColor(Color.RED);
                B3.setButtonTintList(ColorStateList.valueOf(Color.RED));
            } else {
                B3.setEnabled(true);
            }
        }
        for (int i = 0; i < listghe.size(); i++) {
            if (listghe.get(i).contains("B4") && listghe.get(i).contains(gio)) {
                B4.setEnabled(false);
                B4.setTextColor(Color.RED);
                B4.setButtonTintList(ColorStateList.valueOf(Color.RED));
            } else {
                B4.setEnabled(true);
            }
        }
        for (int i = 0; i < listghe.size(); i++) {
            if (listghe.get(i).contains("C1") && listghe.get(i).contains(gio)) {
                C1.setEnabled(false);
                C1.setTextColor(Color.RED);
                C1.setButtonTintList(ColorStateList.valueOf(Color.RED));
            } else {
                C1.setEnabled(true);
            }
        }
        for (int i = 0; i < listghe.size(); i++) {
            if (listghe.get(i).contains("C2") && listghe.get(i).contains(gio)) {
                C2.setEnabled(false);
                C2.setTextColor(Color.RED);
                C2.setButtonTintList(ColorStateList.valueOf(Color.RED));
            } else {
                C2.setEnabled(true);
            }
        }
        for (int i = 0; i < listghe.size(); i++) {
            if (listghe.get(i).contains("C3") && listghe.get(i).contains(gio)) {
                C3.setEnabled(false);
                C3.setTextColor(Color.RED);
                C3.setButtonTintList(ColorStateList.valueOf(Color.RED));
            } else {
                C3.setEnabled(true);
            }
        }
        for (int i = 0; i < listghe.size(); i++) {
            if (listghe.get(i).contains("C4") && listghe.get(i).contains(gio)) {
                C4.setEnabled(false);
                C4.setTextColor(Color.RED);
                C4.setButtonTintList(ColorStateList.valueOf(Color.RED));
            } else {
                C4.setEnabled(true);
            }
        }
        for (int i = 0; i < listghe.size(); i++) {
            if (listghe.get(i).contains("D1") && listghe.get(i).contains(gio)) {
                D1.setEnabled(false);
                D1.setTextColor(Color.RED);
                D1.setButtonTintList(ColorStateList.valueOf(Color.RED));
            } else {
                D1.setEnabled(true);
            }
        }
        for (int i = 0; i < listghe.size(); i++) {
            if (listghe.get(i).contains("D2") && listghe.get(i).contains(gio)) {
                D2.setEnabled(false);
                D2.setTextColor(Color.RED);
                D2.setButtonTintList(ColorStateList.valueOf(Color.RED));
            } else {
                D2.setEnabled(true);
            }
        }
        for (int i = 0; i < listghe.size(); i++) {
            if (listghe.get(i).contains("D3") && listghe.get(i).contains(gio)) {
                D3.setEnabled(false);
                D3.setTextColor(Color.RED);
                D3.setButtonTintList(ColorStateList.valueOf(Color.RED));
            } else {
                D3.setEnabled(true);
            }
        }
        for (int i = 0; i < listghe.size(); i++) {
            if (listghe.get(i).contains("D4") && listghe.get(i).contains(gio)) {
                D4.setEnabled(false);
                D4.setTextColor(Color.RED);
                D4.setButtonTintList(ColorStateList.valueOf(Color.RED));
            } else {
                D4.setEnabled(true);
            }
        }
    }
        private void Listener(){
            list_st = new ArrayList<>();
            CompoundButton.OnCheckedChangeListener listener1 = new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int i = 1;
                    if (isChecked){
                        i++;
                        tang();
                        list_st.add(buttonView.getText() + "");
                    }else {
                        list_st.remove(buttonView.getText());
                        giam();
                    }

                }
            };
            A1.setOnCheckedChangeListener(listener1);
            A2.setOnCheckedChangeListener(listener1);
            A3.setOnCheckedChangeListener(listener1);
            A4.setOnCheckedChangeListener(listener1);
            B1.setOnCheckedChangeListener(listener1);
            B2.setOnCheckedChangeListener(listener1);
            B3.setOnCheckedChangeListener(listener1);
            B4.setOnCheckedChangeListener(listener1);
            C1.setOnCheckedChangeListener(listener1);
            C2.setOnCheckedChangeListener(listener1);
            C3.setOnCheckedChangeListener(listener1);
            C4.setOnCheckedChangeListener(listener1);
            D1.setOnCheckedChangeListener(listener1);
            D2.setOnCheckedChangeListener(listener1);
            D3.setOnCheckedChangeListener(listener1);
            D4.setOnCheckedChangeListener(listener1);
    }
}
