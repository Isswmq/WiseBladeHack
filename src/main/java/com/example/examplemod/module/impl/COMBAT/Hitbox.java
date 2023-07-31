package com.example.examplemod.module.impl.COMBAT;

import com.example.examplemod.Module;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.client.Minecraft;

import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class Hitbox extends Module {
    private double size = 0.4;

    public Hitbox() {
        super("Hitbox", 0, Category.COMBAT);
    }

    @SubscribeEvent
    public void onUpdate(RenderPlayerEvent e) {
        Entity player = e.getEntity();

        if (player != Minecraft.getInstance().player) {
            player.setBoundingBox(new AxisAlignedBB(
                            player.getX() - size,
                            player.getBoundingBox().minY,
                            player.getZ() - size,
                            player.getX() + size,
                            player.getBoundingBox().maxY,
                            player.getZ() + size
                    )
            );
        }
    }

    @SubscribeEvent
    public void key(InputEvent.KeyInputEvent e) {
        int k = e.getKey();

        if (!(Minecraft.getInstance().screen instanceof ChatScreen)) {
            if (k == 75) {
                size += 0.05;
            } if (k == 74) {
               size -= 0.05;
            }
        }
    }
}
