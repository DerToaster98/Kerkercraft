package de.dertoaster.kerkercraft.common.datapack;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.mojang.serialization.Codec;

import de.dertoaster.kerkercraft.common.LazyLoadField;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

public class DatapackRegistry<T> {
		
	protected final ResourceKey<Registry<T>> registryKey;
	protected final Codec<T> objectCodec;
	protected final Codec<Holder<T>> registryCodec;
	
	protected final LazyLoadField<Codec<T>> lazyLoadByNameCodec = new LazyLoadField<>(this::createByNameCodec);
		
	public DatapackRegistry(final ResourceLocation id, final Codec<T> codec) {
		this(ResourceKey.createRegistryKey(id), codec);
	}
	
	public DatapackRegistry(final ResourceKey<Registry<T>> resourceKey, final Codec<T> codec) {
		this(resourceKey, codec, RegistryFileCodec.create(resourceKey, codec));
	}
	
	public DatapackRegistry(final ResourceKey<Registry<T>> resourceKey, final Codec<T> codec, final Codec<Holder<T>> registryCodec) {
		super();
		this.registryCodec = registryCodec;
		this.objectCodec = codec;
		this.registryKey = resourceKey;
	}

	public void registerSynchable(DataPackRegistryEvent.NewRegistry registryEvent) {
		register(registryEvent, true);
	}
	
	public void register(DataPackRegistryEvent.NewRegistry registryEvent) {
		register(registryEvent, false);
	}
	
	public void register(DataPackRegistryEvent.NewRegistry registryEvent, boolean synchable) {
		if (synchable) {
			registryEvent.dataPackRegistry(registryKey, objectCodec, objectCodec);
		} else {
			registryEvent.dataPackRegistry(registryKey, objectCodec);
		}
	}

	// TODO: Rewrite, this is unclean!
	@Nullable
	public T get(ResourceLocation id, RegistryAccess registryAccess) {
		Optional<Registry<T>> optRegistry = this.registry(registryAccess);
		if (optRegistry.isEmpty()) {
			return null;
		}
		return Optional.ofNullable(optRegistry.get().get(id).get()).get().value();
	}
	
	public Optional<Registry<T>> registry(RegistryAccess registryAccess) {
		return registryAccess.lookup(this.registryKey());
	}
	
	public List<T> values(RegistryAccess registryAccess) {
		Optional<Registry<T>> optRegistry = this.registry(registryAccess);
		if (optRegistry.isEmpty()) {
			return Collections.emptyList();
		}
		return optRegistry.get().stream().collect(Collectors.toList());
	}
	
	public ResourceKey<Registry<T>> registryKey() {
		return this.registryKey;
	}
	
	public Codec<T> objectCodec() {
		return this.objectCodec;
	}
	
	public Codec<T> byNameCodec() {
		return this.lazyLoadByNameCodec.get();
	}
	
	public Codec<Holder<T>> registryCodec() {
		return this.registryCodec;
	}
	
	protected Codec<T> createByNameCodec() {
		return createByNameCodec(this);
	}
	
	protected static <V> Codec<V> createByNameCodec(DatapackRegistry<V> registry) {
		return registry.registryCodec().xmap(Holder::value, Holder::direct);
	}
	
}
