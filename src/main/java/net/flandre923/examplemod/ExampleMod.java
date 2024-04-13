package net.flandre923.examplemod;

import com.mojang.logging.LogUtils;
import net.flandre923.examplemod.advancement.InitTrigger;
import net.flandre923.examplemod.block.ModBlocks;
import net.flandre923.examplemod.block.blockentity.ModBlockEntities;
import net.flandre923.examplemod.client.gui.ModMenuTypes;
import net.flandre923.examplemod.config.Config;
import net.flandre923.examplemod.effect.ModEffects;
import net.flandre923.examplemod.enchantment.ModEnchantments;
import net.flandre923.examplemod.entity.ModEntityTypes;
import net.flandre923.examplemod.fluid.ModFluidType;
import net.flandre923.examplemod.fluid.ModFluids;
import net.flandre923.examplemod.item.ModCreativeTab;
import net.flandre923.examplemod.item.ModItems;
import net.flandre923.examplemod.painting.ModPaintings;
import net.flandre923.examplemod.particle.type.ModParticleType;
import net.flandre923.examplemod.potion.ModPotions;
import net.flandre923.examplemod.sounds.ModSounds;
import net.flandre923.examplemod.villager.ModVillagers;
import net.flandre923.examplemod.world.biome.ModTerrablender;
import net.flandre923.examplemod.world.structure.ModStructurePieceTypes;
import net.flandre923.examplemod.world.structure.ModStructureType;
import net.flandre923.examplemod.world.structure.ModStructures;
import net.flandre923.examplemod.world.surface.ModSurfaceRules;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;
import terrablender.api.SurfaceRuleManager;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleMod.MODID)
public class ExampleMod
{
    public static final String MODID = "examplemod";
    private static final Logger LOGGER = LogUtils.getLogger();
     public ExampleMod(IEventBus modEventBus)
    {
        modEventBus.addListener(this::commonSetup);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeTab.register(modEventBus);
        ModPaintings.register(modEventBus);
        ModVillagers.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModEntityTypes.register(modEventBus);
        ModEnchantments.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModFluidType.register(modEventBus);
        ModFluids.register(modEventBus);
        InitTrigger.register(modEventBus);
        ModSounds.register(modEventBus);
        ModPotions.register(modEventBus);
        ModEffects.register(modEventBus);
        ModParticleType.register(modEventBus);
        ModStructureType.register(modEventBus);
        ModStructurePieceTypes.register(modEventBus);
        ModTerrablender.registerBiome();
        //config
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(()->{
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, ExampleMod.MODID, ModSurfaceRules.makeRules());
        });

        // log config info
        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));

    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }
}
