package com.example.examplemod.module.impl.COMBAT;

import com.example.examplemod.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.concurrent.ThreadLocalRandom;

public class AimAssist extends Module {

    int lookAngle = 30;

    public AimAssist() {
        super("AimAssist", 0, Category.PLAYER);
    }

    @SubscribeEvent
    public void assist(LivingEvent.LivingUpdateEvent event) {
        if(mc.player == null){
            return;
        }

        Entity target = event.getEntity();

        if(target instanceof PlayerEntity && !target.equals(mc.player)){
            if (target.isAlive() && target.distanceTo(mc.player) < 5) {
                System.out.println(target.equals(mc.player));
                int delay = ThreadLocalRandom.current().nextInt(10, 21);

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                double playerX = mc.player.getX();
                double playerY = mc.player.getY();
                double playerZ = mc.player.getZ();

                double deltaX = target.getX() - playerX;
                double deltaY = target.getY() - playerY;
                double deltaZ = target.getZ() - playerZ;

                float yaw = (float) Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90;
                boolean isLookingClose = Math.abs(yaw - mc.player.yRot) < lookAngle;

                //if(isLookingClose){
                    yaw = MathHelper.wrapDegrees(yaw);

                    // Вычисляем pitch
                    double distanceXZ = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
                    float pitch = (float) -Math.toDegrees(Math.atan2(deltaY, distanceXZ));

                    // Ограничиваем углы
                    yaw = MathHelper.wrapDegrees(yaw);
                    pitch = MathHelper.clamp(pitch, -90, 90);

                    mc.player.yRot = yaw;
                    mc.player.xRot = pitch;
                //}
            }
        }
    }
}
