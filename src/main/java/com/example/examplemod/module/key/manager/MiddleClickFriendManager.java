package com.example.examplemod.module.key.manager;

import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;

public class MiddleClickFriendManager {

    public static ArrayList<PlayerEntity> friends = new ArrayList<>();

    public static boolean checkFriend(PlayerEntity entity){
        for (PlayerEntity friend : friends) {
            if(friend.getName().equals(entity.getName())){
                return true;
            }
        }
        return false;
    }
}
