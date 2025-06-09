package de.dertoaster.kerkercraft.world.item;

import de.dertoaster.kerkercraft.init.KCAttributeModifiers;
import de.dertoaster.kerkercraft.world.item.weapon.melee.SpearItem;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class KCItemProperties extends Item.Properties {

    public Item.Properties dagger(ToolMaterial material, float attackDamage, float attackSpeed) {
        return applyDaggerProperties(material, new Item.Properties(), attackDamage, attackSpeed);
    }

    public Item.Properties spear(ToolMaterial material, float attackDamage, float attackSpeed) {
        return applySpearProperties(material, new Item.Properties(), attackDamage, attackSpeed);
    }

    private Item.Properties applySpearProperties(ToolMaterial material, Item.Properties properties, float attackDamage, float attackSpeed) {
        HolderGetter<Block> holdergetter = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
        return material.applyCommonProperties(properties)
                .component(
                        DataComponents.TOOL,
                        TridentItem.createToolProperties()
                )
                .attributes(SpearItem.createAttributes(material, attackDamage, attackSpeed))
                .component(DataComponents.WEAPON, new Weapon(1));
    }

    private Item.Properties applyDaggerProperties(ToolMaterial material, Item.Properties properties, float attackDamage, float attackSpeed) {
        HolderGetter<Block> holdergetter = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
        return material.applyCommonProperties(properties)
                .component(
                        DataComponents.TOOL,
                        new Tool(
                                List.of(
                                        Tool.Rule.minesAndDrops(HolderSet.direct(Blocks.COBWEB.builtInRegistryHolder()), 15.0F),
                                        Tool.Rule.overrideSpeed(holdergetter.getOrThrow(BlockTags.SWORD_INSTANTLY_MINES), Float.MAX_VALUE),
                                        Tool.Rule.overrideSpeed(holdergetter.getOrThrow(BlockTags.SWORD_EFFICIENT), 1.5F)
                                ),
                                1.0F,
                                4,
                                false
                        )
                )
                .attributes(this.createDaggerAttributes(material, attackDamage, attackSpeed))
                .component(DataComponents.WEAPON, new Weapon(1));
    }

    private ItemAttributeModifiers createDaggerAttributes(ToolMaterial material, float attackDamage, float attackSpeed) {
        return ItemAttributeModifiers.builder()
                .add(
                        // Less damage
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, (attackDamage + material.attackDamageBonus()) * 0.75D, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        // Make it faster
                        new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, attackSpeed * 0.5D, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ENTITY_INTERACTION_RANGE,
                        new AttributeModifier(KCAttributeModifiers.ATTACK_RANGE_MODIFIER, 0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.MOVEMENT_SPEED,
                        new AttributeModifier(KCAttributeModifiers.DAGGER_MOVEMENT_SPEED_MODIFIER, 2.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

}
