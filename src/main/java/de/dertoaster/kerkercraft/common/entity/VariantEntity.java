package de.dertoaster.kerkercraft.common.entity;

import com.mojang.serialization.DataResult;
import de.dertoaster.kerkercraft.common.KCConstants;
import de.dertoaster.kerkercraft.common.datapack.EntityProfileDatapackRegistries;
import de.dertoaster.kerkercraft.common.entity.profile.EntityProfile;
import de.dertoaster.kerkercraft.common.entity.profile.variant.*;
import de.dertoaster.kerkercraft.common.reference.WeakReferenceLazyLoadField;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.neoforged.bus.EventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityEvent;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;

import java.util.Optional;
import java.util.function.Consumer;

public class VariantEntity extends Monster implements VariantHolder<EntityVariant> {
	
	/*
	 * Variants can change the following things:
	 *  - size
	 *  - damage (weaknesses and resistances)
	 *  - attributes
	 *  - model, texture, animations
	 */
	
	// Forge crap
	@EventBusSubscriber(modid = KCConstants.MODID, bus = EventBusSubscriber.Bus.GAME)
	public static class EventListener {
		
		@SubscribeEvent
		public static void onFinalizeSpawn(FinalizeSpawnEvent event) {
			if (event.getEntity() instanceof VariantEntity ve) {
				EntityType<?> type = ve.getType();
				Optional<EntityProfile> profile = EntityProfileDatapackRegistries.getProfile(type, event.getLevel().registryAccess());
				if (profile.isPresent()) {
					EntityVariant variant = profile.get().getRandomVariant(ve.getRandom());
					if (variant != null) {
						ve.setVariant(variant);
						
						if (variant.hasAssetVariants()) {
							ve.assetEntry = variant.getRandomAssetIndex(ve.getRandom());
						}
						// And now: Attributes
						AttributeMap attributeMap = ve.getAttributes();
						final Consumer<AttributeInstance> onChangeConsumer = (attributeInstance) -> {
							attributeMap.supplier.createInstance(attributeMap::onAttributeModified, (Holder<Attribute>) attributeInstance);
						};

						for (AttributeEntry entry : variant.attributes()) {
							Holder<Attribute> attribute = entry.attribute();
							double value = entry.value();
							
							if (!ve.getAttributes().hasAttribute(attribute)) {
								// Add it lol
								// TODO: Properly implement!
								AttributeInstance attributeInstance = new AttributeInstance(attribute, onChangeConsumer);
								// TODO: Access Widener
								ve.getAttributes().supplier.instances.putIfAbsent(attribute, attributeInstance);
							}
							ve.getAttribute(attribute).setBaseValue(value);
						}
					}
				}
			}
		}
		
		public static void onEntitySize(EntityEvent.Size event) {
			Entity entity = event.getEntity();
			if (entity != null && entity instanceof VariantEntity ve) {
				if (ve.getVariant() == null || ve.getVariant().size() == null) {
					return;
				}
				SizeEntry sizeEntry = ve.getVariant().size();
				switch(event.getPose()) {
				case CROUCHING:
					event.setNewSize(EntityDimensions.scalable(sizeEntry.width(), sizeEntry.height()).scale(ve.getScale()).scale(1F, 0.75F));
					break;
				case SITTING:
					event.setNewSize(EntityDimensions.scalable(sizeEntry.width(), sizeEntry.height()).scale(ve.getScale()).scale(1F, 0.66F));
					break;
				case DIGGING:
				case EMERGING:
					event.setNewSize(EntityDimensions.scalable(sizeEntry.width(), sizeEntry.height()).scale(ve.getScale()).scale(1F, 0.5F));
					break;
				case SLEEPING:
					event.setNewSize(EntityDimensions.scalable(sizeEntry.width(), sizeEntry.height()).scale(ve.getScale()).scale(1F, 0.5F));
					break;
				default:
					event.setNewSize(EntityDimensions.scalable(sizeEntry.width(), sizeEntry.height()).scale(ve.getScale()));
					break;
				}
				
			}
		}
		
	}
	
	private EntityVariant variant;
	private int assetEntry = -1;
	private WeakReferenceLazyLoadField<AssetEntry> assetEntryCache = new WeakReferenceLazyLoadField<>(this::loadAssetEntryLazily);
	
	private AssetEntry loadAssetEntryLazily() {
		if (this.assetEntry < 0) {
			return null;
		}
		if (this.getVariant() == null || !this.getVariant().hasAssetVariants()) {
			return null;
		}
		AssetEntry result = this.getVariant().getAssetAt(this.assetEntry);
		// Out of list
		if (result == null) {
			this.assetEntry = this.getVariant().getRandomAssetIndex(this.getRandom());
			result = this.getVariant().getAssetAt(this.assetEntry);
		}
		return result;
	}

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource pSource, float pAmount) {
		if (this.getVariant() == null) {
			return super.hurtServer(level, pSource, pAmount);
		}
		// Otherwise, we'll need to intercept
		
		DamageEntry damageConfig = this.getVariant().damageConfig();
		// First: damage multipliers
		if (pSource.is(DamageTypeTags.IS_FIRE) && damageConfig.fireImmune()) {
			return false;
		}
		RegistryAccess access = level.registryAccess();
		Float value = damageConfig.damageTypeMultipliers().getOrDefault(access.lookupOrThrow(Registries.DAMAGE_TYPE).getKey(pSource.type()), null);
		if (value != null) {
			pAmount *= value.floatValue();
		}
		
		// Second: min damage
		if (pAmount < damageConfig.minDamage()) {
			return false;
		}
		
		// Third: damage cap
		if (damageConfig.damageCap().isPresent()) {
			DamageCap cap = damageConfig.damageCap().get();
			pAmount = cap.capDamage(pAmount, this::getMaxHealth);
		}
		
		return super.hurtServer(level, pSource, pAmount);
	}
	
	public Optional<AssetEntry> getClientOverrides() {
		if (this.assetEntry >= 0 && this.getVariant() != null && this.assetEntryCache != null) {
			return Optional.ofNullable(this.assetEntryCache.get());
		}
		return Optional.empty();
	}

	public VariantEntity(EntityType<? extends VariantEntity> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag pCompound) {
		super.addAdditionalSaveData(pCompound);
		
		if (this.getVariant() != null) {
			DataResult<Tag> dataResult = EntityVariant.CODEC.encodeStart(NbtOps.INSTANCE, this.getVariant());
			Optional<Tag> optResult = dataResult.result();
			if (optResult.isPresent()) {
				pCompound.put(KCConstants.NBT.KEY_ENTITY_VARIANT, optResult.get());
			} 
		}
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag pCompound) {
		super.readAdditionalSaveData(pCompound);
		
		if (pCompound.contains(KCConstants.NBT.KEY_ENTITY_VARIANT)) {
			Tag nbtTag = pCompound.get(KCConstants.NBT.KEY_ENTITY_VARIANT);
			DataResult<EntityVariant> dataResult = EntityVariant.CODEC.parse(NbtOps.INSTANCE, nbtTag);
			Optional<EntityVariant> optResult = dataResult.result();
			if (!optResult.isEmpty()) {
				this.setVariant(optResult.get());

				// TODO: Implement => Read the actual asset index of that variant!
				if (pCompound.contains(KCConstants.NBT.KEY_ENTITY_VARIANT_ASSETS)) {

				}
			}
		}
	}
	
	@Override
	public void setVariant(EntityVariant pVariant) {
		this.variant = pVariant;
	}

	@Override
	public EntityVariant getVariant() {
		return this.variant;
	}
	
	/*public Optional<HitboxProfile> getHitboxProfile() {
		if (this.getVariant() != null && this.getVariant().getOptHitboxProfile() != null) {
			return this.getVariant().getOptHitboxProfile();
		}
		return Optional.empty();
	}*/
	
}
