package com.qzc.adaptescreen;

/**
 * created by qinzhichang at 2020/11/02 14:31
 * desc:
 */
public class AdapterScreenConfig {

    private static AdapterScreenConfig adapterScreenConfig;
    private boolean isFontScale; //独立字体大小，不跟随系统

    private AdapterScreenConfig() {

    }

    public static AdapterScreenConfig getInstance() {
        if (adapterScreenConfig == null) {
            synchronized (AdapterScreenConfig.class) {
                if (adapterScreenConfig == null) {
                    adapterScreenConfig = new AdapterScreenConfig();
                }
            }
        }
        return adapterScreenConfig;
    }

    public boolean isFontScale() {
        return isFontScale;
    }

    public void setFontScale(boolean fontScale) {
        isFontScale = fontScale;
    }
}
