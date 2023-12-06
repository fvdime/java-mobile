package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class RandomActivity extends AppCompatActivity {

    private EditText editText, editTextMin, editTextMax;
    private Button btn;
    private ScrollView scrollView;
    private LinearLayout linearLayoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        editText = findViewById(R.id.editTextAdet);
        editTextMin = findViewById(R.id.editTextMin);
        editTextMax = findViewById(R.id.editTextMax);
        btn = findViewById(R.id.btnCreateBars);
        scrollView = findViewById(R.id.scrollView);
        linearLayoutContainer = findViewById(R.id.container);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createProgressBars();
            }
        });
    }

    private void createProgressBars() {
        linearLayoutContainer.removeAllViews();

        int adet = Integer.parseInt(editText.getText().toString());

        int minGlobal = Integer.parseInt(editTextMin.getText().toString());
        int maxGlobal = Integer.parseInt(editTextMax.getText().toString());

        Random random = new Random();

        for (int i = 0; i < adet; i++) {
            int min = random.nextInt((maxGlobal - minGlobal) + 1) + minGlobal;
            int max = random.nextInt((maxGlobal - min) + 1) + min;

            int progress = random.nextInt((max - min) + 1) + min;
            int percentage = (progress - min) * 100 / (max - min);

            ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
            progressBar.setProgress(percentage);

            TextView textView = new TextView(this);
            textView.setText("Min: " + min + " Max: " + max + " Değer: " + progress + " Yüzde " + percentage + "%");

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            linearLayoutContainer.addView(progressBar, layoutParams);
            linearLayoutContainer.addView(textView, layoutParams);
        }

        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
}