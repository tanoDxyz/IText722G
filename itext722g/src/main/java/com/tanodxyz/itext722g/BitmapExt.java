package com.tanodxyz.itext722g;

import android.graphics.Bitmap;

public class BitmapExt {
    private final Bitmap bitmap;

    public BitmapExt(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void recycle() {
        try {
            this.bitmap.recycle();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
