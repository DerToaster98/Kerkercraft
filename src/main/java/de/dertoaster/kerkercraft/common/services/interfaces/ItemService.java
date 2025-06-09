package de.dertoaster.kerkercraft.common.services.interfaces;

import de.dertoaster.kerkercraft.world.item.KCItemProperties;
import de.dertoaster.kerkercraft.world.item.armor.KCGeoArmorItem;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.neoforge.registries.DeferredItem;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public interface ItemService {

    public default DeferredItem<Item> registerSimpleItem(String id, Item.Properties properties) {
        return registerItem(id, Item::new, properties);
    }
    public default DeferredItem<Item> registerSimpleItemAsVanilla(String id, Item.Properties properties) {
        return registerAsVanilla(id, Item::new, properties);
    }

    public <T extends Item> DeferredItem<T> registerItem(String id, Function<Item.Properties, T> factory, Item.Properties properties, @Nullable ResourceKey<CreativeModeTab>... tabs);
    public <T extends Item> DeferredItem<T> registerAsVanilla(String id, Function<Item.Properties, Item> factory, Item.Properties properties);


    public default <T extends Item> DeferredItem<T> registerHelmet(String id, ArmorMaterial armorMaterial, Function<Item.Properties, T> factory) {
        return registerArmorPiece(id, armorMaterial, ArmorType.HELMET, factory, Optional.empty());
    }

    public default <T extends Item> DeferredItem<T> registerChestplate(String id, ArmorMaterial armorMaterial, Function<Item.Properties, T> factory) {
        return registerArmorPiece(id, armorMaterial, ArmorType.CHESTPLATE, factory, Optional.empty());
    }

    public default <T extends Item> DeferredItem<T> registerLeggings(String id, ArmorMaterial armorMaterial, Function<Item.Properties, T> factory) {
        return registerArmorPiece(id, armorMaterial, ArmorType.LEGGINGS, factory, Optional.empty());
    }

    public default <T extends Item> DeferredItem<T> registerBoots(String id, ArmorMaterial armorMaterial, Function<Item.Properties, T> factory) {
        return registerArmorPiece(id, armorMaterial, ArmorType.BOOTS, factory, Optional.empty());
    }

    public default <T extends Item> DeferredItem<T> registerHelmet(String id, ArmorMaterial armorMaterial, Function<Item.Properties, T> factory, Consumer<Item.Properties> optPropModifier) {
        return registerArmorPiece(id, armorMaterial, ArmorType.HELMET, factory, Optional.ofNullable(optPropModifier));
    }

    public default <T extends Item> DeferredItem<T> registerChestplate(String id, ArmorMaterial armorMaterial, Function<Item.Properties, T> factory, Consumer<Item.Properties> optPropModifier) {
        return registerArmorPiece(id, armorMaterial, ArmorType.CHESTPLATE, factory, Optional.ofNullable(optPropModifier));
    }

    public default <T extends Item> DeferredItem<T> registerLeggings(String id, ArmorMaterial armorMaterial, Function<Item.Properties, T> factory, Consumer<Item.Properties> optPropModifier) {
        return registerArmorPiece(id, armorMaterial, ArmorType.LEGGINGS, factory, Optional.ofNullable(optPropModifier));
    }

    public default <T extends Item> DeferredItem<T> registerBoots(String id, ArmorMaterial armorMaterial, Function<Item.Properties, T> factory, Consumer<Item.Properties> optPropModifier) {
        return registerArmorPiece(id, armorMaterial, ArmorType.BOOTS, factory, Optional.ofNullable(optPropModifier));
    }

    public default <T extends Item> DeferredItem<T> registerArmorPiece(String id, ArmorMaterial armorMaterial, ArmorType armorType, Function<Item.Properties,T> factory, Optional<Consumer<Item.Properties>> optPropModifier) {
        Item.Properties properties = new KCItemProperties().weightAwareHumanoidArmor(armorMaterial, armorType);

        optPropModifier.ifPresent(c -> c.accept(properties));

        return registerItem(
                id,
                factory,
                properties
        );
    }
}
