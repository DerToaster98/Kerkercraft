package de.dertoaster.kerkercraft.common.datapack;

import java.util.Optional;

import de.dertoaster.kerkercraft.Kerkercraft;
import de.dertoaster.kerkercraft.common.KCConstants;
import de.dertoaster.kerkercraft.common.entity.profile.EntityProfile;
import de.dertoaster.kerkercraft.common.entity.profile.variant.extradata.IVariantExtraData;
import net.commoble.databuddy.codec.RegistryDispatcher;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

@EventBusSubscriber(modid = KCConstants.MODID, bus = EventBusSubscriber.Bus.MOD)
public class EntityProfileDatapackRegistries implements DatapackLoaderHelper {
	
	public static final DatapackRegistry<EntityProfile> ENTITY_PROFILES = new DatapackRegistry<>(Kerkercraft.prefix("entity/profile"), EntityProfile.CODEC);
	public static final RegistryDispatcher<IVariantExtraData<?>> VARIANT_EXTRA_DATA_DISPATCHER = RegistryDispatcher.makeDispatchForgeRegistry(
			Kerkercraft.prefix("registry/dispatcher/variant/extradata"),
			data -> data.getType(),
			builder -> {}
	); 
	
	public static Optional<EntityProfile> getProfile(EntityType<?> entityType, RegistryAccess registryAccess) {
		return getProfile(BuiltInRegistries.ENTITY_TYPE.getKey(entityType), registryAccess);
	}
	
	public static void init() {
		//ENTITY_PROFILES.subscribeAsSyncable(CQRServices.NETWORK.network(), SPacketSyncEntityProfiles::new);
	}
	
	public static Optional<EntityProfile> getProfile(ResourceLocation profileId, RegistryAccess registryAccess) {
		return Optional.ofNullable(ENTITY_PROFILES.get(profileId, registryAccess));
	}
	
	@SubscribeEvent
	public static void register(DataPackRegistryEvent.NewRegistry event) {
		ENTITY_PROFILES.registerSynchable(event);
	}

}
