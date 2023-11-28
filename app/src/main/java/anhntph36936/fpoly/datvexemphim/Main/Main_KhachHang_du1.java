package anhntph36936.fpoly.datvexemphim.Main;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import anhntph36936.fpoly.datvexemphim.Frag_KhachHang_du1.Frag_KH_Home_du1;
import anhntph36936.fpoly.datvexemphim.Frag_KhachHang_du1.Frag_KH_Phim_du1;
import anhntph36936.fpoly.datvexemphim.Frag_KhachHang_du1.Frag_KH_USER_du1;
import anhntph36936.fpoly.datvexemphim.Frag_KhachHang_du1.Frag_KH_VE_du1;
import anhntph36936.fpoly.datvexemphim.R;

public class Main_KhachHang_du1 extends AppCompatActivity {

    DrawerLayout drawer_kh;
    FrameLayout frag_kh;
    BottomNavigationView bottom_kh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_khach_hang_du1);
        drawer_kh = findViewById(R.id.drawer_kh);
        frag_kh = findViewById(R.id.frag_kh);
        bottom_kh = findViewById(R.id.bottom_kh);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frag_kh, new Frag_KH_Home_du1()).commit();

        bottom_kh.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.mTrangChu_kh){
                    fragmentManager.beginTransaction().replace(R.id.frag_kh, new Frag_KH_Home_du1()).commit();
                } else if (item.getItemId() == R.id.mphim_kh){
                    fragmentManager.beginTransaction().replace(R.id.frag_kh, new Frag_KH_Phim_du1()).commit();
                } else if (item.getItemId() == R.id.mVe){
                    fragmentManager.beginTransaction().replace(R.id.frag_kh, new Frag_KH_VE_du1()).commit();
                } else if (item.getItemId() == R.id.mUser){
                    fragmentManager.beginTransaction().replace(R.id.frag_kh, new Frag_KH_USER_du1()).commit();
                }
                return true;
            }
        });

    }
}