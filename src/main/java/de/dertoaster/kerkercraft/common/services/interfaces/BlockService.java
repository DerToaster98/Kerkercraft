package de.dertoaster.kerkercraft.common.services.interfaces;

import java.util.function.Supplier;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface BlockService {

	public <T extends Block> DeferredBlock<T> registerBlock(String id, Supplier<T> blockSupplier);

}
