package com.example.examplemod.module.impl.VISUALS;

import com.example.examplemod.Module;
import com.example.examplemod.Utils.Shadow;
import com.example.examplemod.Utils.template.common.Lang;
import com.example.examplemod.Utils.template.styled.StyledFont;
import com.example.examplemod.Utils.template.styled.StyledFontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.awt.*;

public class FPS extends Module {

    private static final StyledFont font = new StyledFont("font2.ttf", 16, 0.0f, 2.0f, -10.5f, Lang.ENG_RU);

    public FPS(){
        super("ShowFPS", 0, Category.VISUALS);
    }


    @SubscribeEvent
    public void onUpdate(RenderGameOverlayEvent event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.TEXT) {
            return;
        }

        Shadow.drawShadow(80, 10,10, mc.font.width(mc.fpsString.split(" fps")[0]    ) + 23);
        StyledFontRenderer.drawString(event.getMatrixStack(), font,"FPS -> " + mc.fpsString.split(" fps")[0], 82, 10, new Color(0xE8437D));
    }
}
