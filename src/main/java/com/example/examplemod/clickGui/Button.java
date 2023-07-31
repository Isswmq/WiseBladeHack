package com.example.examplemod.clickGui;


import com.example.examplemod.Module;
import com.example.examplemod.Utils.template.common.Lang;
import com.example.examplemod.Utils.template.styled.StyledFont;
import com.example.examplemod.Utils.template.styled.StyledFontRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;

import java.awt.*;

public class Button {
    public Minecraft mc = Minecraft.getInstance();
    private static final StyledFont font = new StyledFont("font2.ttf", 16, 0.0f, 2.0f, -10.5f, Lang.ENG_RU);

    public int x, y, width, height;
    public boolean binding;
    public Module module;

    public Button(int x, int y, int width, int height, Module module) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.module = module;
    }

    public void drawScreen(MatrixStack m, int mouseX, int mouseY, float partialTicks) {
        StyledFontRenderer.drawString(m, font, binding ? "{Bind}" : module.name, x + 20, y + height / 2 - 9 / 2, module.toggled ? new Color(0x9F2FF6) : new Color(0xFFFFFF));
    }

    protected void keyTyped(int keyCode) {
        if (binding) {
            module.keyCode = keyCode;
            binding = false;
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (MainGui.hovered(mouseX, mouseY, x, y, x + width, y + height)) {
            if (mouseButton == 0) {
                module.toggle();
            } else if (mouseButton == 2) {
                binding = !binding;
            }
        }
    }

    protected void mouseReleased(int mouseX, int mouseY, int state) {
    }
}