package com.example.examplemod.module.impl.COMBAT;

import com.example.examplemod.Module;
import net.minecraft.network.play.client.CEntityActionPacket;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AutoShiftTap extends Module {

    public AutoShiftTap(){
        super("AutoShiftTap", 0, Category.COMBAT);
    }

    @SubscribeEvent
    public void onAttack(AttackEntityEvent event){
        mc.player.input.shiftKeyDown = true;
        mc.getConnection().send(new CEntityActionPacket(mc.player, CEntityActionPacket.Action.RELEASE_SHIFT_KEY));
        mc.getConnection().send(new CEntityActionPacket(mc.player, CEntityActionPacket.Action.PRESS_SHIFT_KEY));
    }
}
