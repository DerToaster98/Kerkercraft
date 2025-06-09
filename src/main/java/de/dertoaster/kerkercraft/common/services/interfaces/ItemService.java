package de.dertoaster.kerkercraft.common.services.interfaces;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import javax.annotation.Nullable;
import java.util.function.Function;
import java.util.function.Supplier;

public interface ItemService {

    public default DeferredItem<Item> registerSimpleItem(String id, Item.Properties properties) {
        return registerItem(id, Item::new, properties);
    }

    public <T extends Item> DeferredItem<T> registerItem(String id, Function<Item.Properties, T> factory, Item.Properties properties, @Nullable ResourceKey<CreativeModeTab>... tabs);
    public <T extends Item> DeferredItem<T> registerAsVanilla(String id, Function<Item.Properties, Item> factory, Item.Properties properties);

}
