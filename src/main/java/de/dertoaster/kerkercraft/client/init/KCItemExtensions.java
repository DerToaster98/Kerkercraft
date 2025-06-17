package de.dertoaster.kerkercraft.client.init;

import de.dertoaster.kerkercraft.client.item.KCArmorModelExtension;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class KCItemExtensions {

    public static void registerExtensions(BiConsumer<IClientItemExtensions, Item> consumer) {

    }

    private static void registerArmorModel(final Item armor, final ModelLayerLocation modelLayerLocation, final Function<ModelPart, Model> modelConstructor, BiConsumer<IClientItemExtensions, Item> consumer) {
        consumer.accept(new KCArmorModelExtension(modelLayerLocation, modelConstructor), armor);
    }

}
