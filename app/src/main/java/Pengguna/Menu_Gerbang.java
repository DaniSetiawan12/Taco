package Pengguna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.example.teco.R;

import controlling.Controlling;

public class Menu_Gerbang extends AppCompatActivity {

    LinearLayout linearBukaGerbang;
    LinearLayout linearJedaBukaGerbang;
    LinearLayout linearTutupGerbang;
    LinearLayout linearJedaTutupGerbang;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.menu__gerbang);

        linearBukaGerbang = (LinearLayout) findViewById(R.id.linearBukaGerbang);
        linearJedaBukaGerbang = (LinearLayout) findViewById(R.id.linearJedaBukaGerbang);
        linearTutupGerbang = (LinearLayout) findViewById(R.id.linearTutupGerbang);
        linearJedaTutupGerbang = (LinearLayout) findViewById(R.id.linearJedaTutupGerbang);


        linearBukaGerbang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controlling controlling = new Controlling();
                controlling.controll(requestQueue, Menu_Gerbang.this, "Buka Gerbang", "Berhasil Membuka Gerbang");
            }
        });
        linearJedaBukaGerbang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controlling controlling = new Controlling();
                controlling.controll(requestQueue, Menu_Gerbang.this, "Jeda Buka Pintu", "Gerbang Buka Terjeda");
            }
        });
        linearTutupGerbang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controlling controlling = new Controlling();
                controlling.controll(requestQueue, Menu_Gerbang.this, "Tutup Pintu", "Berhasil Menutup Pintu");
            }
        });
        linearJedaTutupGerbang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controlling controlling = new Controlling();
                controlling.controll(requestQueue, Menu_Gerbang.this, "Jeda Tutup Pintu", "Gerbang Tutup Terjeda");
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(Menu_Gerbang.this, Menu_Pengguna.class);
        startActivity(i);
        finish();
    }
}