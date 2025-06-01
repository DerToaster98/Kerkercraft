package de.dertoaster.kerkercraft.common.entity.profile.variant;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;

public record AttributeEntry(
		Attribute attribute, 
		double value
) {
	public static final Codec<AttributeEntry> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group(
				BuiltInRegistries.ATTRIBUTE.byNameCodec().fieldOf("id").forGetter(AttributeEntry::attribute),
				Codec.DOUBLE.fieldOf("value").forGetter(AttributeEntry::value)
			).apply(instance, AttributeEntry::new);
	});
}