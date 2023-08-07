package com.example.examplemod.module.impl.packet;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CEntityActionPacket;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Supplier;

public class ElytraSwapPacket {

    private final int slotIndex1;
    private final int slotIndex2;

    public ElytraSwapPacket(int slotIndex1, int slotIndex2){
        this.slotIndex1 = slotIndex1;
        this.slotIndex2 = slotIndex2;
    }

    public static void encode(ElytraSwapPacket packet, PacketBuffer buffer){
        buffer.writeInt(packet.slotIndex1);
        buffer.writeInt(packet.slotIndex2);
    }

    public static ElytraSwapPacket decode(PacketBuffer buffer){
        int slotIndex1 = buffer.readInt();
        int slotIndex2 = buffer.readInt();
        return new ElytraSwapPacket(slotIndex1, slotIndex2);
    }

    public static void handle(ElytraSwapPacket packet, Supplier<NetworkEvent.Context> contextSupplier){
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayerEntity sender = context.getSender();
            if (sender != null) {
                int slotIndex1 = packet.slotIndex1;
                int slotIndex2 = packet.slotIndex2;

                swapSlots(sender, slotIndex1, slotIndex2);

                sender.containerMenu.broadcastChanges();
                sender.tick();
            }
        });
        context.setPacketHandled(true);
    }

    public static void swapSlots(ServerPlayerEntity player, int slot1, int slot2){
            PlayerInventory inventory = player.inventory;

            ItemStack itemStack1 = inventory.getItem(slot1);
            ItemStack itemStack2 = inventory.getArmor(2);
            Timer timer = new Timer();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    inventory.armor.set(slot2, itemStack1);
                }
            }, 50);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                  inventory.setItem(slot1, itemStack2);
                }
            },50);
    }
}

