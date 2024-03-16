package net.flandre923.examplemod.data;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.data.loot.ModBlockLootProvider;
import net.flandre923.examplemod.data.loot.ModLootTableProvider;
import net.flandre923.examplemod.data.tag.ModBlockTagProvider;
import net.minecraft.data.DataProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber(modid = ExampleMod.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGeneratorHandler {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        ExistingFileHelper efh = event.getExistingFileHelper();
        var lp = event.getLookupProvider();

        // 语言文件
        event.getGenerator().addProvider(
                event.includeClient(),
                (DataProvider.Factory<ModLanguageProvider>) pOutput -> new ModLanguageProvider(pOutput,ExampleMod.MODID,"en_us")
        );
        // 物品模型
        event.getGenerator().addProvider(
                event.includeClient(),
                (DataProvider.Factory<ModItemModelProvider>) pOutput -> new ModItemModelProvider(pOutput,ExampleMod.MODID,efh)
        );
        // 方块state
        event.getGenerator().addProvider(
                event.includeClient(),
                (DataProvider.Factory<ModBlockStateProvider>) pOutput -> new ModBlockStateProvider(pOutput,ExampleMod.MODID,efh)
        );
        // recipe
        event.getGenerator().addProvider(
                event.includeServer(),
                (DataProvider.Factory<ModRecipeProvider>) pOutput -> new ModRecipeProvider(pOutput,lp)
        );
        // loot table
        event.getGenerator().addProvider(
                event.includeServer(),
                (DataProvider.Factory<ModLootTableProvider>)pOutput -> new ModLootTableProvider(pOutput, Collections.emptySet(),
                        List.of(
                                new LootTableProvider.SubProviderEntry(ModBlockLootProvider::new, LootContextParamSets.BLOCK)
                        ))
        );
        // tag
        event.getGenerator().addProvider(
                event.includeServer(),
                (DataProvider.Factory<ModBlockTagProvider>) pOutput -> new ModBlockTagProvider(pOutput,lp,ExampleMod.MODID,efh)
        );
        //world  gen

        event.getGenerator().addProvider(event.includeServer(), (DataProvider.Factory<ModWorldGen>) pOutput -> new ModWorldGen(pOutput,lp));




    }
}
