package de.dertoaster.kerkercraft.common.services.interfaces;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.registries.DeferredHolder;

public interface EntityTypeService {

	public <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(final String id, EntityType.Builder<T> builder);

}
