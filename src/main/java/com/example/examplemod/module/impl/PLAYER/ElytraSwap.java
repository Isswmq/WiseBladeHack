package com.example.examplemod.module.impl.PLAYER;

import com.example.examplemod.Module;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;

public class ElytraSwap extends Module {
    public ElytraSwap() {
        super("ElytraSwap", 0, Category.PLAYER);
    }

    @Override
    public void onEnabled() {
        if (mc.player != null) {
            IInventory inventory = mc.player.inventory;

            ItemStack elytra = null;
            for (int i = 0; i < inventory.getContainerSize(); i++) {
                ItemStack stack = inventory.getItem(i);
                if (stack.getItem() instanceof ElytraItem && i == 8) {
                    elytra = stack;
                    break;
                }
            }

            if (elytra != null) {
                ItemStack armor = mc.player.inventory.armor.get(2);
                mc.player.inventory.armor.set(2, elytra);
                mc.player.inventory.items.set(8, armor);
                mc.player.containerMenu.broadcastChanges();
                mc.player.tick();
            }
        }
    }

    @Override
    public void onDisable() {
        if (mc.player != null) {
            IInventory inventory = mc.player.inventory;

            ItemStack chestplate = null;
            for (int i = 0; i < inventory.getContainerSize(); i++) {
                ItemStack stack = inventory.getItem(i);
                if (stack.getItem() instanceof ArmorItem && i == 8) {
                    chestplate = stack;
                    break;
                }
            }

            if (chestplate != null) {
                ItemStack elytra = mc.player.inventory.armor.get(2);
                mc.player.inventory.armor.set(2, chestplate);
                mc.player.inventory.items.set(8, elytra);
                mc.player.inventoryMenu.broadcastChanges();
                mc.player.tick();
            }

        }
    }
}
