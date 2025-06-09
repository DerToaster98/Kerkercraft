package de.dertoaster.kerkercraft.common.services.interfaces;

import net.minecraft.core.component.DataComponentType;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.UnaryOperator;

public interface DataComponentService {

    public <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> registerDataComponent(String id, UnaryOperator<DataComponentType.Builder<T>> builder) ;

}
