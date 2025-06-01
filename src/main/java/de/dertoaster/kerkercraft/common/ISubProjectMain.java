package de.dertoaster.kerkercraft.common;

import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.IEventBus;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;


public interface ISubProjectMain {
	
	public List<Consumer<? extends Event>> getEventListenersToReigster();
	public void registerEventHandlerObjects(Consumer<Object> registerMethod);

	// Loading callbacks
	public void onModConstruction(final IEventBus eventBus);
	public void onConfigFolderInit(final Path folderPath);

}
