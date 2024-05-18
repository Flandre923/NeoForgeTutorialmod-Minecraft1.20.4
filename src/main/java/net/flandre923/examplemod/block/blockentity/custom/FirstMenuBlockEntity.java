package net.flandre923.examplemod.block.blockentity.custom;

import net.flandre923.examplemod.ExampleMod;
import net.flandre923.examplemod.block.blockentity.ModBlockEntities;
import net.flandre923.examplemod.client.gui.FirstMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class FirstMenuBlockEntity extends BlockEntity implements MenuProvider {
    int i = 0;
    protected final ContainerData data;
    private final ItemStackHandler itemHandler = new ItemStackHandler(1);
    public FirstMenuBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.FIRST_MENU_BLOCK_ENTITY.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                if (pIndex==0){
                    return i;
                }
                return 0;
            }

            @Override
            public void set(int pIndex, int pValue) {
                if(pIndex==0){
                    FirstMenuBlockEntity.this.i = pValue;
                }
            }

            @Override
            public int getCount() {
                return 1;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("gui."+ExampleMod.MODID+".first_menu_gui");
    }
    // 创建了我们的Container
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new FirstMenu(pContainerId,pPlayerInventory,this,this.data);
    }


    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        CompoundTag inventory = pTag.getCompound("inventory");
        this.itemHandler.deserializeNBT(pRegistries,inventory);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        pTag.put("inventory",this.itemHandler.serializeNBT(pRegistries));
    }

    public ItemStackHandler getItemHandler(){
        return this.itemHandler;
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, FirstMenuBlockEntity pBlockEntity) {
        if(pLevel!=null && !pLevel.isClientSide){
            pBlockEntity.data.set(0,pBlockEntity.itemHandler.getStackInSlot(0).getCount());
        }
    }
}
