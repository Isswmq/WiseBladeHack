package com.example.examplemod.module.impl.MOVEMENT;

import com.example.examplemod.Module;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Sprint extends Module {

    public Sprint() {
        super("Sprint", 0, Category.MOVEMENT);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event){
        if(mc.player != null){
            if(mc.player.input.hasForwardImpulse() && !mc.player.horizontalCollision && !mc.player.isSprinting()){
                mc.player.setSprinting(true);
            }
        }
    }
}
