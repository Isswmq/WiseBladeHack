package com.example.examplemod.module.impl.PLAYER;

import com.example.examplemod.Module;
import net.minecraft.network.play.client.CPlayerPacket;

public class KTLeave extends Module {

    public KTLeave() {
        super("KTLeave", 0, Category.PLAYER);
    }

    @Override
    public void onEnabled() {
        int xCoord = 8000 + interpolateRandom(-6500, 6500);
        int zCoord = 8000 + interpolateRandom(-6500, 6500);

        for(int i = 0; i < 70; ++i){
            mc.player.connection.send(new CPlayerPacket.PositionPacket(xCoord, 180.0, zCoord, true));
            mc.player.connection.send(new CPlayerPacket.PositionPacket(xCoord, 180.0, zCoord, true));
        }

        for (int i = 0; i < 70; ++i) {
            mc.player.connection.send(new CPlayerPacket.PositionPacket(mc.player.position().x, 180.0, mc.player.position().z, true));
            mc.player.connection.send(new CPlayerPacket.PositionPacket(mc.player.position().x, 180.0, mc.player.position().z, true));
        }
    }

    public static int interpolateRandom(int a, int b) {
        return (int)(Math.random() * (double)(b - a) + (double)a);
    }
}
