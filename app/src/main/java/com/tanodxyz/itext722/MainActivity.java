package com.tanodxyz.itext722;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * //todo
         * static {
         *     Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);
         * }
         */
    }
}