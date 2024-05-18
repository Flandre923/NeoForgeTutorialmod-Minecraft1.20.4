package net.flandre923.examplemod.world.structure.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.flandre923.examplemod.world.structure.ModStructureType;
import net.flandre923.examplemod.world.structure.piece.MyStructurePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.structures.IglooPieces;

import java.util.Optional;

public class MyStructure extends Structure {

    public static final MapCodec<MyStructure> CODEC = simpleCodec(MyStructure::new);

    public MyStructure(StructureSettings pSettings) {
        super(pSettings);
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext pContext) {
        return onTopOfChunkCenter(pContext, Heightmap.Types.WORLD_SURFACE_WG, structurePiecesBuilder -> this.generatePieces(structurePiecesBuilder, pContext));
    }

    private void generatePieces(StructurePiecesBuilder pBuilder, Structure.GenerationContext pContext) {
        ChunkPos chunkpos = pContext.chunkPos();
        WorldgenRandom worldgenrandom = pContext.random();
        BlockPos blockpos = new BlockPos(chunkpos.getMinBlockX(), 64, chunkpos.getMinBlockZ());
        Rotation rotation = Rotation.getRandom(worldgenrandom);
        MyStructurePieces.addPieces(pContext.structureTemplateManager(), blockpos, rotation, pBuilder, worldgenrandom);
    }

    @Override
    public StructureType<?> type() {
        return ModStructureType.MY_STRUCTURE.get();
    }
}
