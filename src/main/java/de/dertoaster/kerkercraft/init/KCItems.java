package de.dertoaster.kerkercraft.init;

import de.dertoaster.kerkercraft.common.services.KCServices;
import de.dertoaster.kerkercraft.world.item.KCItemProperties;
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
}
