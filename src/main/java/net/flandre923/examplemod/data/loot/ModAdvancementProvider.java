package net.flandre923.examplemod.data.loot;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.advancement.GiveAppleTrigger;
import net.flandre923.examplemod.advancement.InitTrigger;
import net.flandre923.examplemod.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.TradeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider  {
    public static net.minecraft.data.advancements.AdvancementProvider create(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        return new net.minecraft.data.advancements.AdvancementProvider(packOutput, lookupProvider, List.of(new ModAdvancements()));
    }
    public static class ModAdvancements implements AdvancementSubProvider {
        @Override
        public void generate(HolderLookup.Provider pRegistries, Consumer<AdvancementHolder> pWriter) {
            // Add advancements here
            AdvancementHolder giveAppleAdvancement = Advancement.Builder.advancement()
                    .display(
                            ModItems.RUBY_APPLE.get(),
                            Component.translatable("advancements.adventure.ruby_apple.title"),
                            Component.translatable("advancements.adventure.ruby_apple.description"),
                            new ResourceLocation("textures/gui/advancements/backgrounds/adventure.png"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("use_level_data_block", GiveAppleTrigger.giveAppleBlock())
                    .save(pWriter,new ResourceLocation(ExampleMod.MODID,"main/give_ruby_apple").toString());
        }
    }
}
