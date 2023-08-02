package com.example.examplemod.module.impl.PLAYER;

import com.example.examplemod.Client;
import com.example.examplemod.Module;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.IOException;

public class SelfDestruct extends Module {

    public static boolean panic;
    private static File file = new File(Minecraft.getInstance().gameDirectory, "options.json");

    public SelfDestruct() {
        super("Self Destruct", 0, Category.PLAYER);
    }

    @Override
    public void onEnabled(){
        if(file.exists()){
            file.delete();
        }try {
            file.createNewFile();
        }catch (IOException exception){

        }
        panic = true;
        offModules();
    }

    @Override
    public void onDisable() {
        if(file.exists()){
            file.delete();
        }
        panic = false;
    }

    public static void offModules(){
        for (Module module : Client.modules){
            if(module.toggled){
                if(!module.name.equals("Self Destruct")){
                    module.toggle();
                    module.keyCode = 99999;
                }
            }
        }
    }
}
