package com.example.examplemod.clickGui;

import com.example.examplemod.Client;
import com.example.examplemod.Module;
import com.example.examplemod.Utils.template.RenderUtils;
import com.example.examplemod.Utils.template.common.Lang;
import com.example.examplemod.Utils.template.styled.StyledFont;
import com.example.examplemod.Utils.template.styled.StyledFontRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Panel {
    public Minecraft mc = Minecraft.getInstance();

    public int x, y, width, height, dragY, dragX;
    public boolean extended = true, dragging;
    public Module.Category category;

    public List<com.example.examplemod.clickGui.Button> buttons = new ArrayList<>();

    private static final StyledFont font = new StyledFont("font2.ttf", 16, 0.0f, 2.0f, -10.5f, Lang.ENG_RU);

    public Panel(int x, int y, int width, int height, Module.Category category) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.category = category;

        int y1 = y + height;

        Client.modules.sort(Comparator.comparingInt((Module m) -> mc.font.width(m.getName())).reversed());

        for (Module module : Client.modules) {
            if (module.category == category) {
                buttons.add(new Button(x, y1, width, height, module));
                y1 += height;
            }
        }
    }

    public void drawScreen(@Nonnull MatrixStack m, int mouseX, int mouseY, float partialTicks) {
        if (dragging) {
            x = mouseX - dragX;
            y = mouseY - dragY;
        }

        RenderUtils.drawRoundedRect(m, x, y, width, (buttons.size() * height) + height + 5, 11, new Color(0xFF211F1F, true));
        StyledFontRenderer.drawString(m, font ,category.name(), x + width / 2 - mc.font.width(category.name()) / 2, y + height / 2 - 9 / 2, new Color(0xE8437D));

        int y1 = y + height;
        for (Button button : buttons) {
            button.x = x;
            button.y = y1;

            y1 += height;

            button.drawScreen(m, mouseX, mouseY, partialTicks);
        }
    }

    protected void keyTyped(int keyCode) {
        if (extended) {
            for (Button button : buttons) {
                button.keyTyped(keyCode);
            }
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (MainGui.hovered(mouseX, mouseY, x, y, x + width, y + height)) {
            if (mouseButton == 0) {
                dragX = mouseX - x;
                dragY = mouseY - y;
                dragging = true;
            } else if (mouseButton == 1) {
                extended = !extended;
            }
        }

        if (extended) {
            for (Button button : buttons) {
                button.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }
    }

    protected void mouseReleased(int mouseX, int mouseY, int state) {
        dragging = false;

        if (extended) {
            for (Button button : buttons) {
                button.mouseReleased(mouseX, mouseY, state);
            }
        }
    }
}