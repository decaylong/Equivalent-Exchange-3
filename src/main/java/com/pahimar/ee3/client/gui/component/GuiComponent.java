package com.pahimar.ee3.client.gui.component;

import com.pahimar.ee3.client.gui.GuiBase;
import net.minecraft.util.ResourceLocation;

public abstract class GuiComponent implements Comparable<GuiComponent> {

    protected final GuiBase parentGui;
    protected final String id;
    protected ResourceLocation texture;

    protected int positionX, positionY, componentWidth, componentHeight, textureWidth, textureHeight;
    protected int ordering = 0;
    protected boolean isVisible = true;
    protected boolean isEnabled = true;

    public GuiComponent(GuiBase parentGui, String id, int positionX, int positionY) {
        this.parentGui = parentGui;
        this.id = id;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public final String getId() {
        return id;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public GuiComponent setTexture(ResourceLocation texture) {
        this.texture = texture;
        return this;
    }

    public GuiComponent setTexture(ResourceLocation texture, int textureWidth, int textureHeight) {
        this.texture = texture;
        return this.setTextureSize(textureWidth, textureHeight);
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public GuiComponent setPosition(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        return this;
    }

    public int getComponentWidth() {
        return componentWidth;
    }

    public int getComponentHeight() {
        return componentHeight;
    }

    public GuiComponent setComponentSize(int componentWidth, int componentHeight) {
        this.componentWidth = componentWidth;
        this.componentHeight = componentHeight;
        return this;
    }

    public int getTextureWidth() {
        return textureWidth;
    }

    public int getTextureHeight() {
        return textureHeight;
    }

    public GuiComponent setTextureSize(int textureWidth, int textureHeight) {
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        return this;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public GuiComponent setVisible(boolean isVisible) {

        if (this.isVisible && !isVisible) {
            this.onHide();
        } else if (!this.isVisible && isVisible) {
            this.onShow();
        }

        this.isVisible = isVisible;
        return this;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public GuiComponent setEnabled(boolean isEnabled) {

        if (this.isEnabled && !isEnabled) {
            this.onDisable();
        } else if (!this.isEnabled && isEnabled) {
            this.onEnable();
        }

        this.isEnabled = isEnabled;
        return this;
    }

    public abstract void drawForeground(float partialTicks);

    public abstract void drawBackground(float partialTicks);

    public abstract void onUpdate(int mouseX, int mouseY, float partialTicks);

    /**
     * Checks whether or not the mouse cursor is intersecting with this GuiComponent
     *
     * @param mouseX x position of mouse cursor on the screen
     * @param mouseY y position of mouse cursor on the screen
     * @return true if the mouse cursor is intersecting with this GuiComponent, false otherwise
     */
    public boolean intersectsWithMouse(int mouseX, int mouseY) {
        return (mouseX >= this.positionX && mouseX <= this.positionX + this.componentWidth) && (mouseY >= this.positionY && mouseY <= this.positionY + this.componentHeight);
    }

    public abstract void onMouseButtonClick(int mouseX, int mouseY, int mouseButton);

    public abstract void onMouseButtonDown(int mouseX, int mouseY, int mouseButton);

    public abstract void onMouseButtonUp(int mouseX, int mouseY, int mouseButton);

    public abstract void onMouseButtonRelease(int mouseX, int mouseY, int mouseButton, long duration);

    public abstract void onMouseMove(int prevMouseX, int prevMouseY, int newMouseX, int newMouseY);

    public abstract void onMouseEnter(int mouseX, int mouseY);

    public abstract void onMouseOver(int mouseX, int mouseY);

    public abstract void onMouseLeave(int mouseX, int mouseY);

    public abstract void onKeyDown(int keyPressed);

    public abstract void onKeyUp(int keyPressed);

    public abstract void onKeyPress(char characterTyped, int keyPressed);

    public abstract void onFocusGain();

    public abstract void onFocusLost();

    public abstract void onInit();

    public abstract void onClose();

    public abstract void onHide();

    public abstract void onShow();

    public abstract void onEnable();

    public abstract void onDisable();

    /**
     * Compares this GuiComponent with another one to determine sort order
     *
     * @param guiComponent the GuiComponent we are comparing this one against
     * @return
     */
    @Override
    public int compareTo(GuiComponent guiComponent) {
        if (this.ordering == guiComponent.ordering) {
            if (this.id != null && guiComponent.id != null) {
                return this.id.compareToIgnoreCase(guiComponent.id);
            } else {
                return this.hashCode() - guiComponent.hashCode();
            }
        } else {
            return this.ordering - guiComponent.ordering;
        }
    }
}