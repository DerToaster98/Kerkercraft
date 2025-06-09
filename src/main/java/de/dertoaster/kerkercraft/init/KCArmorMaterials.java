package de.dertoaster.kerkercraft.init;

import com.google.common.collect.Maps;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.*;

import java.util.Map;

public interface KCArmorMaterials extends ArmorMaterials {

    public static final ArmorMaterial HEAVY_IRON = createHeavy(ArmorMaterials.IRON, KCEquipmentAssets.HEAVY_IRON);
    public static final ArmorMaterial HEAVY_DIAMOND = createHeavy(ArmorMaterials.DIAMOND, KCEquipmentAssets.HEAVY_DIAMOND);
    public static final ArmorMaterial HEAVY_NETHERITE = createHeavy(ArmorMaterials.NETHERITE, KCEquipmentAssets.HEAVY_NETHERITE);

    public static final ArmorMaterial TORTOISE = new ArmorMaterial(20, ArmorMaterials.makeDefense(3, 5, 7, 3, 8), 8, SoundEvents.ARMOR_EQUIP_TURTLE, 2.0F, 0.3F, KCItemTags.REPAIRS_TORTOISE_ARMOR, KCEquipmentAssets.TORTOISE);

    public static final ArmorMaterial BULL = new ArmorMaterial(17, ArmorMaterials.makeDefense(2, 4, 8, 4, 7), 10, SoundEvents.ARMOR_EQUIP_NETHERITE, 0.0F, 0.05F, KCItemTags.REPAIRS_BULL_ARMOR, KCEquipmentAssets.BULL);

    // TODO: Create own sound and repair material tag!
    public static final ArmorMaterial SLIME = new ArmorMaterial(15, ArmorMaterials.makeDefense(2, 5, 6, 2, 5), 15, Holder.direct(SoundEvents.SLIME_BLOCK_PLACE), 0.0F, 0.025F, KCItemTags.REPAIRS_SLIME_ARMOR, KCEquipmentAssets.SLIME);

    public static final ArmorMaterial DYABLE_GOLD = createDyable(ArmorMaterials.GOLD, KCEquipmentAssets.DYABLE_GOLD);
    public static final ArmorMaterial DYABLE_IRON = createDyable(ArmorMaterials.IRON, KCEquipmentAssets.DYABLE_IRON);
    public static final ArmorMaterial DYABLE_DIAMOND = createDyable(ArmorMaterials.DIAMOND, KCEquipmentAssets.DYABLE_DIAMOND);
    public static final ArmorMaterial DYABLE_NETHERITE = createDyable(ArmorMaterials.NETHERITE, KCEquipmentAssets.DYABLE_NETHERITE);

    // TODO: Create own sound and repair material tag!
    public static final ArmorMaterial KING_ARMOR = new ArmorMaterial(35, ArmorMaterials.makeDefense(3, 6, 8, 3, 11), 20, SoundEvents.ARMOR_EQUIP_NETHERITE, 4.0F, 0.2F, ItemTags.REPAIRS_DIAMOND_ARMOR, KCEquipmentAssets.KING);

    // TODO: Create own sound and repair material tag!
    public static final ArmorMaterial CLOTH = new ArmorMaterial(4, ArmorMaterials.makeDefense(1, 2, 3, 1, 3), 25, Holder.direct(SoundEvents.WOOL_PLACE), 1.0F, 0.0F, ItemTags.WOOL, KCEquipmentAssets.CLOTH);

    static ArmorMaterial createHeavy(ArmorMaterial baseMaterial, ResourceKey<EquipmentAsset> assetResourceKey) {
        Map<ArmorType, Integer> defenses = Maps.newEnumMap(baseMaterial.defense());
        defenses.entrySet().forEach(entry -> {
            entry.setValue((int) Math.round(entry.getValue() * 1.25D));
        });
        return new ArmorMaterial((int) Math.round(baseMaterial.durability() * 1.5), defenses, baseMaterial.enchantmentValue(), baseMaterial.equipSound(), baseMaterial.toughness() * 1.5F, baseMaterial.knockbackResistance() + 0.1F, baseMaterial.repairIngredient(), assetResourceKey);
    }

    static ArmorMaterial createDyable(ArmorMaterial baseMaterial, ResourceKey<EquipmentAsset> assetResourceKey) {
        Map<ArmorType, Integer> defenses = Maps.newEnumMap(baseMaterial.defense());
        defenses.entrySet().forEach(entry -> {
            entry.setValue((int) Math.round(entry.getValue() * 0.75D));
        });
        return new ArmorMaterial((int) Math.round(baseMaterial.durability() * 0.75D), defenses, baseMaterial.enchantmentValue(), baseMaterial.equipSound(), baseMaterial.toughness() * 0.75F, baseMaterial.knockbackResistance() * 0.75F, baseMaterial.repairIngredient(), assetResourceKey);
    }

}
