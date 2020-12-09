package com.johanlund.mathgame.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.johanlund.mathgame.R;

public class MainActivity extends AppCompatActivity {
    final String TAG = getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}