package de.dertoaster.kerkercraft.service.implementation;

import de.dertoaster.kerkercraft.common.KCConstants;
import de.dertoaster.kerkercraft.common.services.interfaces.ItemService;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class NeoItemService implements ItemService {

    private static final DeferredRegister.Items KC_ITEMS = DeferredRegister.createItems(KCConstants.MODID);
    private static final DeferredRegister.Items MINECRAFT_ITEMS = DeferredRegister.createItems("minecraft");

    @Override
    public <T extends Item> DeferredItem<T> registerItem(String id, Function<Item.Properties, T> factory, Item.Properties properties, @Nullable ResourceKey<CreativeModeTab>... tabs) {
        return registerInternal(KC_ITEMS, id, factory, properties, tabs);
    }

    @Override
    public <T extends Item> DeferredItem<T> registerAsVanilla(String id, Function<Item.Properties, T> factory, Item.Properties properties) {
        return registerInternal(MINECRAFT_ITEMS, id, factory, properties);
    }

    private <T extends Item> DeferredItem<T> registerInternal(DeferredRegister.Items register, String id, Function<Item.Properties, T> factory, Item.Properties properties, @Nullable ResourceKey<CreativeModeTab>... tabs) {
        return register.registerItem(
                id,
                factory,
                properties
        );
        // TODO: Support already setting tabs!
    }
}
