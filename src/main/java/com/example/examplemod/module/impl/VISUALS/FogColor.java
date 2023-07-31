package com.example.examplemod.module.impl.VISUALS;

import com.example.examplemod.Module;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FogColor extends Module {

    public FogColor(){
        super("FogColor", 0, Category.VISUALS);
    }

    @SubscribeEvent
    public void fog(EntityViewRenderEvent.FogColors e) {
        e.setGreen(0);
        e.setRed(100);
        e.setBlue(200);
    }
}
