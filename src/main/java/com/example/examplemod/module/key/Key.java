package com.example.examplemod.module.key;
import com.example.examplemod.clickGui.MainGui;
import com.example.examplemod.Client;
import com.example.examplemod.module.impl.PLAYER.SelfDestruct;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class Key {

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event){
        if (event.getKey() == 344 && event.getAction() == 1 && Minecraft.getInstance().screen == null && !SelfDestruct.panic) {
            Minecraft.getInstance().setScreen(new MainGui());
        }
        Client.keyPress(event.getKey(), event.getAction());
    }
}
