package com.example.examplemod.module.impl.VISUALS;

import com.example.examplemod.Client;
import com.example.examplemod.Module;
import com.example.examplemod.Utils.Shadow;
import com.example.examplemod.Utils.template.common.Lang;
import com.example.examplemod.Utils.template.styled.StyledFont;
import com.example.examplemod.Utils.template.styled.StyledFontRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.awt.*;
import java.util.Comparator;

public class HUD extends Module {

    private static final StyledFont font = new StyledFont("font2.ttf", 16, 0.0f, 2.0f, -10.5f, Lang.ENG_RU);

    public HUD() {
        super("HUD", 0, Category.VISUALS);
    }

    @SubscribeEvent
    public void onUpdate(RenderGameOverlayEvent event){
        switch (event.getType()) {
            case TEXT:
                MatrixStack matrices = event.getMatrixStack();
                if(event.getType() == RenderGameOverlayEvent.ElementType.TEXT){
                    int y = 11;
                    final int[] counter = {1};
                    Client.modules.sort(Comparator.comparingInt((Module m) -> mc.font.width(m.getName())).reversed());
                    for(Module module : Client.modules){
                        if(module.isToggled()){
                            int color = rainbow(counter[0] * 10);
                            Shadow.drawShadow(8, y,11, mc.font.width(module.getName()) + 15);
                            StyledFontRenderer.drawString(matrices, font, module.getName(), 10, y, new Color(color));
                            y += 11;
                            counter[0]++;
                        }
                    }
                }
            default:
                break;
        }
    }

    public static int rainbow(int delay) {
        double rainbowState = (Math.ceil((System.currentTimeMillis() + delay) / 10.0) % 360);
        return Color.getHSBColor((float) (rainbowState / 360.0f), 1f, 1f).getRGB();
    }
}
