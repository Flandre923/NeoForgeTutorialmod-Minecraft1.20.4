package net.flandre923.examplemod.level;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

import java.util.Stack;

public class ModLevelSaveData extends SavedData {
    public static final String NAME = "mod_level_save_data";
    private final Stack<ItemStack> itemStacks = new Stack<>();

    // In some class
    private static ModLevelSaveData create() {
        return new ModLevelSaveData();
    }

    public void putItem(ItemStack item) {
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

    @Override
    public CompoundTag save(CompoundTag pCompoundTag) {
        ListTag listTag = new ListTag();
        itemStacks.forEach((stack) -> {
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.put("itemstack", stack.getTag());
            listTag.add(compoundTag);
        });
        pCompoundTag.put("list", listTag);
        return pCompoundTag;
    }

    private ModLevelSaveData load(CompoundTag nbt) {
        ModLevelSaveData data = this.create();
        ListTag listNBT = (ListTag) nbt.get("list");
        if (listNBT != null) {
            for (Tag value : listNBT) {
                CompoundTag tag = (CompoundTag) value;
                ItemStack itemStack = ItemStack.of(tag.getCompound("itemstack"));
                itemStacks.push(itemStack);
            }
        }

        // Load saved data
        return data;
    }

    private static ModLevelSaveData decode(CompoundTag tag){
        ModLevelSaveData modLevelSaveData = ModLevelSaveData.create();
        modLevelSaveData.load(tag);
        return modLevelSaveData;
    }


    public static ModLevelSaveData get(Level worldIn) {
        if (!(worldIn instanceof ServerLevel)) {
            throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
        }
        ServerLevel world = worldIn.getServer().getLevel(ServerLevel.OVERWORLD);
        DimensionDataStorage dataStorage = world.getDataStorage();
        return dataStorage.computeIfAbsent(new Factory<ModLevelSaveData>(ModLevelSaveData::create, ModLevelSaveData::decode), ModLevelSaveData.NAME);
    }
}