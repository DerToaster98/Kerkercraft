package de.dertoaster.kerkercraft.init;

import de.dertoaster.kerkercraft.common.services.KCServices;
import de.dertoaster.kerkercraft.world.item.KCItemProperties;
import de.dertoaster.kerkercraft.world.item.armor.DyableArmor;
import de.dertoaster.kerkercraft.world.item.armor.KCGeoArmorItem;
import de.dertoaster.kerkercraft.world.item.weapon.melee.SpearItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.neoforge.registries.DeferredItem;

public class KCItems {

    // region Daggers
    // Pass it the sword values, it will calculate itself!
    public static DeferredItem<Item> STONE_DAGGER = KCServices.ITEM.registerSimpleItem(
            "stone_dagger",
            new KCItemProperties().dagger(ToolMaterial.STONE, 3.0F, -2.4F)
    );
    public static DeferredItem<Item> GOLDEN_DAGGER = KCServices.ITEM.registerSimpleItem(
            "golden_dagger",
            new KCItemProperties().dagger(ToolMaterial.GOLD, 3.0F, -2.4F)
    );
    public static DeferredItem<Item> IRON_DAGGER = KCServices.ITEM.registerSimpleItem(
            "iron_dagger",
            new KCItemProperties().dagger(ToolMaterial.IRON, 3.0F, -2.4F)
    );
    public static DeferredItem<Item> DIAMOND_DAGGER = KCServices.ITEM.registerSimpleItem(
            "diamond_dagger",
            new KCItemProperties().dagger(ToolMaterial.DIAMOND, 3.0F, -2.4F)
    );
    public static DeferredItem<Item> NETHERITE_DAGGER = KCServices.ITEM.registerSimpleItem(
            "netherite_dagger",
            new KCItemProperties().dagger(ToolMaterial.NETHERITE, 3.0F, -2.4F).fireResistant()
    );
    // endregion Daggers

    // region Spears
    public static DeferredItem<SpearItem> STONE_SPEAR = KCServices.ITEM.registerItem(
            "stone_spear",
            SpearItem::new,
            new KCItemProperties().spear(ToolMaterial.STONE, 2.5F, -2.7F)
    );
    public static DeferredItem<SpearItem> GOLDEN_SPEAR = KCServices.ITEM.registerItem(
            "golden_spear",
            SpearItem::new,
            new KCItemProperties().spear(ToolMaterial.GOLD, 2.5F, -2.7F)
    );
    public static DeferredItem<SpearItem> IRON_SPEAR = KCServices.ITEM.registerItem(
            "iron_spear",
            SpearItem::new,
            new KCItemProperties().spear(ToolMaterial.IRON, 2.5F, -2.7F)
    );
    public static DeferredItem<SpearItem> DIAMOND_SPEAR = KCServices.ITEM.registerItem(
            "diamond_spear",
            SpearItem::new,
            new KCItemProperties().spear(ToolMaterial.DIAMOND, 2.5F, -2.7F)
    );
    public static DeferredItem<SpearItem> NETHERITE_SPEAR = KCServices.ITEM.registerItem(
            "netherite_spear",
            SpearItem::new,
            new KCItemProperties().spear(ToolMaterial.NETHERITE, 2.5F, -2.7F).fireResistant()
    );
    // endregion Spears

    // region Armors
    public static DeferredItem<KCGeoArmorItem> HEAVY_IRON_HELMET = KCServices.ITEM.registerHelmet("heavy_iron_helmet", KCArmorMaterials.HEAVY_IRON, KCGeoArmorItem::new);
    public static DeferredItem<KCGeoArmorItem> HEAVY_IRON_CHESTPLATE = KCServices.ITEM.registerHelmet("heavy_iron_chestplate", KCArmorMaterials.HEAVY_IRON, KCGeoArmorItem::new);
    public static DeferredItem<KCGeoArmorItem> HEAVY_IRON_LEGGINGS = KCServices.ITEM.registerHelmet("heavy_iron_leggings", KCArmorMaterials.HEAVY_IRON, KCGeoArmorItem::new);
    public static DeferredItem<KCGeoArmorItem> HEAVY_IRON_BOOTS = KCServices.ITEM.registerHelmet("heavy_iron_boots", KCArmorMaterials.HEAVY_IRON, KCGeoArmorItem::new);

    public static DeferredItem<KCGeoArmorItem> HEAVY_DIAMOND_HELMET = KCServices.ITEM.registerHelmet("heavy_diamond_helmet", KCArmorMaterials.HEAVY_DIAMOND, KCGeoArmorItem::new);
    public static DeferredItem<KCGeoArmorItem> HEAVY_DIAMOND_CHESTPLATE = KCServices.ITEM.registerHelmet("heavy_diamond_chestplate", KCArmorMaterials.HEAVY_DIAMOND, KCGeoArmorItem::new);
    public static DeferredItem<KCGeoArmorItem> HEAVY_DIAMOND_LEGGINGS = KCServices.ITEM.registerHelmet("heavy_diamond_leggings", KCArmorMaterials.HEAVY_DIAMOND, KCGeoArmorItem::new);
    public static DeferredItem<KCGeoArmorItem> HEAVY_DIAMOND_BOOTS = KCServices.ITEM.registerHelmet("deavy_diamond_boots", KCArmorMaterials.HEAVY_DIAMOND, KCGeoArmorItem::new);

    public static DeferredItem<KCGeoArmorItem> HEAVY_NETHERITE_HELMET = KCServices.ITEM.registerHelmet("heavy_netherite_helmet", KCArmorMaterials.HEAVY_NETHERITE, KCGeoArmorItem::new, c -> c.fireResistant());
    public static DeferredItem<KCGeoArmorItem> HEAVY_NETHERITE_CHESTPLATE = KCServices.ITEM.registerHelmet("heavy_netherite_chestplate", KCArmorMaterials.HEAVY_NETHERITE, KCGeoArmorItem::new, c -> c.fireResistant());
    public static DeferredItem<KCGeoArmorItem> HEAVY_NETHERITE_LEGGINGS = KCServices.ITEM.registerHelmet("heavy_netherite_leggings", KCArmorMaterials.HEAVY_NETHERITE, KCGeoArmorItem::new, c -> c.fireResistant());
    public static DeferredItem<KCGeoArmorItem> HEAVY_NETHERITE_BOOTS = KCServices.ITEM.registerHelmet("heavy_netherite_boots", KCArmorMaterials.HEAVY_NETHERITE, KCGeoArmorItem::new, c -> c.fireResistant());

    // TODO: Proper custom item implementation!
    public static DeferredItem<KCGeoArmorItem> TORTOISE_HELMET = KCServices.ITEM.registerHelmet("tortoise_helmet", KCArmorMaterials.TORTOISE, KCGeoArmorItem::new);
    public static DeferredItem<KCGeoArmorItem> TORTOISE_CHESTPLATE = KCServices.ITEM.registerHelmet("tortoise_chestplate", KCArmorMaterials.TORTOISE, KCGeoArmorItem::new);
    public static DeferredItem<KCGeoArmorItem> TORTOISE_LEGGINGS = KCServices.ITEM.registerHelmet("tortoise_leggings", KCArmorMaterials.TORTOISE, KCGeoArmorItem::new);
    public static DeferredItem<KCGeoArmorItem> TORTOISE_BOOTS = KCServices.ITEM.registerHelmet("tortoise_boots", KCArmorMaterials.TORTOISE, KCGeoArmorItem::new);

    // TODO: Proper custom item implementation!
    public static DeferredItem<KCGeoArmorItem> BULL_HELMET = KCServices.ITEM.registerHelmet("bull_helmet", KCArmorMaterials.BULL, KCGeoArmorItem::new);
    public static DeferredItem<KCGeoArmorItem> BULL_CHESTPLATE = KCServices.ITEM.registerHelmet("bull_chestplate", KCArmorMaterials.BULL, KCGeoArmorItem::new);
    public static DeferredItem<KCGeoArmorItem> BULL_LEGGINGS = KCServices.ITEM.registerHelmet("bull_leggings", KCArmorMaterials.BULL, KCGeoArmorItem::new);
    public static DeferredItem<KCGeoArmorItem> BULL_BOOTS = KCServices.ITEM.registerHelmet("bull_boots", KCArmorMaterials.BULL, KCGeoArmorItem::new);

    // TODO: Proper custom item implementation!
    public static DeferredItem<KCGeoArmorItem> SLIME_HELMET = KCServices.ITEM.registerHelmet("slime_helmet", KCArmorMaterials.SLIME, Item::new);
    public static DeferredItem<KCGeoArmorItem> SLIME_CHESTPLATE = KCServices.ITEM.registerHelmet("slime_chestplate", KCArmorMaterials.SLIME, Item::new);
    public static DeferredItem<KCGeoArmorItem> SLIME_LEGGINGS = KCServices.ITEM.registerHelmet("slime_leggings", KCArmorMaterials.SLIME, Item::new);
    public static DeferredItem<KCGeoArmorItem> SLIME_BOOTS = KCServices.ITEM.registerHelmet("slime_boots", KCArmorMaterials.SLIME, Item::new);

    public static DeferredItem<KCGeoArmorItem> KING_CROWN = KCServices.ITEM.registerHelmet("king_armor_helmet", KCArmorMaterials.KING_ARMOR, KCGeoArmorItem::new);
    public static DeferredItem<KCGeoArmorItem> KING_CHESTPLATE = KCServices.ITEM.registerHelmet("king_armor_chestplate", KCArmorMaterials.KING_ARMOR, KCGeoArmorItem::new);

    public static DeferredItem<KCGeoArmorItem> DYABLE_GOLD_HELMET = KCServices.ITEM.registerHelmet("dyable_gold_helmet", KCArmorMaterials.DYABLE_GOLD, Item::new);
    public static DeferredItem<KCGeoArmorItem> DYABLE_GOLD_CHESTPLATE = KCServices.ITEM.registerHelmet("dyable_gold_chestplate", KCArmorMaterials.DYABLE_GOLD, Item::new);
    public static DeferredItem<KCGeoArmorItem> DYABLE_GOLD_LEGGINGS = KCServices.ITEM.registerHelmet("dyable_gold_leggings", KCArmorMaterials.DYABLE_GOLD, Item::new);
    public static DeferredItem<KCGeoArmorItem> DYABLE_GOLD_BOOTS = KCServices.ITEM.registerHelmet("dyable_gold_boots", KCArmorMaterials.DYABLE_GOLD, Item::new);

    public static DeferredItem<KCGeoArmorItem> DYABLE_IRON_HELMET = KCServices.ITEM.registerHelmet("dyable_iron_helmet", KCArmorMaterials.DYABLE_IRON, Item::new);
    public static DeferredItem<KCGeoArmorItem> DYABLE_IRON_CHESTPLATE = KCServices.ITEM.registerHelmet("dyable_iron_chestplate", KCArmorMaterials.DYABLE_IRON, Item::new);
    public static DeferredItem<KCGeoArmorItem> DYABLE_IRON_LEGGINGS = KCServices.ITEM.registerHelmet("dyable_iron_leggings", KCArmorMaterials.DYABLE_IRON, Item::new);
    public static DeferredItem<KCGeoArmorItem> DYABLE_IRON_BOOTS = KCServices.ITEM.registerHelmet("dyable_iron_boots", KCArmorMaterials.DYABLE_IRON, Item::new);

    public static DeferredItem<KCGeoArmorItem> DYABLE_DIAMOND_HELMET = KCServices.ITEM.registerHelmet("dyable_diamond_helmet", KCArmorMaterials.DYABLE_DIAMOND, Item::new);
    public static DeferredItem<KCGeoArmorItem> DYABLE_DIAMOND_CHESTPLATE = KCServices.ITEM.registerHelmet("dyable_diamond_chestplate", KCArmorMaterials.DYABLE_DIAMOND, Item::new);
    public static DeferredItem<KCGeoArmorItem> DYABLE_DIAMOND_LEGGINGS = KCServices.ITEM.registerHelmet("dyable_diamond_leggings", KCArmorMaterials.DYABLE_DIAMOND, Item::new);
    public static DeferredItem<KCGeoArmorItem> DYABLE_DIAMOND_BOOTS = KCServices.ITEM.registerHelmet("dyable_diamond_boots", KCArmorMaterials.DYABLE_DIAMOND, Item::new);

    public static DeferredItem<KCGeoArmorItem> DYABLE_NETHERITE_HELMET = KCServices.ITEM.registerHelmet("dyable_netherite_helmet", KCArmorMaterials.DYABLE_NETHERITE, Item::new, c -> c.fireResistant());
    public static DeferredItem<KCGeoArmorItem> DYABLE_NETHERITE_CHESTPLATE = KCServices.ITEM.registerHelmet("dyable_netherite_chestplate", KCArmorMaterials.DYABLE_NETHERITE, Item::new, c -> c.fireResistant());
    public static DeferredItem<KCGeoArmorItem> DYABLE_NETHERITE_LEGGINGS = KCServices.ITEM.registerHelmet("dyable_netherite_leggings", KCArmorMaterials.DYABLE_NETHERITE, Item::new, c -> c.fireResistant());
    public static DeferredItem<KCGeoArmorItem> DYABLE_NETHERITE_BOOTS = KCServices.ITEM.registerHelmet("dyable_netherite_boots", KCArmorMaterials.DYABLE_NETHERITE, Item::new, c -> c.fireResistant());

    public static DeferredItem<KCGeoArmorItem> DYABLE_ROBE_HELMET = KCServices.ITEM.registerHelmet("dyable_robe_helmet", KCArmorMaterials.CLOTH, DyableArmor::new);
    public static DeferredItem<KCGeoArmorItem> DYABLE_ROBE_CHESTPLATE = KCServices.ITEM.registerHelmet("dyable_robe_chestplate", KCArmorMaterials.CLOTH, DyableArmor::new);
    public static DeferredItem<KCGeoArmorItem> DYABLE_ROBE_LEGGINGS = KCServices.ITEM.registerHelmet("dyable_robe_leggings", KCArmorMaterials.CLOTH, DyableArmor::new);
    public static DeferredItem<KCGeoArmorItem> DYABLE_ROBE_BOOTS = KCServices.ITEM.registerHelmet("dyable_robe_boots", KCArmorMaterials.CLOTH, DyableArmor::new);
    // endregion Armors
}
