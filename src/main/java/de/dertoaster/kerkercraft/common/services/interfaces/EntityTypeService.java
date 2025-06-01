package de.dertoaster.kerkercraft.common.services.interfaces;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityType.EntityFactory;

import java.util.function.Supplier;

public interface EntityTypeService {

	public <T extends Entity> Supplier<EntityType<T>> registerSized(EntityFactory<T> factory, final String entityName, float width, float height, int updateInterval);

}
