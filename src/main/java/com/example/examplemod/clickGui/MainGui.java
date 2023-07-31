package com.example.examplemod.clickGui;

import com.example.examplemod.Module;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

import static com.example.examplemod.clickGui.draggableListener.setMouseX;
import static com.example.examplemod.clickGui.draggableListener.setMouseY;

public class MainGui extends Screen {
    public List<Panel> panels = new ArrayList<>();

    public MainGui() {
        super(new StringTextComponent("WiseBlade?"));

        for (Module.Category value : Module.Category.values()) {

            if(value.equals(Module.Category.COMBAT)){
                panels.add(new Panel(10, 10, 100, 12, value));
            }

            if(value.equals(Module.Category.MOVEMENT)){
                panels.add(new Panel(10, 107, 100, 12, value));
            }

            if(value.equals(Module.Category.VISUALS)){
                panels.add(new Panel(130, 10, 100, 12, value));
            }

            if(value.equals(Module.Category.PLAYER)){
                panels.add(new Panel(250, 10, 120, 12, value));
            }
        }
    }

    @Override
    public void init() {

    }

    @Override
    public boolean keyPressed(int p_231046_1_, int p_231046_2_, int p_231046_3_) {
        for (Panel panel : panels) {
            panel.keyTyped(p_231046_1_);
        }

        return super.keyPressed(p_231046_1_, p_231046_2_, p_231046_3_);
    }

    @Override
    public void mouseMoved(double p_212927_1_, double p_212927_3_) {
        super.mouseMoved(p_212927_1_, p_212927_3_);
    }

    @Override
    public boolean mouseDragged(double x, double y, int mouse, double p_231045_6_, double p_231045_8_) {
        if (mouse == 1) {
            setMouseX((int) x);
            setMouseY((int) y);
        }
        return super.mouseDragged(x, y, mouse, p_231045_6_, p_231045_8_);
    }

    @Override
    public boolean mouseReleased(double p_231048_1_, double p_231048_3_, int p_231048_5_) {
        for (Panel panel : panels) {
            panel.mouseReleased((int) p_231048_1_, (int) p_231048_3_, p_231048_5_);
        }
        return super.mouseReleased(p_231048_1_, p_231048_3_, p_231048_5_);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        for (Panel panel : panels) {
            panel.mouseClicked((int) mouseX, (int) mouseY, mouseButton);
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void onClose() {
        super.onClose();
    }

    @Override
    public void render(MatrixStack m, int x, int y, float partialTicks) {
        this.renderBackground(m);
        for (Panel panel : panels) {
            panel.drawScreen(m, x, y, partialTicks);
        }
        super.render(m, x, y, partialTicks);
    }

    public static boolean hovered(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX > x && mouseY > y && mouseX < width && mouseY < height;
    }
}
