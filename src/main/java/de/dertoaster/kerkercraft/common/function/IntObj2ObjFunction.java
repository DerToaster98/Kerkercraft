package de.dertoaster.kerkercraft.common.function;

@FunctionalInterface
public interface IntObj2ObjFunction<T, R> {

	R apply(int x, T t);

}
