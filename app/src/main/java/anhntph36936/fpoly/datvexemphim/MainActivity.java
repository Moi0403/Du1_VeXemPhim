package anhntph36936.fpoly.datvexemphim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import anhntph36936.fpoly.datvexemphim.Main.DangNhap_du1;
import anhntph36936.fpoly.datvexemphim.Main.Main_Adm_du1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent chao = new Intent(MainActivity.this, Main_Adm_du1.class);
                startActivity(chao);
            }
        }, 2000);
    }
}