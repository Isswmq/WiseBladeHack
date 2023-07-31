package com.example.examplemod.module.impl.PLAYER;

import com.example.examplemod.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.text.DecimalFormat;

public class DeathCord extends Module {

    public DeathCord(){
        super("DeathCord", 0, Category.PLAYER);
    }
    private boolean deathDetected;

    @SubscribeEvent
    public void writeDeathCord(RenderWorldLastEvent event){
        if(mc.player != null && mc.player.isDeadOrDying() && !deathDetected){
            DecimalFormat df = new DecimalFormat("###.###");
            String x = df.format(mc.player.getX());
            String y = df.format(mc.player.getY());
            String z = df.format(mc.player.getZ());

            Minecraft.getInstance().player.sendMessage(new StringTextComponent(x + " / " + y + " / " + z), Minecraft.getInstance().player.getUUID());

            deathDetected = true;
        }
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        deathDetected = false;
    }
}
