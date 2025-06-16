package de.dertoaster.kerkercraft.world.item;

import de.dertoaster.kerkercraft.init.KCDataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class KCContainerItem extends Item {

    public KCContainerItem(Properties properties) {
        super(properties);
    }

    @Override
    // Never allow backpacks in other containers!
    public boolean canFitInsideContainerItems() {
        return false;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (hand == InteractionHand.OFF_HAND || player.isCrouching()) {
            return InteractionResult.FAIL;
        }

        ItemStack itemStack = player.getItemInHand(hand);

        // If we dont have a chestplate equipped, we equip the backpack
        if (!isWearingBackpack(player)) {
            // TODO: Open container menu and screen!
        } else {
            equipBackpack(player, itemStack.copy());
            player.setItemInHand(hand, ItemStack.EMPTY);
        }

        return InteractionResult.SUCCESS_SERVER;
    }

    public static void equipBackpack(Player player, ItemStack copy) {
        // TODO: Extend to support a future attachment system
        player.setItemSlot(EquipmentSlot.CHEST, copy);
    }

    public static boolean isWearingBackpack(Player player) {
        // TODO: Implement a way to add a backpack as a attachable item to armor!
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        if (chestplate.isEmpty()) {
            return false;
        }
        return chestplate.getComponents().has(KCDataComponents.BACKPACK_CONTAINER);
    }

}
