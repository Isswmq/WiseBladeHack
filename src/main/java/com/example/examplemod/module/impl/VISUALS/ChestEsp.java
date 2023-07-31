package com.example.examplemod.module.impl.VISUALS;

import com.example.examplemod.Module;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.ShulkerBoxTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class ChestEsp extends Module {
    public ChestEsp() {
        super("ChestEsp", 0 ,Category.VISUALS);
    }

    @SubscribeEvent
    public void onUpdate(RenderWorldLastEvent event){
        MatrixStack matrixStack = event.getMatrixStack();
        IRenderTypeBuffer.Impl bufferBuilder = Minecraft.getInstance().renderBuffers().bufferSource();
        IVertexBuilder buffer = bufferBuilder.getBuffer(RenderType.LINES);
        for(TileEntity o : mc.level.blockEntityList){
            if(o instanceof ChestTileEntity){
                BlockPos pos = o.getBlockPos();
                chestEsp(pos, matrixStack, buffer, 0, 1,1,1);
            }else if (o instanceof ShulkerBoxTileEntity){
                BlockPos pos = o.getBlockPos();
                chestEsp(pos, matrixStack, buffer, 120, 255, 20, 1);
            }
        }
    }

    public static void chestEsp(BlockPos pos, MatrixStack matrixStack, IVertexBuilder builder, int r, int g, int b, int a){
        GL11.glPushMatrix();
        double x = pos.getX() - Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().x;
        double y = pos.getY() - Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().y;
        double z = pos.getZ() - Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().z;

        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_BLEND);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);

        WorldRenderer.renderLineBox(matrixStack, builder, new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), r, g, b, a);

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }
}
