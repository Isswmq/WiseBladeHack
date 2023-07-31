package com.example.examplemod.module.impl.VISUALS;

import com.example.examplemod.Module;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GlowEsp extends Module {

    public GlowEsp() {
        super("GlowEsp", 0, Category.VISUALS);
    }

    @SubscribeEvent
    public void onRender(RenderPlayerEvent event){
        event.getEntity().setGlowing(true);
    }

    @Override
    public void onEnabled() {
        super.onEnabled();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        for(PlayerEntity p : mc.level.players()){
            p.setGlowing(false);
        }
    }
}
