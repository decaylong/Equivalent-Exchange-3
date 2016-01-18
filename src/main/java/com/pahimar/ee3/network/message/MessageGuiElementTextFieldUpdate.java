package com.pahimar.ee3.network.message;

import com.pahimar.ee3.inventory.element.IElementTextFieldHandler;
import com.pahimar.repackage.cofh.lib.gui.element.ElementTextField;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

// TODO Consolidate Gui related messages into proper inheritance
public class MessageGuiElementTextFieldUpdate implements IMessage {

    public String elementName;
    public String elementText;

    public MessageGuiElementTextFieldUpdate() {
    }

    public MessageGuiElementTextFieldUpdate(ElementTextField elementTextField) {
        this(elementTextField.getName(), elementTextField.getText());
    }

    public MessageGuiElementTextFieldUpdate(String elementName, String elementText) {

        this.elementName = elementName;
        this.elementText = elementText;
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {

        int elementNameLength = byteBuf.readInt();
        this.elementName = new String(byteBuf.readBytes(elementNameLength).array());
        int elementTextLength = byteBuf.readInt();
        this.elementText = new String(byteBuf.readBytes(elementTextLength).array());
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {

        byteBuf.writeInt(elementName.length());
        byteBuf.writeBytes(elementName.getBytes());
        byteBuf.writeInt(elementText.length());
        byteBuf.writeBytes(elementText.getBytes());
    }

    public static class MessageHandler implements IMessageHandler<MessageGuiElementTextFieldUpdate, IMessage> {

        @Override
        public IMessage onMessage(MessageGuiElementTextFieldUpdate message, MessageContext ctx) {

            EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;

            if ((entityPlayer != null) && (entityPlayer.openContainer instanceof IElementTextFieldHandler)) {
                ((IElementTextFieldHandler) entityPlayer.openContainer).handleElementTextFieldUpdate(message.elementName, message.elementText);
            }

            return null;
        }
    }
}
