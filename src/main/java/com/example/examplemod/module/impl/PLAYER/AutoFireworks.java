package com.example.examplemod.module.impl.PLAYER;

import com.example.examplemod.Module;
import net.minecraft.client.multiplayer.PlayerController;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.network.play.client.CEntityActionPacket;
import net.minecraft.network.play.client.CPlayerTryUseItemPacket;
import net.minecraft.server.management.PlayerInteractionManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Timer;
import java.util.TimerTask;

public class AutoFireworks extends Module {
    private boolean state;
    private int slot;

    private boolean clicked;
    Timer timer = new Timer();

    public AutoFireworks(){
        super("AutoFireworks", 0, Category.PLAYER);
    }

    @SubscribeEvent
    public void onUpdate(InputEvent.MouseInputEvent event){
        if(mc.player != null && mc.level != null){
            if(event.getButton() == 3){
                if(mc.player.inventory.armor.get(2).getItem() instanceof ElytraItem){
                    if(!clicked){
                        clicked = true;
                        state = !state;
                        PlayerController controller = new PlayerController(mc, mc.player.connection);
                        if(state){
                            if(!(mc.player.inventory.offhand.get(0).getItem() instanceof FireworkRocketItem)){
                                int fireworksIndex = findFireworks();
                                if(fireworksIndex >= 0){
                                    controller.handleInventoryMouseClick(0,fireworksIndex,0, ClickType.PICKUP, mc.player);
                                    controller.handleInventoryMouseClick(0,45,0, ClickType.PICKUP, mc.player);
                                    controller.handleInventoryMouseClick(0,fireworksIndex,0, ClickType.PICKUP, mc.player);
                                }
                                slot = fireworksIndex;

                                startFlying(controller);

                            }
                        }else {
                            if(slot >= 0){
                                controller.handleInventoryMouseClick(0,slot,0, ClickType.PICKUP, mc.player);
                                controller.handleInventoryMouseClick(0,45,0, ClickType.PICKUP, mc.player);
                                controller.handleInventoryMouseClick(0,slot,0, ClickType.PICKUP, mc.player);
                            }
                            slot = -1;
                        }
                    }else {
                        clicked = false;
                    }
                }
            }
        }
    }

    private int findFireworks(){
        for (Slot slot : mc.player.inventoryMenu.slots){
            if(slot.getItem().getItem() instanceof FireworkRocketItem){
                return slot.index;
            }
        }
        return -1;
    }

    private void startFlying(PlayerController controller){
        if(mc.player.isOnGround()){
            mc.player.jumpFromGround();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mc.player.startFallFlying();
                mc.getConnection().send(new CEntityActionPacket(mc.player, CEntityActionPacket.Action.START_FALL_FLYING));
            }
        }, 30);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ActionResultType actionResultType = controller.useItem(mc.player, mc.level, Hand.OFF_HAND);
                System.out.println(actionResultType);
            }
        },150);
    }
}
