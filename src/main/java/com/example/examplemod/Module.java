package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class Module {
    public String name;
    public boolean toggled;
    public int keyCode;
    public Category category;
    public Minecraft mc = Minecraft.getInstance();

    public enum Category{
        COMBAT,
        VISUALS,
        MOVEMENT,
        PLAYER,
    }

    public Module(String name, int key, Category category){
        this.name = name;
        this.keyCode = key;
        this.category = category;
    }

    public void onEnabled(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void onDisable(){
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    public boolean isToggled(){
        return toggled;
    }

    public void toggle(){
        toggled = !toggled;
        if(toggled){
            this.onEnabled();
        }else{
            this.onDisable();
        }
    }

    public void setToggled(boolean toggled){
        this.toggled = toggled;
        if(this.toggled){
            onEnabled();
        }else{
            onDisable();
        }
    }

    public int getKey(){
        return keyCode;
    }

    public void setKeyCode(int key){
        this.keyCode = key;
    }

    public Category getCategory(){
        return category;
    }

    public String getName(){
        return name;
    }
}
