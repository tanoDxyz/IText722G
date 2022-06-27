package com.tanodxyz.itext722;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tanodxyz.itext722g.IText722;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IText722.init(this);
        new Thread(()->{
            try {
                new Barcode39Test().barcode01Test();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}