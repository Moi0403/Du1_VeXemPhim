package anhntph36936.fpoly.datvexemphim;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import anhntph36936.fpoly.datvexemphim.Main.DangNhap_du1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent chao = new Intent(MainActivity.this, DangNhap_du1.class);
                startActivity(chao);
            }
        }, 2000);
    }
}