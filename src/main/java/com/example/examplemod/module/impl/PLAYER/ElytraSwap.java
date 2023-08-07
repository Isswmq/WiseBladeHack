package com.example.examplemod.module.impl.PLAYER;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module;
import com.example.examplemod.module.impl.packet.ElytraSwapPacket;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.network.PacketDistributor;

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
                ElytraSwapPacket swapPacket = new ElytraSwapPacket(8, 2);
                ExampleMod.CHANNEL.send(PacketDistributor.SERVER.noArg(), swapPacket);
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
                ElytraSwapPacket swapPacket = new ElytraSwapPacket(8, 2);
                ExampleMod.CHANNEL.send(PacketDistributor.SERVER.noArg(), swapPacket);
                //mc.player.containerMenu.broadcastChanges();
                //mc.player.tick();
            }
        }
    }
}
