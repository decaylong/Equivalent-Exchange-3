package com.pahimar.ee3.client.util;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;

public class RenderUtils
{
    private static int pulse = 0;
    private static boolean doInc = true;

    public static void bindTexture(ResourceLocation texture) {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
    }

    public static int getCenteredTextOffset(FontRenderer fontRenderer, String string, int width) {

        return (width - fontRenderer.getStringWidth(string)) / 2;
    }

    public static void renderQuad(ResourceLocation texture)
    {
        // TODO Implement later when we figure out a nice way to render a quad in the world in game
//        FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
//        Tessellator tessellator = Tessellator.getInstance();
//        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
//        GL11.glEnable(GL11.GL_BLEND);
//        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//        GL11.glColor4f(1, 1, 1, 1);
//        tessellator.startDrawingQuads();
//        tessellator.addVertexWithUV(-0.5D, 0.5D, 0F, 0, 1);
//        tessellator.addVertexWithUV(0.5D, 0.5D, 0F, 1, 1);
//        tessellator.addVertexWithUV(0.5D, -0.5D, 0F, 1, 0);
//        tessellator.addVertexWithUV(-0.5D, -0.5D, 0F, 0, 0);
//        tessellator.draw();
//        GL11.glDisable(GL11.GL_BLEND);
//        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    }

    public static void renderPulsingQuad(ResourceLocation texture, float maxTransparency)
    {
        // TODO Implement later when we figure out a nice way to render a quad in the world in game
//        float pulseTransparency = getPulseValue() * maxTransparency / 3000f;
//        FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
//        Tessellator tessellator = Tessellator.instance;
//        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
//        GL11.glEnable(GL11.GL_BLEND);
//        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//        GL11.glColor4f(1, 1, 1, pulseTransparency);
//        tessellator.startDrawingQuads();
//        tessellator.setColorRGBA_F(1, 1, 1, pulseTransparency);
//        tessellator.addVertexWithUV(-0.5D, 0.5D, 0F, 0, 1);
//        tessellator.addVertexWithUV(0.5D, 0.5D, 0F, 1, 1);
//        tessellator.addVertexWithUV(0.5D, -0.5D, 0F, 1, 0);
//        tessellator.addVertexWithUV(-0.5D, -0.5D, 0F, 0, 0);
//        tessellator.draw();
//        GL11.glDisable(GL11.GL_BLEND);
//        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    }

    private static int getPulseValue()
    {
        if (doInc)
        {
            pulse += 50;
        }
        else
        {
            pulse -= 50;
        }
        if (pulse == 3000)
        {
            doInc = false;
        }
        if (pulse == 0)
        {
            doInc = true;
        }
        return pulse;
    }
}
