package com.pahimar.ee3.network.message;

import com.google.gson.stream.JsonReader;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.EnergyValueStackMapping;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.util.CompressionHelper;
import com.pahimar.ee3.util.LogHelper;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.TreeMap;

public class MessageSyncEnergyValues implements IMessage {

    public String jsonEnergyValueRegistry;

    public MessageSyncEnergyValues() {
    }

    public MessageSyncEnergyValues(EnergyValueRegistry energyValueRegistry) {
        this.jsonEnergyValueRegistry = energyValueRegistry.toJson();
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {

        byte[] compressedBytes = null;
        int readableBytes = byteBuf.readInt();

        if (readableBytes > 0) {
            compressedBytes = byteBuf.readBytes(readableBytes).array();
        }

        if (compressedBytes != null) {
            this.jsonEnergyValueRegistry = CompressionHelper.decompressStringFromByteArray(compressedBytes);
        }
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {

        byte[] compressedBytes = null;

        if (jsonEnergyValueRegistry != null) {
            compressedBytes = CompressionHelper.compressStringToByteArray(jsonEnergyValueRegistry);
        }

        if (compressedBytes != null) {
            byteBuf.writeInt(compressedBytes.length);
            byteBuf.writeBytes(compressedBytes);
        } else {
            byteBuf.writeInt(0);
        }
    }

    public static class MessageHandler implements IMessageHandler<MessageSyncEnergyValues, IMessage> {

        @Override
        public IMessage onMessage(MessageSyncEnergyValues message, MessageContext ctx) {

            if (message.jsonEnergyValueRegistry != null) {
                Map<WrappedStack, EnergyValue> energyValueStackMap = new TreeMap<WrappedStack, EnergyValue>();

                try {
                    JsonReader jsonReader = new JsonReader(new StringReader(message.jsonEnergyValueRegistry));
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        EnergyValueStackMapping energyValueStackMapping = EnergyValueStackMapping.jsonSerializer.fromJson(jsonReader, EnergyValueStackMapping.class);
                        if (energyValueStackMapping != null) {
                            energyValueStackMap.put(energyValueStackMapping.wrappedStack, energyValueStackMapping.energyValue);
                        }
                    }
                    jsonReader.endArray();
                    jsonReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                EnergyValueRegistry.getInstance().loadFromMap(energyValueStackMap);
                LogHelper.info(EnergyValueRegistry.ENERGY_VALUE_MARKER, "Client successfully received EnergyValues from server");
            } else {
                LogHelper.info(EnergyValueRegistry.ENERGY_VALUE_MARKER, "Client failed to receive EnergyValues from server - falling back to local EnergyValues");
            }

            return null;
        }
    }
}
