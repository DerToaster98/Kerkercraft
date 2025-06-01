package de.dertoaster.kerkercraft.common.entity.profile;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.dertoaster.kerkercraft.common.LazyLoadField;
import de.dertoaster.kerkercraft.common.entity.profile.variant.EntityVariant;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EntityProfile {
	
	protected final EntityBaseData baseData;
	protected final EntityVariant defaultData;
	protected final Map<String, EntityVariant> variants;
	
	private final LazyLoadField<WeightedList<EntityVariant>> variantWeightedList = new LazyLoadField<>(this::generateWeightedVariantList);
	
	public EntityProfile(EntityBaseData baseData, EntityVariant defaultData, Map<String, EntityVariant> variants) {
		this.baseData = baseData;
		this.defaultData = defaultData;
		this.variants = variants;
	}
	
	private final WeightedList<EntityVariant> generateWeightedVariantList() {
		List<Weighted<EntityVariant>> weightedList = new ArrayList(this.variants.values().size() + 1);
		for(EntityVariant variant : this.variants.values()) {
			weightedList.add(new Weighted<>(variant, variant.weight()));
		}
		weightedList.add(new Weighted<>(this.defaultData, this.defaultData.weight()));
		
		return WeightedList.of(weightedList);
	}
	
	public static final Codec<EntityProfile> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group(
				EntityBaseData.CODEC.fieldOf("base").forGetter(profile -> {return profile.baseData;}),
				EntityVariant.CODEC.fieldOf("default").forGetter(profile -> {return profile.defaultData;}),
				Codec.unboundedMap(Codec.STRING, EntityVariant.CODEC).optionalFieldOf("variants", Collections.emptyMap()).forGetter(profile -> {return profile.variants;})
			).apply(instance, EntityProfile::new);
	});

	static final RandomSource RANDOM_SOURCE = RandomSource.create();

	@Nullable
	public EntityVariant getRandomVariant() {
		return this.variantWeightedList.get().getRandom(RANDOM_SOURCE).get();
	}
	
	@Nullable
	public EntityVariant getRandomVariant(final RandomSource random) {
		return this.variantWeightedList.get().getRandom(random).get();
	}
	
	public EntityBaseData baseData() {
		return this.baseData;
	}
	
	public EntityVariant defaultData() {
		return this.defaultData;
	}
	
	public EntityVariant getByName(String name) {
		if (name != null && this.variants.containsKey(name)) {
			return this.variants.getOrDefault(name, this.defaultData);
		}
		return this.defaultData;
	}

}
