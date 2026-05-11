package com.netcatgirl.immersivethunder.platform;

import com.netcatgirl.immersivethunder.Constants;
import com.netcatgirl.immersivethunder.platform.services.IPlatformHelper;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

import java.lang.reflect.Method;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        try {
            Method isProduction = FMLLoader.class.getMethod("isProduction");
            return !(Boolean) isProduction.invoke(null);
        } catch (NoSuchMethodException ignored) {
            try {
                Method getCurrent = FMLLoader.class.getMethod("getCurrent");
                Object loader = getCurrent.invoke(null);
                Method isProduction = loader.getClass().getMethod("isProduction");
                return !(Boolean) isProduction.invoke(loader);
            } catch (ReflectiveOperationException exception) {
                throw new IllegalStateException(Constants.MOD_NAME + " unable to query NeoForge environment", exception);
            }
        } catch (ReflectiveOperationException exception) {
            throw new IllegalStateException(Constants.MOD_NAME + " unable to query NeoForge environment", exception);
        }
    }
}
