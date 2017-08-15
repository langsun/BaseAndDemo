package com.example.sun.demo.demo;

import com.example.sun.demo.R;

/**
 * Created by sun on 17/8/15.
 */

public enum DeviceInfo {
    KJ520G_X05("KJ520G_X05", R.drawable.img_kj520g_x05),
    KJ800G_S60("KJ800G_S60", R.drawable.img_kj800g_s60),
    KJG420_X1("KJG420_X1", R.drawable.img_kjg420_x1),
    KJG500_Z6("KJG500_Z6", R.drawable.img_kjg500_z6),
    KXJ420G_X03("KXJ420G_X03", R.drawable.img_kxj420g_x03);


    public final int res;
    public final String name;

    DeviceInfo(String name, int res) {
        this.name = name;
        this.res = res;
    }
}
