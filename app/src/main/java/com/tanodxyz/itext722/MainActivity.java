package com.tanodxyz.itext722;

import static com.tanodxyz.itext722g.barcodes.Barcode39.getBarsCode39;
import static com.tanodxyz.itext722g.barcodes.Barcode39.getChecksum;
import static com.tanodxyz.itext722g.barcodes.Barcode39.getCode39Ex;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.tanodxyz.itext722g.BitmapExt;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatImageView imv = findViewById(R.id.imageView);
        Handler handler = new Handler();
        new Thread(()->{
            BitmapExt awtImage = createAwtImage(Color.BLACK, Color.WHITE);
            handler.post(()->{
                imv.setImageBitmap(awtImage.getBitmap());
            });
        }).start();
    }

    public BitmapExt createAwtImage(int foreground, int background) {
        int f = foreground;
        int g = background;
        android.graphics.Canvas canvas = new android.graphics.Canvas();
        String code = "Sample code";
        String bCode = code;
        if (true) {
            bCode = getCode39Ex(code);
        }
        if (true) {
            bCode += getChecksum(bCode);
        }
        int len = bCode.length() + 2;
        int nn = 2;
        int fullWidth = len * (6 + 3 * nn) + (len - 1);
        byte[] bars = getBarsCode39(bCode);
        boolean print = true;
        int ptr = 0;
        int height = 27;
        int[] pix = new int[fullWidth * height];
        for (int k = 0; k < bars.length; ++k) {
            int w = (bars[k] == 0 ? 1 : nn);
            int c = g;
            if (print) {
                c = f;
            }
            print = !print;
            for (int j = 0; j < w; ++j) {
                pix[ptr++] = c;
            }
        }
        for (int k = fullWidth; k < pix.length; k += fullWidth) {
            System.arraycopy(pix, 0, pix, k, fullWidth);
        }
        Bitmap bitmap = Bitmap.createBitmap(pix, 0, fullWidth, fullWidth, height, Bitmap.Config.ARGB_8888);
        return new BitmapExt(bitmap);
    }
}