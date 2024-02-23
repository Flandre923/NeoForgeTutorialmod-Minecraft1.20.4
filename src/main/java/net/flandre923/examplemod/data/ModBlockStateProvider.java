package net.flandre923.examplemod.data;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.block.ModBlocks;
import net.flandre923.examplemod.block.custom.LampBlock;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.simpleBlockWithItem(ModBlocks.RUBY_BLOCK.get(),cubeAll(ModBlocks.RUBY_BLOCK.get()));
        this.simpleBlockWithItem(ModBlocks.RUBY_COUNTER.get(),cubeAll(ModBlocks.RUBY_COUNTER.get()));
        this.simpleBlockWithItem(ModBlocks.HIDDEN_BLOCK.get(),cubeAll(ModBlocks.HIDDEN_BLOCK.get()));
        this.propertyBlock(ModBlocks.LAMP_BLOCK.get());
        this.addWithHaveModel(ModBlocks.RUBY_FRAME.get(),"ruby_frame");
        this.addWithHaveModel(ModBlocks.OBSIDIAN_OBJ.get(),"obsidian_obj");
        this.glassModel(ModBlocks.GLASS_JAR.get(),"glass_jar");
        this.simpleBlockWithItem(ModBlocks.RUBY_ORE.get(),cubeAll(ModBlocks.RUBY_ORE.get()));
    }

    public void glassModel(Block block,String name){
        var model = models().getExistingFile(new ResourceLocation(ExampleMod.MODID,name));
        getVariantBuilder(block).partialState().setModels(new ConfiguredModel(model));
        simpleBlockItem(block,model);
    }
    public void addWithHaveModel(Block block,String name){
        var model_path = models().getExistingFile(new ResourceLocation(ExampleMod.MODID,name));
        var model = new ConfiguredModel(model_path);
        getVariantBuilder(block).partialState().setModels(model);
        simpleBlockItem(block,model_path);
    }
    public void propertyBlock(Block block){
        var block_off = models().cubeAll("lamp_off",new ResourceLocation(ExampleMod.MODID,ModelProvider.BLOCK_FOLDER+"/"+"zircon_lamp_off"));
        var block_on = models().cubeAll("lamp_on",new ResourceLocation(ExampleMod.MODID, ModelProvider.BLOCK_FOLDER+"/"+"zircon_lamp_on"));
        getVariantBuilder(block).partialState().with(LampBlock.LIT,true)
                .modelForState().modelFile(block_on).addModel()
                .partialState().with(LampBlock.LIT,false)
                .modelForState().modelFile(block_off).addModel();
        simpleBlockItem(block,block_off);
    }



}
