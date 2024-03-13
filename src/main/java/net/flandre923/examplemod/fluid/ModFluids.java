package net.flandre923.examplemod.fluid;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.block.ModBlocks;
import net.flandre923.examplemod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.PoweredRailBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, ExampleMod.MODID);

    public static Supplier<FlowingFluid> MY_SOURCE_FLUID_BLOCK = FLUIDS.register("my_fluid", () -> new BaseFlowingFluid.Source(ModFluids.MY_FLUID_PROPERTIES));
    public static Supplier<FlowingFluid> MY_FLOWING_FLUID_BLOCK = FLUIDS.register("my_fluid_flow", () -> new BaseFlowingFluid.Flowing(ModFluids.MY_FLUID_PROPERTIES));

    private static final BaseFlowingFluid.Properties MY_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(ModFluidType.MY_FLUID_TYPE, ModFluids.MY_SOURCE_FLUID_BLOCK, ModFluids.MY_FLOWING_FLUID_BLOCK).bucket(ModItems.MY_FLUID_BUCKET).slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.MY_FLUID_BLOCK);
    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
