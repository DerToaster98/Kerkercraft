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
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class KCItemProperties extends Item.Properties {

    public Item.Properties dagger(ToolMaterial material, float attackDamage, float attackSpeed) {
        return applyDaggerProperties(material, new Item.Properties(), attackDamage, attackSpeed);
    }

    public Item.Properties spear(ToolMaterial material, float attackDamage, float attackSpeed) {
        return applySpearProperties(material, new Item.Properties(), attackDamage, attackSpeed);
    }

    private static final double ARMOR_TYPE_WEIGHT_MODIFIERS[] = calcModifierMap();
    private static double[] calcModifierMap() {
        double[] result = new double[ArmorType.values().length];
        for (int i = 0; i < ArmorType.values().length; i++) {
            double modifier = switch(ArmorType.values()[i]) {
                case BODY ->  0.33D;
                case HELMET -> 0.21D;
                case LEGGINGS -> 0.29;
                case BOOTS -> 0.17;
                default -> 1.0D;
            };
            result[i] = modifier;
        }
        return result;
    }
    // TODO: Move to config option or datapack!
    private static final Map<ArmorMaterial, Double> DENSITY_MAP = new HashMap<>(Map.of(
            ArmorMaterials.IRON, 7.874D,
            ArmorMaterials.CHAINMAIL, 3.94D,
            ArmorMaterials.GOLD, 19.302,
            ArmorMaterials.DIAMOND, 3.5D,
            ArmorMaterials.NETHERITE, 8.578D,
            ArmorMaterials.LEATHER, 1.4D
    ));

    private static final Function<Double, Double> CALCULATION_FUNCTION = buildCalculationFunction();

    private static Function<Double, Double> buildCalculationFunction() {
        double A = 0, B = 0;

        // TODO: Config value!
        final double modifierLeather = 0.25D;
        final double modifierChainmail = 0.0D;

        final double densityLeather = DENSITY_MAP.getOrDefault(ArmorMaterials.LEATHER, 1.4D);
        final double densityChainmail = DENSITY_MAP.getOrDefault(ArmorMaterials.CHAINMAIL, 3.94D);

        A = (modifierLeather - modifierChainmail) / (densityChainmail - densityLeather);
        B = modifierChainmail - (A * densityChainmail);

        final double fA = A;
        final double fB = B;
        return (x) -> {
            return (fA * x) + fB;
        };
    }

    public Item.Properties KCHumanoidArmor(ArmorMaterial material, ArmorType type) {
        // A full leather set gives +25% movement speed. => 3.94 - 1.4D = 2.54-> +0.25
        // A full chainmal set does not modify movement speed => 3.94 -> 0.0
        // => y = A * x + B
        // 0 = A * 1.4 + B
        // 0.25 = A * 3.94 + B
        //
        // Horizontal modifier (A):
        // Delta: 3.94 - 1.4 = 2.54
        // Over 2.54 units, our vertical delta is 0.25. The normal delta should be 2.54, so that is how we get our scaling!
        // => X modifier = 0.25 / 2.54 = 0.0984251969...
        //
        // Vertical modifier (B):
        // We know that at X = 3.94 (density of chainmal), Y (the speed modifier) should be zero
        // Calculate the value at X = 1.4:
        //   3.94 * 0.0984251969... = 0.3877952758...
        // Known value that we need here: 0
        // Difference: B = 0 - 0.3877952758... = -0.3877952758
        //
        // Since we already calculated our relation to leather and the slot modifier, we can multiply it with that!
        double density = DENSITY_MAP.getOrDefault(material, 1D) / DENSITY_MAP.getOrDefault(ArmorMaterials.LEATHER, 1.4D);
        double slotMultiplier = ARMOR_TYPE_WEIGHT_MODIFIERS[type.ordinal()];
        final double speedModifier = CALCULATION_FUNCTION.apply(density) * slotMultiplier;

        return this
                .durability(type.getDurability(material.durability()))
                .attributes(
                        material.createAttributes(type)
                                .withModifierAdded(
                                        Attributes.MOVEMENT_SPEED,
                                        new AttributeModifier(KCAttributeModifiers.ARMOR_WEIGHT_MOVEMENT_SPEED_MODIFIER, speedModifier, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                                        EquipmentSlotGroup.bySlot(type.getSlot())
                                )
                )
                .enchantable(material.enchantmentValue())
                .component(
                        DataComponents.EQUIPPABLE, Equippable
                                .builder(type.getSlot())
                                    .setEquipSound(material.equipSound())
                                    .setAsset(material.assetId())
                                .build()
                )
                .repairable(material.repairIngredient()
        );
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
