package com.netcatgirl.immersivethunder;

import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

    public static final String MOD_ID = "immersivethunder";
    public static final String MOD_NAME = "ImmersiveThunder: Reforged";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static final Identifier THUNDER_CLOSE = Identifier.fromNamespaceAndPath(MOD_ID, "thunder_close");
    public static SoundEvent ENTITY_LIGHTNING_BOLT_THUNDER_CLOSE = SoundEvent.createVariableRangeEvent(THUNDER_CLOSE);

    public static final Identifier THUNDER_MEDIUM = Identifier.fromNamespaceAndPath(MOD_ID, "thunder_medium");
    public static SoundEvent ENTITY_LIGHTNING_BOLT_THUNDER_MEDIUM = SoundEvent.createVariableRangeEvent(THUNDER_MEDIUM);

    public static final Identifier THUNDER_FAR = Identifier.fromNamespaceAndPath(MOD_ID, "thunder_far");
    public static SoundEvent ENTITY_LIGHTNING_BOLT_THUNDER_FAR = SoundEvent.createVariableRangeEvent(THUNDER_FAR);
}
