package de.dertoaster.kerkercraft.world.item.vanilla;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PotionItem extends net.minecraft.world.item.PotionItem {
    public PotionItem(Properties p_42979_) {
        super(p_42979_);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        // we always want the bar to be visible
        return true;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        return super.finishUsingItem(stack, level, livingEntity);
    }

    // There is custom logic for waterbottles and converting mud, that should stay as it is!


    @Override
    public int getBarColor(ItemStack stack) {
        // Color: Dark blue
        return 0x8195ce;
    }
}
