package com.netcatgirl.immersivethunder;

import com.netcatgirl.immersivethunder.platform.Services;

public class CommonClass {

    public static void init() {
        if (Services.PLATFORM.isModLoaded(Constants.MOD_ID)) {

            Constants.LOG.info("{} loaded", Constants.MOD_NAME);
        }
    }
}
