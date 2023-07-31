package com.example.examplemod.module.impl.COMBAT;

import com.example.examplemod.Module;
import com.example.examplemod.module.key.manager.MiddleClickFriendManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
public class NoDamageFriends extends Module {

    public NoDamageFriends() {
        super("NoDamageFriends", 0, Category.COMBAT);
    }

    @SubscribeEvent
    public void onUpdate(AttackEntityEvent event){
        if(event.getEntity() instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) event.getEntity();
            if(MiddleClickFriendManager.checkFriend(player)) {
                event.setCanceled(true);
            }
        }
    }
}
