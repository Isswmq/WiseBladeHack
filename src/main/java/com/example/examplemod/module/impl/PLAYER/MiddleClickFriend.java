package com.example.examplemod.module.impl.PLAYER;

import com.example.examplemod.Module;
import com.example.examplemod.module.key.manager.MiddleClickFriendManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

public class MiddleClickFriend extends Module {

    public MiddleClickFriend(){
        super("MiddleClickFriend", 0, Category.PLAYER);
    }

    boolean isMouseButtonPressed;

    @SubscribeEvent
    public void onUpdate(InputEvent.MouseInputEvent event){
        RayTraceResult hitResult = mc.hitResult;

        if(event.getButton() == 2){
            if(event.getAction() == GLFW.GLFW_PRESS){
                if(!isMouseButtonPressed){
                    isMouseButtonPressed = true;
                    if(hitResult != null && hitResult.getType() == RayTraceResult.Type.ENTITY){
                        EntityRayTraceResult entityRayTraceResult = (EntityRayTraceResult) hitResult;
                        Entity entity = entityRayTraceResult.getEntity();
                        if(entity instanceof PlayerEntity){
                            PlayerEntity friend = (PlayerEntity) entity;
                            if(!MiddleClickFriendManager.checkFriend(friend)){
                                MiddleClickFriendManager.friends.add(friend);
                                Minecraft.getInstance().player.sendMessage(new StringTextComponent(friend.getScoreboardName() + "- added"), Minecraft.getInstance().player.getUUID());
                            }else {
                                MiddleClickFriendManager.friends.remove(friend);
                            }
                        }
                    }
                }
            } else if (event.getAction() == GLFW.GLFW_RELEASE) {
                isMouseButtonPressed = false;
            }
        }
    }
}
