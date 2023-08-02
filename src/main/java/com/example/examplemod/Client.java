package com.example.examplemod;

import com.example.examplemod.module.impl.COMBAT.*;
import com.example.examplemod.module.impl.PLAYER.AutoRespawn;
import com.example.examplemod.module.impl.MOVEMENT.Sprint;
import com.example.examplemod.module.impl.PLAYER.*;
import com.example.examplemod.module.impl.PLAYER.AutoLeave;
import com.example.examplemod.module.impl.VISUALS.*;

import java.util.concurrent.CopyOnWriteArrayList;

public class Client {
    public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<>();

    public static void startup() {
        modules.add(new Hitbox());
        modules.add(new GlowEsp());
        modules.add(new Sprint());
        modules.add(new HUD());
        modules.add(new Fullbright());
        modules.add(new AutoLeave());
        modules.add(new Tracers());
        modules.add(new TriggerBot());
        modules.add(new ElytraSwap());
        modules.add(new AutoShiftTap());
        modules.add(new MiddleClickPearl());
        modules.add(new BlockReach());
        modules.add(new HideName());
        modules.add(new MiddleClickFriend());
        modules.add(new NoDamageFriends());
        modules.add(new AutoRespawn());
        modules.add(new KTLeave());
        modules.add(new ChestEsp());
        modules.add(new DeathCord());
        modules.add(new FogColor());
        modules.add(new Cord());
        modules.add(new FPS());
        modules.add(new SelfDestruct());
    }

    public static void keyPress(int key, int action){
        if(action == 1){
            for(Module m : modules){
                if(m.getKey() == key){
                    m.toggle();
                }
            }
        }
    }
}
