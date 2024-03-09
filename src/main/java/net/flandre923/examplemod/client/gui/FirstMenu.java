package net.flandre923.examplemod.client.gui;

import net.flandre923.examplemod.block.blockentity.custom.FirstMenuBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class FirstMenu extends AbstractContainerMenu {
    private final ContainerData data;
    public FirstMenu(int pContainerId, Inventory inv, FriendlyByteBuf buf) {
        this(pContainerId,inv,inv.player.level().getBlockEntity(buf.readBlockPos()),new SimpleContainerData(1));
    }
    public FirstMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.FIRST_MENU.get(), pContainerId);
        checkContainerSize(inv,1);
        this.data = data;
        FirstMenuBlockEntity firstMenuBlockEntity = (FirstMenuBlockEntity) entity;
        ItemStackHandler itemHandler = firstMenuBlockEntity.getItemHandler();
        this.addSlot(new SlotItemHandler(itemHandler,0,80,32));
        addDataSlots(data);
        layoutPlayerInventorySlots(inv);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }

    private void layoutPlayerInventorySlots(Inventory playerInventory) {
        // Player inventory
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }

        // Hotbar
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }


    public ContainerData getContainerData(){
        return this.data;
    }

}
