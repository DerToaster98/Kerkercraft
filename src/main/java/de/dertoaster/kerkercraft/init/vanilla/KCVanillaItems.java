package de.dertoaster.kerkercraft.init.vanilla;

import de.dertoaster.kerkercraft.common.services.KCServices;
import de.dertoaster.kerkercraft.init.KCDataComponents;
import de.dertoaster.kerkercraft.world.item.KCItemProperties;
import de.dertoaster.kerkercraft.world.item.component.ConvertOnBreak;
import de.dertoaster.kerkercraft.world.item.vanilla.MultiUsePotionItem;
import de.dertoaster.kerkercraft.world.item.weapon.melee.SpearItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.neoforge.registries.DeferredItem;

public class KCVanillaItems {

    // TODO: Move durability property to config!
    static final DeferredItem<MultiUsePotionItem> POTION = KCServices.ITEM.registerAsVanilla(
            "potion",
            MultiUsePotionItem::new,
            new Item.Properties()
                    .stacksTo(1)
                    .durability(3)
                    .component(DataComponents.POTION_CONTENTS, PotionContents.EMPTY)
                    .component(DataComponents.CONSUMABLE, Consumables.DEFAULT_DRINK)
                    .component(KCDataComponents.CUSTOM_USE_REMAINDER.get(), new ConvertOnBreak(Items.GLASS_BOTTLE.getDefaultInstance()))
    );

    // TODO: Move stack size to config!
    static final DeferredItem<SplashPotionItem> SPLASH_POTION = KCServices.ITEM.registerAsVanilla(
            "splash_potion",
            SplashPotionItem::new,
            new Item.Properties()
                    .stacksTo(16)
                    .component(DataComponents.POTION_CONTENTS, PotionContents.EMPTY)
    );

    // TODO: Move stack size to config!
    static final DeferredItem<LingeringPotionItem> LINGERING_POTION = KCServices.ITEM.registerAsVanilla(
            "lingering_potion",
            LingeringPotionItem::new,
            new Item.Properties()
                    .stacksTo(8)
                    .component(DataComponents.POTION_CONTENTS, PotionContents.EMPTY)
                    // Also buff them a bit more...
                    .component(DataComponents.POTION_DURATION_SCALE, 0.5F /* Vanilla: 0.25F */)
    );

    static final DeferredItem<TridentItem> TRIDENT = KCServices.ITEM.registerAsVanilla(
            "trident",
            TridentItem::new,
            new Item.Properties()
                    .rarity(Rarity.RARE)
                    .durability(250)
                    // For increased attack range
                    .attributes(SpearItem.createAttributes())
                    .component(DataComponents.TOOL, TridentItem.createToolProperties())
                    .enchantable(1)
                    .component(DataComponents.WEAPON, new Weapon(1))
    );

    // region armor rebalance
    // TODO: Extend to configurable items! Even from mods!
    static final DeferredItem<Item> LEATHER_HELMET = KCServices.ITEM.registerSimpleItemAsVanilla(
            "leather_helmet",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.LEATHER, ArmorType.HELMET)
    );
    static final DeferredItem<Item> LEATHER_CHESTPLATE = KCServices.ITEM.registerSimpleItemAsVanilla(
            "leather_chestplate",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.LEATHER, ArmorType.CHESTPLATE)
    );
    static final DeferredItem<Item> LEATHER_LEGGINGS = KCServices.ITEM.registerSimpleItemAsVanilla(
            "leather_leggings",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.LEATHER, ArmorType.LEGGINGS)
    );
    static final DeferredItem<Item> LEATHER_BOOTS = KCServices.ITEM.registerSimpleItemAsVanilla(
            "leather_boots",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.LEATHER, ArmorType.BOOTS)
    );

    static final DeferredItem<Item> IRON_HELMET = KCServices.ITEM.registerSimpleItemAsVanilla(
            "iron_helmet",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.IRON, ArmorType.HELMET)
    );
    static final DeferredItem<Item> IRON_CHESTPLATE = KCServices.ITEM.registerSimpleItemAsVanilla(
            "iron_chestplate",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.IRON, ArmorType.CHESTPLATE)
    );
    static final DeferredItem<Item> IRON_LEGGINGS = KCServices.ITEM.registerSimpleItemAsVanilla(
            "iron_leggings",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.IRON, ArmorType.LEGGINGS)
    );
    static final DeferredItem<Item> IRON_BOOTS = KCServices.ITEM.registerSimpleItemAsVanilla(
            "iron_boots",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.IRON, ArmorType.BOOTS)
    );

    static final DeferredItem<Item> GOLDEN_HELMET = KCServices.ITEM.registerSimpleItemAsVanilla(
            "golden_helmet",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.GOLD, ArmorType.HELMET)
    );
    static final DeferredItem<Item> GOLDEN_CHESTPLATE = KCServices.ITEM.registerSimpleItemAsVanilla(
            "golden_chestplate",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.GOLD, ArmorType.CHESTPLATE)
    );
    static final DeferredItem<Item> GOLDEN_LEGGINGS = KCServices.ITEM.registerSimpleItemAsVanilla(
            "golden_leggings",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.GOLD, ArmorType.LEGGINGS)
    );
    static final DeferredItem<Item> GOLDEN_BOOTS = KCServices.ITEM.registerSimpleItemAsVanilla(
            "golden_boots",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.GOLD, ArmorType.BOOTS)
    );

    static final DeferredItem<Item> CHAINMAIL_HELMET = KCServices.ITEM.registerSimpleItemAsVanilla(
            "chainmail_helmet",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.CHAINMAIL, ArmorType.HELMET)
    );
    static final DeferredItem<Item> CHAINMAIL_CHESTPLATE = KCServices.ITEM.registerSimpleItemAsVanilla(
            "chainmail_chestplate",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.CHAINMAIL, ArmorType.CHESTPLATE)
    );
    static final DeferredItem<Item> CHAINMAIL_LEGGINGS = KCServices.ITEM.registerSimpleItemAsVanilla(
            "chainmail_leggings",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.CHAINMAIL, ArmorType.LEGGINGS)
    );
    static final DeferredItem<Item> CHAINMAIL_BOOTS = KCServices.ITEM.registerSimpleItemAsVanilla(
            "chainmail_boots",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.CHAINMAIL, ArmorType.BOOTS)
    );

    static final DeferredItem<Item> DIAMOND_HELMET = KCServices.ITEM.registerSimpleItemAsVanilla(
            "diamond_helmet",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.DIAMOND, ArmorType.HELMET)
    );
    static final DeferredItem<Item> DIAMOND_CHESTPLATE = KCServices.ITEM.registerSimpleItemAsVanilla(
            "diamond_chestplate",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.DIAMOND, ArmorType.CHESTPLATE)
    );
    static final DeferredItem<Item> DIAMOND_LEGGINGS = KCServices.ITEM.registerSimpleItemAsVanilla(
            "diamond_leggings",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.DIAMOND, ArmorType.LEGGINGS)
    );
    static final DeferredItem<Item> DIAMOND_BOOTS = KCServices.ITEM.registerSimpleItemAsVanilla(
            "diamond_boots",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.DIAMOND, ArmorType.BOOTS)
    );

    static final DeferredItem<Item> NETHERITE_HELMET = KCServices.ITEM.registerSimpleItemAsVanilla(
            "netherite_helmet",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.NETHERITE, ArmorType.HELMET).fireResistant()
    );
    static final DeferredItem<Item> NETHERITE_CHESTPLATE = KCServices.ITEM.registerSimpleItemAsVanilla(
            "netherite_chestplate",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.NETHERITE, ArmorType.CHESTPLATE).fireResistant()
    );
    static final DeferredItem<Item> NETHERITE_LEGGINGS = KCServices.ITEM.registerSimpleItemAsVanilla(
            "netherite_leggings",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.NETHERITE, ArmorType.LEGGINGS).fireResistant()
    );
    static final DeferredItem<Item> NETHERITE_BOOTS = KCServices.ITEM.registerSimpleItemAsVanilla(
            "netherite_boots",
            new KCItemProperties().weightAwareHumanoidArmor(ArmorMaterials.NETHERITE, ArmorType.BOOTS).fireResistant()
    );
    // endregion armor rebalance
}
