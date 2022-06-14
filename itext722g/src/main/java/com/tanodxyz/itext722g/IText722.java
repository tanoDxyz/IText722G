package com.tanodxyz.itext722g;

import java.security.Security;

public class IText722 {
    static {
        Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);
    }
}
