package de.dertoaster.kerkercraft.common.entity.profile.variant;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.registries.ForgeRegistries;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public record AttributeEntry(
		Attribute attribute, 
		double value
) {
	public static final Codec<AttributeEntry> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group(
				Registries.ATTRIBUTE.getCodec().fieldOf("id").forGetter(AttributeEntry::attribute),
				Codec.DOUBLE.fieldOf("value").forGetter(AttributeEntry::value)
			).apply(instance, AttributeEntry::new);
	});
}