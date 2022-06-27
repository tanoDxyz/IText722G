package com.tanodxyz.itext722g;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IText722 {
    private static Context appContext;
    public static String ANDROID_FONTS_DIR = "system" + File.separatorChar + "fonts";

    static {
        Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);
    }

    public static void init(Context context) {
        appContext = context.getApplicationContext();
    }

    public static Context getContext() {
        return appContext;
    }

    public static File getCacheDir() {
        return appContext.getCacheDir();
    }

    public static Map<String, Typeface> getSSystemFontMap() {
        Map<String, Typeface> sSystemFontMap = null;
        try {
            //Typeface typeface = Typeface.class.newInstance();
            Typeface typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL);
            Field f = Typeface.class.getDeclaredField("sSystemFontMap");
            f.setAccessible(true);
            sSystemFontMap = (Map<String, Typeface>) f.get(typeface);
            for (Map.Entry<String, Typeface> entry : sSystemFontMap.entrySet()) {
                Log.d("FontMap", entry.getKey() + " ---> " + entry.getValue() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sSystemFontMap;
    }

    private static List<String> getKeyWithValue(Map map, Typeface value) {
        Set set = map.entrySet();
        List<String> arr = new ArrayList<>();
        for (Object obj : set) {
            Map.Entry entry = (Map.Entry) obj;
            if (entry.getValue().equals(value)) {
                String str = (String) entry.getKey();
                arr.add(str);
            }
        }
        return arr;
    }

    public static InputStream getResourceStream(String path) throws IOException {
        return getContext().getAssets().open(path);
    }
}
