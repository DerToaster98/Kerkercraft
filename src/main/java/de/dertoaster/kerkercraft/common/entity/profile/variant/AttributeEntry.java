package de.dertoaster.kerkercraft.common.entity.profile.variant;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;

public record AttributeEntry(
		Holder<Attribute> attribute,
		double value
) {
	public static final Codec<AttributeEntry> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group(
				BuiltInRegistries.ATTRIBUTE.holderByNameCodec().fieldOf("id").forGetter(AttributeEntry::attribute),
				Codec.DOUBLE.fieldOf("value").forGetter(AttributeEntry::value)
			).apply(instance, AttributeEntry::new);
	});
}