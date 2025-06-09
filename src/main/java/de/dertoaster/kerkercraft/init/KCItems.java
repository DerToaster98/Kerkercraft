package de.dertoaster.kerkercraft.init;

import de.dertoaster.kerkercraft.common.services.KCServices;
import de.dertoaster.kerkercraft.world.item.KCItemProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.neoforge.registries.DeferredItem;

public class KCItems {

    // region Daggers
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
    // endregion Spears
}
