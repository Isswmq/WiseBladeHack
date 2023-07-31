package com.example.examplemod.module.impl.PLAYER;

import com.example.examplemod.Module;
import com.example.examplemod.Utils.Shadow;
import com.example.examplemod.Utils.template.common.Lang;
import com.example.examplemod.Utils.template.styled.StyledFont;
import com.example.examplemod.Utils.template.styled.StyledFontRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.awt.*;
import java.text.DecimalFormat;

public class Cord extends Module {

    private static final StyledFont font = new StyledFont("font2.ttf", 12, 0.0f, 2.0f, -10.5f, Lang.ENG_RU);

    public Cord() {
        super("Cord", 0, Category.PLAYER);
    }

    @SubscribeEvent
    public void onUpdate(RenderGameOverlayEvent event){
        if (event.getType() != RenderGameOverlayEvent.ElementType.TEXT) {
            return;
        }

        DecimalFormat df = new DecimalFormat("###.###");
        String x = df.format(mc.player.getX());
        String y = df.format(mc.player.getY());
        String z = df.format(mc.player.getZ());
        String cord = x + " / " + y + " / " + z;

        MatrixStack matrixStack = event.getMatrixStack();
        Shadow.drawShadow(11, 255, 8, mc.font.width(cord) - 5);
        StyledFontRenderer.drawString(matrixStack, font, cord, 14, 252, new Color(0x8E56BE));
    }
}
