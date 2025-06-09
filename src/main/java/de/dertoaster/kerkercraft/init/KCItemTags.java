package de.dertoaster.kerkercraft.init;

import de.dertoaster.kerkercraft.Kerkercraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class KCItemTags {

    public static final TagKey<Item> REPAIRS_BULL_ARMOR = create("repairs_bull_armor");
    public static final TagKey<Item> REPAIRS_TORTOISE_ARMOR = create("repairs_tortoise_armor");
    public static final TagKey<Item> REPAIRS_SLIME_ARMOR = create("repairs_slime_armor");

    private static TagKey<Item> create(String id) {
        return TagKey.create(Registries.ITEM, Kerkercraft.prefix(id));
    }

}
