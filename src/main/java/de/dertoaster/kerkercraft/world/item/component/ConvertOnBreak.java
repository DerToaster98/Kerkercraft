package de.dertoaster.kerkercraft.world.item.component;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

public record ConvertOnBreak(ItemStack convertInto) {

    public static final Codec<ConvertOnBreak> CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, ConvertOnBreak> STREAM_CODEC;

    public ConvertOnBreak(ItemStack convertInto) {
        this.convertInto = convertInto;
    }

    // Behave just like the normal UseRemainder, but if the stack aint empty, dont create the converted stack!
    public ItemStack convertInto(ItemStack stack, int count, boolean hasInfiniteMaterials) {
        if (hasInfiniteMaterials) {
            return stack;
        } else if (stack.getCount() >= count) {
            return stack;
        } else {
            ItemStack itemstack = this.convertInto.copy();
            if (stack.isEmpty()) {
                return itemstack;
            } else {
                return stack;
            }
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other != null && this.getClass() == other.getClass()) {
            ConvertOnBreak useRemainderKC = (ConvertOnBreak)other;
            return ItemStack.matches(this.convertInto, useRemainderKC.convertInto);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return ItemStack.hashItemAndComponents(this.convertInto);
    }

    public ItemStack convertInto() {
        return this.convertInto;
    }

    static {
        CODEC = ItemStack.CODEC.xmap(ConvertOnBreak::new, ConvertOnBreak::convertInto);
        STREAM_CODEC = StreamCodec.composite(ItemStack.STREAM_CODEC, ConvertOnBreak::convertInto, ConvertOnBreak::new);
    }

    @FunctionalInterface
    public interface OnExtraCreatedRemainder {
        void apply(ItemStack var1);
    }

}
