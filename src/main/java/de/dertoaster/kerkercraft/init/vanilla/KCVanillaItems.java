package de.dertoaster.kerkercraft.init.vanilla;

import de.dertoaster.kerkercraft.common.services.KCServices;
import de.dertoaster.kerkercraft.init.KCDataComponents;
import de.dertoaster.kerkercraft.world.item.component.ConvertOnBreak;
import de.dertoaster.kerkercraft.world.item.vanilla.PotionItem;
import de.dertoaster.kerkercraft.world.item.weapon.melee.SpearItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.Weapon;
import net.neoforged.neoforge.registries.DeferredItem;

public class KCVanillaItems {

    // TODO: Move durability property to config!
    static final DeferredItem<PotionItem> POTION = KCServices.ITEM.registerAsVanilla(
            "potion",
            PotionItem::new,
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
}
