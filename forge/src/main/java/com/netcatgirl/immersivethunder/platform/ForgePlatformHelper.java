package com.netcatgirl.immersivethunder.platform;

import com.netcatgirl.immersivethunder.platform.services.IPlatformHelper;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

import java.lang.reflect.Method;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        try {
            Method isLoaded = ModList.class.getMethod("isLoaded", String.class);
            try {
                Method get = ModList.class.getMethod("get");
                return (Boolean) isLoaded.invoke(get.invoke(null), modId);
            } catch (NoSuchMethodException ignored) {
                return (Boolean) isLoaded.invoke(null, modId);
            }
        } catch (ReflectiveOperationException exception) {
            throw new IllegalStateException("Unable to query Forge mod list", exception);
        }
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }
}
