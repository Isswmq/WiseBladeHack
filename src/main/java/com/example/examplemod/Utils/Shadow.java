package com.example.examplemod.Utils;
import org.lwjgl.opengl.GL11;

public class Shadow {

    public static void drawShadow(int x, int y, int height, int width) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 1f);

        GL11.glVertex2d(x,y);
        GL11.glVertex2d(x, y + height);
        GL11.glVertex2d(width, y + height);
        GL11.glVertex2d(width,y);
        GL11.glEnd();

    }
}
