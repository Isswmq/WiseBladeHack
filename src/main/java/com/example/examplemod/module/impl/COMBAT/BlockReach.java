package com.example.examplemod.module.impl.COMBAT;

import com.example.examplemod.Module;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.ForgeMod;

import java.util.Objects;

public class BlockReach extends Module {

    public BlockReach(){
        super("BlockReach", 0, Category.COMBAT);
    }

    @Override
    public void onEnabled() {
        Objects.requireNonNull(mc.player.getAttribute(ForgeMod.REACH_DISTANCE.get())).addPermanentModifier(new AttributeModifier(mc.player.getUUID(), "block reach", 1.0f, AttributeModifier.Operation.ADDITION));
    }

    @Override
    public void onDisable() {
        Objects.requireNonNull(mc.player.getAttribute(ForgeMod.REACH_DISTANCE.get())).removeModifier(mc.player.getUUID());
    }
}
