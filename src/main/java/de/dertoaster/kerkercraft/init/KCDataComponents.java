package de.dertoaster.kerkercraft.init;

import de.dertoaster.kerkercraft.common.services.KCServices;
import de.dertoaster.kerkercraft.world.item.component.BackpackContent;
import de.dertoaster.kerkercraft.world.item.component.ConvertOnBreak;
import net.minecraft.core.component.DataComponentType;

import java.util.function.Supplier;

public class KCDataComponents {

    public static final Supplier<DataComponentType<ConvertOnBreak>> CUSTOM_USE_REMAINDER = KCServices.DATA_COMPONENT.registerDataComponent(
            "custom_use_remainder",
            builder -> builder
                    .persistent(ConvertOnBreak.CODEC)
                    .networkSynchronized(ConvertOnBreak.STREAM_CODEC)
                    .cacheEncoding()
    );

    public static final Supplier<DataComponentType<BackpackContent>> BACKPACK_CONTAINER = KCServices.DATA_COMPONENT.registerDataComponent(
            "backpack_content",
            builder -> builder
                    .persistent(BackpackContent.CODEC)
                    .networkSynchronized(BackpackContent.STREAM_CODEC)
                    .cacheEncoding()
    );

}
