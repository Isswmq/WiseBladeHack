package com.example.examplemod.Utils.template;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.IngameGui;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class RenderUtils {
    public static void drawRoundedRect(MatrixStack m, double x, double y, int width, int height, int radius, Color color) {

        int x2 = (int) (x + ((radius / 2F) + 0.5F));
        int y2 = (int) (y + ((radius / 2F) + 0.5F));
        int width2 = (int) (width - ((radius / 2F) + 0.5F));
        int height2 = (int) (height - ((radius / 2F) + 0.5F));

        IngameGui.fill(m, x2, y2, x2 + width2, y2 + height2, color.getRGB());

        polygon(x, y, radius * 2, 360, true, color);
        polygon(x + width2 - radius + 1.2 - 1, y, radius * 2, 360, true, color);

        polygon(x + width2 - radius + 1.2 - 1, y + height2 - radius + 1 - 1, radius * 2, 360, true, color);
        polygon(x, y + height2 - radius + 1 - 1, radius * 2, 360, true, color);

        GL11.glColor4f(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, color.getAlpha() / 255F);
        IngameGui.fill(m, (int) (x2 - radius / 2 - 0.5F), y2 + radius / 2, x2 + width2, y2 + height2 - radius / 2, color.getRGB());
        IngameGui.fill(m,x2, y2 + radius / 2, (int) (x2 + width2 + radius / 2 + 0.5f), y2 + height2 - radius / 2, color.getRGB());
        IngameGui.fill(m,x2 + radius / 2, (int) (y2 - radius / 2 - 0.5F), x2 + width2 - radius / 2, (int) (y + height2 - radius / 2), color.getRGB());
        IngameGui.fill(m,x2 + radius / 2, y2, x2 + width2 - radius / 2, (int) (y2 + height2 + radius / 2 + 0.5f), color.getRGB());
    }

    public static void polygon(double x, double y, double sideLength, double amountOfSides, boolean filled, Color color) {
        sideLength /= 2;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GlStateManager._disableAlphaTest();
        GL11.glColor4f(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, color.getAlpha() / 255F);
        if (!filled) {
            GL11.glLineWidth(1);
        }
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBegin(filled ? GL11.GL_TRIANGLE_FAN : GL11.GL_LINE_STRIP);

        for (double i = 0; i <= amountOfSides; i++) {
            double angle = i * (Math.PI * 2) / amountOfSides;
            GL11.glVertex2d(x + (sideLength * Math.cos(angle)) + sideLength, y + (sideLength * Math.sin(angle)) + sideLength);
        }

        GL11.glColor4f(1, 1, 1, 1);
        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GlStateManager._enableAlphaTest();
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }
}
