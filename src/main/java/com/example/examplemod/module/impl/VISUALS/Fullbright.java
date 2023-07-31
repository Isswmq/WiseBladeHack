package com.example.examplemod.module.impl.VISUALS;

import com.example.examplemod.Module;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;


public class Fullbright extends Module {

    public Fullbright() {
        super("Fullbright",0 , Category.VISUALS);
    }

    @Override
    public void onEnabled() {
        mc.player.addEffect(new EffectInstance(Effects.NIGHT_VISION, 9999999, 255, true, true));
    }

    @Override
    public void onDisable() {
        mc.player.removeEffect(Effects.NIGHT_VISION);
    }
}
