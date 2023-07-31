package com.example.examplemod.module.impl.PLAYER;

import com.example.examplemod.Module;
import net.minecraft.client.multiplayer.PlayerController;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MiddleClickPearl extends Module {
    public MiddleClickPearl() {
        super("MiddleClickPerl", 0, Category.PLAYER);
    }

    private boolean pearlClicked = false;

    private int selected;

    @SubscribeEvent
    public void onMouseInput(InputEvent.MouseInputEvent event) {
        if (mc.player != null) {
             if (event.getButton() == 2) {
                 if (!pearlClicked) {
                     int selected = mc.player.inventory.selected;
                     for (int i = 0; i <= 8; i++) {
                         ItemStack itemStack = mc.player.inventory.getItem(i);
                         if (!itemStack.isEmpty() && itemStack.getItem() == Items.ENDER_PEARL) {
                             pearlClicked = true;
                             System.out.println(i);
                             mc.player.inventory.selected = i;
                             new PlayerController(mc, mc.player.connection).useItem(mc.player, mc.level, Hand.MAIN_HAND);
                             break;
                         }
                     }
                 } else {
                     pearlClicked = false;
                     mc.player.inventory.selected = selected;
                     System.out.println("else selected" + selected);
                 }
            }
        }
    }
}
