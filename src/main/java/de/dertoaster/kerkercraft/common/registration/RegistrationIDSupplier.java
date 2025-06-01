package de.dertoaster.kerkercraft.common.registration;

import javax.annotation.Nonnull;

import net.minecraft.resources.ResourceLocation;

public interface RegistrationIDSupplier {

	@Nonnull
	public ResourceLocation getId();
	
	public void setId(@Nonnull final ResourceLocation id);
	
}
