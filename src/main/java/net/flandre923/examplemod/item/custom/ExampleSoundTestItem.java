package net.flandre923.examplemod.item.custom;

import net.flandre923.examplemod.sounds.ModSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ExampleSoundTestItem extends Item {
    public ExampleSoundTestItem() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(pLevel.isClientSide){
            pLevel.playSound(pPlayer,pPlayer.blockPosition(), ModSounds.EXAMPLE_SOUND.get(), SoundSource.AMBIENT,10f,1f);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
