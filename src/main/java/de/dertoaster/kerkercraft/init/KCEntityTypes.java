package de.dertoaster.kerkercraft.init;

import de.dertoaster.kerkercraft.common.services.KCServices;
import de.dertoaster.kerkercraft.world.entity.projectile.ThrownSpear;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;

public class KCEntityTypes {

    public static final DeferredHolder<EntityType<?>, EntityType<ThrownSpear>> THROWN_SPEAR = KCServices.ENTITY_TYPE.register(
            "thrown_spear",
            EntityType.Builder.<ThrownSpear>of(ThrownSpear::new, MobCategory.MISC)
                    .noLootTable()
                    .sized(0.5F, 0.5F)
                    .eyeHeight(0.13F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
    );

}
