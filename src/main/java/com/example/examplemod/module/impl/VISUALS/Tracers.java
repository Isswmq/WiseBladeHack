package com.example.examplemod.module.impl.VISUALS;

import com.example.examplemod.Module;
import com.example.examplemod.module.key.manager.MiddleClickFriendManager;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.util.math.vector.Vector3d;

public class Tracers extends Module {
    public Tracers() {
        super("Tracers", 0, Category.VISUALS);
    }

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        Minecraft mc = Minecraft.getInstance();
        ClientPlayerEntity player = mc.player;
        ClientWorld world = mc.level;

        if (player == null || world == null) {
            return;
        }

        float partialTicks = event.getPartialTicks();
        Vector3d cameraPos = mc.player.getEyePosition(partialTicks);

        Vector3f vec = mc.gameRenderer.getMainCamera().getLookVector();
        MatrixStack matrixStack = event.getMatrixStack();

        matrixStack.pushPose();
        matrixStack.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);

        RenderSystem.disableTexture();
        RenderSystem.enableBlend();

        IRenderTypeBuffer.Impl bufferBuilder = mc.renderBuffers().bufferSource();
        IVertexBuilder buffer = bufferBuilder.getBuffer(RenderType.LINES);

        for (Entity entity : world.entitiesForRendering()) {
            if (entity instanceof LivingEntity && entity != player && entity.isAlive()) {
                Vector3d entityPos = entity.getEyePosition(partialTicks);
                float r , g, b, a = 0.65f;

                if(entity instanceof PlayerEntity && entity!= mc.player){
                    if(MiddleClickFriendManager.checkFriend((PlayerEntity) entity)) {
                        r = 0; g = 1; b = 0;
                    }else {
                        r = 1; g = 0; b = 0;
                    }
                    buffer.vertex(matrixStack.last().pose(), (float) entityPos.x, (float) entityPos.y, (float) entityPos.z).color(r,g,b,a).endVertex();
                    buffer.vertex(matrixStack.last().pose(), (float) cameraPos.x + vec.x(), (float) cameraPos.y + vec.y(), (float) cameraPos.z + vec.z()).color(r,g,b,a).endVertex();
                }
            }
        }

        bufferBuilder.endBatch(RenderType.LINES);

        RenderSystem.disableBlend();
        RenderSystem.enableTexture();

        matrixStack.popPose();
    }
}
