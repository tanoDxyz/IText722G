package com.tanodxyz.itext722;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tanodxyz.itext722g.IText722;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IText722.init(this);
    }

}