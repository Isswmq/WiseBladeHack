package com.example.examplemod.module.impl.PLAYER;

import com.example.examplemod.Module;
import net.minecraft.client.multiplayer.PlayerController;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ElytraItem;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ElytraSwap extends Module {
    private boolean state;
    private int slot;
    public ElytraSwap() {
        super("ElytraSwap", 0, Category.PLAYER);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if(event.getKey() == getKey()){
            if (mc.player != null) {
                PlayerController controller = new PlayerController(mc, mc.player.connection);
                state = !state;
                if(state){
                    if(!(mc.player.inventory.armor.get(2).getItem() instanceof ElytraItem)){
                        int elytraIndex = findElytra();
                        if(elytraIndex >= 0){
                            controller.handleInventoryMouseClick(0,elytraIndex,0, ClickType.PICKUP, mc.player);
                            controller.handleInventoryMouseClick(0,6,0, ClickType.PICKUP, mc.player);
                            controller.handleInventoryMouseClick(0,elytraIndex,0, ClickType.PICKUP, mc.player);
                        }
                        slot = elytraIndex;
                    }
                }else {
                    if(slot >= 0){
                        controller.handleInventoryMouseClick(0,slot,0, ClickType.PICKUP, mc.player);
                        controller.handleInventoryMouseClick(0,6,0, ClickType.PICKUP, mc.player);
                        controller.handleInventoryMouseClick(0,slot,0, ClickType.PICKUP, mc.player);
                    }
                    slot = -1;
                }
            }
        }
    }

    private int findElytra(){
        for (Slot slot : mc.player.inventoryMenu.slots){
            if(slot.getItem().getItem() instanceof  ElytraItem){
                return slot.index;
            }
        }
        return -1;
    }
}