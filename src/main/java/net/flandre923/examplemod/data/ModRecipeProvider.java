package net.flandre923.examplemod.data;

import net.flandre923.examplemod.block.ModBlocks;
import net.flandre923.examplemod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    //https://docs.neoforged.net/docs/datagen/server/recipes
    public ModRecipeProvider(PackOutput pPackOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(pPackOutput, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        // 有序合成
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_PICKAXE.get())
                .pattern("aaa")
                .pattern(" b ")
                .pattern(" b ")
                .define('a',ModItems.RUBY.get())
                .define('b', Items.STICK)
                .unlockedBy("has_ruby",has(ModItems.RUBY.get()))
                .save(pRecipeOutput);
        // 无序合成
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MAGIC_INGOT.get())
                .requires(Items.IRON_INGOT,3)
                .unlockedBy("has_iron_ingot",has(Items.IRON_INGOT))
                .save(pRecipeOutput);
        // 冶炼
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.RUBY_ORE.get()),RecipeCategory.MISC,ModItems.RUBY.get(),0.3f,100)
                .unlockedBy("has_ruby_ore",has(ModBlocks.RUBY_ORE.get()))
                .save(pRecipeOutput);
        // 切割石头
//        SingleItemRecipeBuilder.stonecutting()



    }
}
