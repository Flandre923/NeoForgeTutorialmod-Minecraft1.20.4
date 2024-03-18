package net.flandre923.examplemod.world.structure;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.world.structure.piece.MyStructurePieces;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.structures.IglooPieces;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Locale;
import java.util.function.Supplier;

public class ModStructurePieceTypes {

    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPES = DeferredRegister.create(Registries.STRUCTURE_PIECE, ExampleMod.MODID);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> MY_STRUCTURE_PIECE  = registerPieceType("my_structure_piece", MyStructurePieces.MyStructurePiece::new);
    private static DeferredHolder<StructurePieceType, StructurePieceType> registerPieceType(String name, StructurePieceType.StructureTemplateType structurePieceType) {
        return STRUCTURE_PIECE_TYPES.register(name.toLowerCase(Locale.ROOT), () -> structurePieceType);
    }

    public static void register(IEventBus eventBus){
        STRUCTURE_PIECE_TYPES.register(eventBus);
    }
}
