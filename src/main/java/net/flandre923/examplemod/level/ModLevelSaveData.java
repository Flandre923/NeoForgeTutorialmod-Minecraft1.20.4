package net.flandre923.examplemod.level;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

import java.util.ArrayList;
import java.util.Stack;

public class ModLevelSaveData extends SavedData {
    public static final String NAME = "mod_level_save_data";
    private final Stack<ItemStack> itemStacks = new Stack<>();


    // In some class
    private static ModLevelSaveData create() {
        return new ModLevelSaveData();
    }

    public void putItem(ItemStack item) {
        if (item.isEmpty())
            return;
        itemStacks.push(item);
        setDirty();
    }

    public ItemStack getItem() {
        if (itemStacks.isEmpty()) {
            return new ItemStack(Items.AIR);
        }
        setDirty();
        return itemStacks.pop();
    }


    private ModLevelSaveData load(CompoundTag nbt,HolderLookup.Provider pLevelRegistry) {
        ListTag listNBT = (ListTag) nbt.get("list");
        if (listNBT != null) {
            for (Tag value : listNBT) {
                CompoundTag tag = (CompoundTag) value;
                ItemStack itemStack = ItemStack.parse(pLevelRegistry,tag).orElse(ItemStack.EMPTY);
                if (itemStack.equals(ItemStack.EMPTY)){
                    continue;
                }
                itemStacks.push(itemStack);
            }
        }
        // Load saved data
        return this;
    }

    private static ModLevelSaveData decode(CompoundTag tag,HolderLookup.Provider pRegistries){
        return ModLevelSaveData.create().load(tag,pRegistries);
    }


    public static ModLevelSaveData get(Level worldIn) {
        if (!(worldIn instanceof ServerLevel)) {
            throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
        }
        ServerLevel world = worldIn.getServer().getLevel(ServerLevel.OVERWORLD);
        DimensionDataStorage dataStorage = world.getDataStorage();
        return dataStorage.computeIfAbsent(new Factory<ModLevelSaveData>(ModLevelSaveData::create, ModLevelSaveData::decode), ModLevelSaveData.NAME);
    }

    @Override
    public CompoundTag save(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        ListTag listTag = new ListTag();
        itemStacks.forEach((stack) -> {
            CompoundTag compoundTag = new CompoundTag();
            listTag.add(stack.save(pRegistries,compoundTag));
        });
        pTag.put("list", listTag);
        return pTag;
    }
}