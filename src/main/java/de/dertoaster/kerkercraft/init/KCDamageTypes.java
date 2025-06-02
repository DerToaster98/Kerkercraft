package de.dertoaster.kerkercraft.init;

import de.dertoaster.kerkercraft.Kerkercraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class KCDamageTypes {

    public static final ResourceKey<DamageType> SPIKES = create("spikes");

    private static ResourceKey<DamageType> create(String id) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, Kerkercraft.prefix(id));
    }

}
