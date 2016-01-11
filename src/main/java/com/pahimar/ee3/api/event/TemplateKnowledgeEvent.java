package com.pahimar.ee3.api.event;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Event;

public class TemplateKnowledgeEvent extends Event {
    @Override
    public boolean isCancelable() {
        return true;
    }

    public static class TemplateLearnKnowledgeEvent extends TemplateKnowledgeEvent {
        public final ItemStack itemStack;

        public TemplateLearnKnowledgeEvent(ItemStack itemStack) {
            this.itemStack = itemStack;
        }
    }

    public static class TemplateForgetKnowledgeEvent extends TemplateKnowledgeEvent {
        public final ItemStack itemStack;

        public TemplateForgetKnowledgeEvent(ItemStack itemStack) {
            this.itemStack = itemStack;
        }
    }

    public static class TemplateForgetAllKnowledgeEvent extends TemplateKnowledgeEvent {
        public TemplateForgetAllKnowledgeEvent() {
        }
    }
}
