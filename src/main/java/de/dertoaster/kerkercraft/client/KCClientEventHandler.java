package de.dertoaster.kerkercraft.client;

import de.dertoaster.kerkercraft.client.init.KCItemExtensions;
import de.dertoaster.kerkercraft.common.KCConstants;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

// TODO: Refactor into services
@EventBusSubscriber(modid = KCConstants.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KCClientEventHandler {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public static void onExtensionRegistration(RegisterClientExtensionsEvent event) {
        KCItemExtensions.registerExtensions(event::registerItem);
    }

}
