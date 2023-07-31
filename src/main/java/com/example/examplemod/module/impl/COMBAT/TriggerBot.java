package com.example.examplemod.module.impl.COMBAT;

import com.example.examplemod.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TriggerBot extends Module {

    public TriggerBot() {
        super("TriggerBot", 0, Category.COMBAT);
    }

    @SubscribeEvent
    public void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {
        RayTraceResult rayTraceResult = mc.hitResult;
        if (rayTraceResult != null && rayTraceResult.getType() == RayTraceResult.Type.ENTITY) {
            EntityRayTraceResult entityRayTraceResult = (EntityRayTraceResult) rayTraceResult;
            Entity entity = entityRayTraceResult.getEntity();
            if (entity instanceof PlayerEntity) {
            PlayerEntity targetPlayer = (PlayerEntity) entity;
                if(mc.player != null && mc.gameMode != null){
                    if (mc.player.getAttackStrengthScale(0) == 1) {
                        mc.gameMode.attack(mc.player, targetPlayer);
                        mc.player.swing(Hand.MAIN_HAND);
                        mc.player.resetAttackStrengthTicker();
                    }
                }
            }
        }
    }
}
