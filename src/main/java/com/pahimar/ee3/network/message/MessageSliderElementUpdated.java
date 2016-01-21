package com.pahimar.ee3.network.message;

import com.pahimar.ee3.inventory.element.IElementSliderHandler;
import com.pahimar.repackage.cofh.lib.gui.element.ElementSlider;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSliderElementUpdated implements IMessage {

    public String elementName;
    public int elementValue;

    public MessageSliderElementUpdated() {
    }

    public MessageSliderElementUpdated(ElementSlider elementSlider) {
        this(elementSlider.getName(), elementSlider.getValue());
    }

    public MessageSliderElementUpdated(String elementName, int elementValue) {

        this.elementName = elementName;
        this.elementValue = elementValue;
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {

        int elementNameLength = byteBuf.readInt();
        this.elementName = new String(byteBuf.readBytes(elementNameLength).array());
        this.elementValue = byteBuf.readInt();
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {

        // TODO This isn't really "safe," rewrite
        if (elementName != null) {

            byteBuf.writeInt(elementName.length());
            byteBuf.writeBytes(elementName.getBytes());
        } else {
            byteBuf.writeInt(0);
        }
        byteBuf.writeInt(elementValue);
    }

    public static class MessageHandler implements IMessageHandler<MessageSliderElementUpdated, IMessage> {

        @Override
        public IMessage onMessage(MessageSliderElementUpdated message, MessageContext ctx) {

            EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;

            if (entityPlayer != null && entityPlayer.openContainer instanceof IElementSliderHandler) {
                ((IElementSliderHandler) entityPlayer.openContainer).handleElementSliderUpdate(message.elementName, message.elementValue);
            }

            return null;
        }
    }
}
