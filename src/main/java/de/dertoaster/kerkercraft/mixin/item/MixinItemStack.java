package de.dertoaster.kerkercraft.mixin.item;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import de.dertoaster.kerkercraft.init.KCDataComponents;
import de.dertoaster.kerkercraft.world.item.component.ConvertOnBreak;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ItemStack.class)
public class MixinItemStack {

    @Inject(
            method = "applyAfterUseComponentSideEffects(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;",
            at = @At(
                    value = "TAIL"
            ),
            remap = false,
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void kc_callCustomUseRemainder(LivingEntity entity, ItemStack stack, CallbackInfoReturnable<ItemStack> cir, @Local(name = "itemstack") LocalRef<ItemStack> itemStackLocal, @Local(name = "i") int stackCount) {
        ConvertOnBreak convertOnBreak = stack.get(KCDataComponents.CUSTOM_USE_REMAINDER.get());
        if (convertOnBreak != null) {
            itemStackLocal.set(convertOnBreak.convertInto((ItemStack)((Object)this), stackCount, entity.hasInfiniteMaterials()));
        }
    }

}
