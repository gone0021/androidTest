package com.example.poly.countdown;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.tvNum);
    }

    public void onButtonClick(View v) {
        text.setText("開始");

        CountDownAsyncTask t = new CountDownAsyncTask(text);

        t.execute();
    }
}
