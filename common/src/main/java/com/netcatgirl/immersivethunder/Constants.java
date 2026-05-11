package com.netcatgirl.immersivethunder;

import net.minecraft.sounds.SoundEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Constants {

    public static final String MOD_ID = "immersivethunder";
    public static final String MOD_NAME = "ImmersiveThunder: Reforged";
    public static final String MOD_SLUG = "immersivethunder-reforged";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static final Object THUNDER_CLOSE = location("thunder_close");
    public static SoundEvent ENTITY_LIGHTNING_BOLT_THUNDER_CLOSE = createVariableRangeEvent(THUNDER_CLOSE);

    public static final Object THUNDER_MEDIUM = location("thunder_medium");
    public static SoundEvent ENTITY_LIGHTNING_BOLT_THUNDER_MEDIUM = createVariableRangeEvent(THUNDER_MEDIUM);

    public static final Object THUNDER_FAR = location("thunder_far");
    public static SoundEvent ENTITY_LIGHTNING_BOLT_THUNDER_FAR = createVariableRangeEvent(THUNDER_FAR);

    public static void registerSound(Object registry, Object location, SoundEvent soundEvent) {
        try {
            Class<?> registryClass = requiredClass("net.minecraft.core.Registry", "net.minecraft.class_2378");
            Method register = getMethod(registryClass, new String[] {"register", "method_10230"}, registryClass, location.getClass(), Object.class);
            register.invoke(null, registry, location, soundEvent);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException exception) {
            throw new IllegalStateException(MOD_NAME + " failed to register sound event " + location, exception);
        }
    }

    private static Object location(String path) {
        Object location = tryLocation("net.minecraft.resources.Identifier", path);
        if (location != null) {
            return location;
        }
        location = tryLocation("net.minecraft.resources.ResourceLocation", path);
        if (location != null) {
            return location;
        }
        location = tryLocation("net.minecraft.class_2960", path);
        if (location != null) {
            return location;
        }
        throw new IllegalStateException(MOD_NAME + " found no supported Minecraft location class");
    }

    private static Object tryLocation(String className, String path) {
        try {
            Class<?> locationClass = Class.forName(className);
            Method factory = getMethod(locationClass, new String[] {"fromNamespaceAndPath", "method_60655"}, String.class, String.class);
            return factory.invoke(null, MOD_ID, path);
        } catch (ClassNotFoundException exception) {
            return null;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException exception) {
            throw new IllegalStateException(MOD_NAME + " failed to create Minecraft location " + MOD_ID + ":" + path, exception);
        }
    }

    private static SoundEvent createVariableRangeEvent(Object location) {
        try {
            Method factory = getMethod(SoundEvent.class, new String[] {"createVariableRangeEvent", "method_47908"}, location.getClass());
            return (SoundEvent) factory.invoke(null, location);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException exception) {
            throw new IllegalStateException(MOD_NAME + " failed to create sound event " + location, exception);
        }
    }

    private static Method getMethod(Class<?> owner, String[] names, Class<?>... parameterTypes) throws NoSuchMethodException {
        for (String name : names) {
            try {
                return owner.getMethod(name, parameterTypes);
            } catch (NoSuchMethodException ignored) {
            }
        }
        throw new NoSuchMethodException(owner.getName() + "." + String.join("/", names));
    }

    private static Class<?> requiredClass(String... classNames) {
        for (String className : classNames) {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException ignored) {
            }
        }
        throw new IllegalStateException(MOD_NAME + " found no supported Minecraft class: " + String.join("/", classNames));
    }
}
