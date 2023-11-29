package anhntph36936.fpoly.datvexemphim.Main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import anhntph36936.fpoly.datvexemphim.Frag_Adm_du1.Frag_Adm_DMK_du1;
import anhntph36936.fpoly.datvexemphim.Frag_Adm_du1.Frag_Adm_Phim_du1;
import anhntph36936.fpoly.datvexemphim.Frag_Adm_du1.Frag_Adm_QLTK_du1;
import anhntph36936.fpoly.datvexemphim.Frag_Adm_du1.Frag_Adm_QLTL_du1;
import anhntph36936.fpoly.datvexemphim.Frag_Adm_du1.Frag_Adm_QLXC_du1;
import anhntph36936.fpoly.datvexemphim.Frag_Adm_du1.Frag_Adm_Trangchu_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class Main_Adm_du1 extends AppCompatActivity {
    DrawerLayout drawe_adm;
    Toolbar toolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_adm_du1);
        drawe_adm = findViewById(R.id.drawer_adm);
        toolbar = findViewById(R.id.toolbar);
        frameLayout = findViewById(R.id.framelayout);
        navigationView = findViewById(R.id.navigation);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawe_adm, toolbar, R.string.open, R.string.close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        drawe_adm.addDrawerListener(drawerToggle);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.framelayout, new Frag_Adm_Trangchu_du1()).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.mHome){
                    fm.beginTransaction().replace(R.id.framelayout, new Frag_Adm_Trangchu_du1()).commit();
                } else if (item.getItemId() == R.id.mPhim) {
                    fm.beginTransaction().replace(R.id.framelayout, new Frag_Adm_Phim_du1()).commit();
                } else if (item.getItemId() == R.id.mQLTaiKhoan) {
                    fm.beginTransaction().replace(R.id.framelayout, new Frag_Adm_QLTK_du1()).commit();
                } else if (item.getItemId() == R.id.mTheLoai) {
                    fm.beginTransaction().replace(R.id.framelayout, new Frag_Adm_QLTL_du1()).commit();
                } else if (item.getItemId() == R.id.mXuatChieu) {
                    fm.beginTransaction().replace(R.id.framelayout, new Frag_Adm_QLXC_du1()).commit();
                } else if (item.getItemId() == R.id.mDoiMK) {
                    fm.beginTransaction().replace(R.id.framelayout, new Frag_Adm_DMK_du1()).commit();
                } else if (item.getItemId() == R.id.mThoat) {
                    showExit();
                }
                drawe_adm.close();
                return true;
            }
        });


    }
    private void showExit(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Main_Adm_du1.this);
        builder.setTitle("Exit !");
        builder.setIcon(R.drawable.errol);
        builder.setMessage("Bạn muốn đăng xuất ?");
        builder.setCancelable(false);

        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Main_Adm_du1.this, DangNhap_du1.class));
                finish();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog =builder.create();
        dialog.show();
    }


}