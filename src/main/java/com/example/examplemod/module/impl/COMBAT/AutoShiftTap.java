package com.example.examplemod.module.impl.COMBAT;

import com.example.examplemod.Module;
import net.minecraft.network.play.client.CEntityActionPacket;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class AutoShiftTap extends Module {

    public AutoShiftTap(){
        super("AutoShiftTap", 0, Category.COMBAT);
    }

    @SubscribeEvent
    public void onAttack(AttackEntityEvent event){
        if(mc.player != null){
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mc.player.input.shiftKeyDown = true;
                    Objects.requireNonNull(mc.getConnection()).send(new CEntityActionPacket(mc.player, CEntityActionPacket.Action.PRESS_SHIFT_KEY));
                }
            }, 20);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Objects.requireNonNull(mc.getConnection()).send(new CEntityActionPacket(mc.player, CEntityActionPacket.Action.RELEASE_SHIFT_KEY));
                }
            },50);
        }
    }
}
