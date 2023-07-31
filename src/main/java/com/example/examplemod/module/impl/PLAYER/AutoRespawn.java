package com.example.examplemod.module.impl.PLAYER;

import com.example.examplemod.Module;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AutoRespawn extends Module {


    public AutoRespawn() {
        super("AutoRespawn", 0, Category.PLAYER);
    }

    @SubscribeEvent
    public void onUpdate(RenderWorldLastEvent event){
        if(mc.player != null && mc.player.isDeadOrDying()){
            mc.player.respawn();
        }
    }
}
