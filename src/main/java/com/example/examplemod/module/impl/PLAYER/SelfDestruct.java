package com.example.examplemod.module.impl.PLAYER;

import com.example.examplemod.Client;
import com.example.examplemod.Module;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SelfDestruct extends Module {

    public static boolean panic;
    private static File file = new File(Minecraft.getInstance().gameDirectory, "options.json");

    private HashMap<Module, Integer> disabledModules = new HashMap<>();


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

        Client.modules.stream()
                        .filter(module -> disabledModules.containsKey(module))
                        .forEach(module -> {
                            module.toggle();
                            module.setKeyCode(disabledModules.get(module));
                        });

        panic = false;
    }

    private void offModules(){
        for (Module module : Client.modules){
            if(module.toggled){
                if(!module.name.equals("Self Destruct")){
                    disabledModules.put(module, module.getKey());
                    module.toggle();
                    module.keyCode = 9999;
                }
            }
        }
    }
}
