package com.example.agricultureproducts.cart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.agricultureproducts.MActivity;
import com.example.agricultureproducts.R;

public class SuccessActivity extends AppCompatActivity {

    private TextView back_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        back_home = findViewById(R.id.back_home);

        back_home.setOnClickListener(v -> {
            startActivity(new Intent(this, MActivity.class));
            overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            finish();
        });
    }
}
