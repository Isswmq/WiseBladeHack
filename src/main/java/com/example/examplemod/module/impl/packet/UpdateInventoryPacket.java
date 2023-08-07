package com.example.examplemod.module.impl.packet;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraftforge.fml.network.NetworkEvent;

public class UpdateInventoryPacket {
    private static PlayerInventory inventory;

    public static PlayerInventory getInventory(){
        return inventory;
    }

    public static void handle(NetworkEvent.Context context){

        if(context.getDirection().getReceptionSide().isClient()){
            PlayerEntity player = context.getSender();
            if(player != null){
                player.inventory.clearContent();
                for(int i = 0; i < getInventory().getContainerSize(); i++) {
                    player.inventory.setItem(i, getInventory().getItem(i));
                }
            }
        }
    }
}
