package de.dertoaster.kerkercraft.common.entity.profile.variant.extradata;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;

public interface IVariantExtraData<T> {
	
	public T getExtraData();
	public void setExtraData(T data);
	
	public MapCodec<? extends IVariantExtraData<? extends T>> getType();

}
