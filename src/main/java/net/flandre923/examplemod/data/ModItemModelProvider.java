package net.flandre923.examplemod.data;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Objects;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.basicItem(ModItems.RUBY.get());
        this.basicItem(ModItems.RUBY_APPLE.get());
        this.basicItem(ModItems.RUBY_SWORD.get());
        this.basicItem(ModItems.RUBY_PICKAXE.get());
        this.basicItem(ModItems.RUBY_HELMET.get());
        this.basicItem(ModItems.RUBY_CHESTPLATE.get());
        this.basicItem(ModItems.RUBY_LEGGINGS.get());
        this.basicItem(ModItems.RUBY_BOOTS.get());
        this.magicIngotModel(getResourceLocation(ModItems.MAGIC_INGOT.get()));
    }


    public ResourceLocation getResourceLocation(Item item){
        return Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item));
    }
    public void magicIngotModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0",new ResourceLocation("item/iron_ingot"))
                .override().predicate(new ResourceLocation(ExampleMod.MODID,"size"),16).model(new ModelFile.UncheckedModelFile("item/gold_ingot")).end();
    }
}
