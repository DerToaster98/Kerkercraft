package de.dertoaster.kerkercraft.common.services;

import de.dertoaster.kerkercraft.Kerkercraft;
import de.dertoaster.kerkercraft.common.services.interfaces.*;

import java.util.ServiceLoader;

public class KCServices {
	
	public static final FactionService FACTION = load(FactionService.class);
	
	public static final BlockService BLOCK = load(BlockService.class);
	public static final ConfigService CONFIG = load(ConfigService.class);
	public static final EntityTypeService ENTITY_TYPE = load(EntityTypeService.class);
	public static final BlockEntityService BLOCK_ENTITY_TYPE = load(BlockEntityService.class);

	public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        Kerkercraft.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
	
}
