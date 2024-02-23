package net.flandre923.examplemod.villager;

import com.google.common.collect.ImmutableSet;
import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class ModVillagers {

    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, ExampleMod.MODID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(Registries.VILLAGER_PROFESSION, ExampleMod.MODID);


    public static final Supplier<PoiType> RUBY_BLOCK_POI = POI_TYPES.register("ruby_block_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.RUBY_BLOCK.get().getStateDefinition().getPossibleStates()),
                    1, 1));

    public static final Predicate<Holder<PoiType>> IS_RUBY_BLOCK_POI = poiTypeHolder -> poiTypeHolder.value() == RUBY_BLOCK_POI.get();
    public static final Supplier<VillagerProfession> RUBY_MASTER = VILLAGER_PROFESSIONS.register("ruby_master",
            () -> new VillagerProfession("ruby_master",IS_RUBY_BLOCK_POI,
                    IS_RUBY_BLOCK_POI, ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_ARMORER));

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }

}
