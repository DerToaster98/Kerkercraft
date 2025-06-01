package de.dertoaster.kerkercraft.common.entity.profile;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.ForgeRegistries;

public record EntityBaseData(
		//MobType mobType,
		List<EntityType<?>> allowedMounts
		// TODO: To be extended => intelligence of mob maybe?
	) {
	
	public static final Codec<EntityBaseData> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group(
				// TODO: Create a mixin that introduces a map that holds the mobtypes by their "id" and then create a proper codec here
				Registries.ENTITY_TYPE.getCodec().listOf().fieldOf("mounts").forGetter(EntityBaseData::allowedMounts)
			).apply(instance, EntityBaseData::new);
	});

}
