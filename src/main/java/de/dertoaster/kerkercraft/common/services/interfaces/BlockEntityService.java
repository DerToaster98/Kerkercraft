package de.dertoaster.kerkercraft.common.services.interfaces;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public interface BlockEntityService {

	public <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntityType(String name, BlockEntityType<T> type);
	
}
