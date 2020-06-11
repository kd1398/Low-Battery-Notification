package com.example.lowbattery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MyNotifyActivity extends AppCompatActivity {

    String title,detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notify);

        TextView txttitle = findViewById(R.id.txttitle);
        TextView txtdetail = findViewById(R.id.txtdetail);

        title = this.getIntent().getExtras().getString("title");
        detail = this.getIntent().getExtras().getString("detail");

        txttitle.setText(title);
        txtdetail.setText(detail);
    }
}
