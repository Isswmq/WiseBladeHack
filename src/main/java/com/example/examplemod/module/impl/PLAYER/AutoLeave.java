package com.example.examplemod.module.impl.PLAYER;

import com.example.examplemod.Module;
import net.minecraft.network.play.server.SDisconnectPacket;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AutoLeave extends Module {

    public AutoLeave() {
        super("AutoLeave", 0, Category.PLAYER);
    }

    @SubscribeEvent
    public void onUpdate(RenderPlayerEvent event){
        if(event.getPlayer() != mc.player && mc.player != null){
            if(mc.player.distanceTo(event.getPlayer()) <= 15){
                mc.player.connection.handleDisconnect(new SDisconnectPacket());
            }
        }
    }
}
