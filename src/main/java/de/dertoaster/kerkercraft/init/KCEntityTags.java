package de.dertoaster.kerkercraft.init;

import de.dertoaster.kerkercraft.Kerkercraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class KCEntityTags {

    public static final TagKey<EntityType<?>> SPIKE_WALK_PENALTY_IMMUNE = create("spike_walk_penalty_immune");
    public static final TagKey<EntityType<?>> SPIKE_DAMAGE_IMMUNE = create("spike_damage_immune");

    private static TagKey<EntityType<?>> create(String id) {
        return TagKey.create(Registries.ENTITY_TYPE, Kerkercraft.prefix(id));
    }

}
