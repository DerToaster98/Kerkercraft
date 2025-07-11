package de.dertoaster.kerkercraft.common.io;

import java.io.IOException;

public interface IOBiConsumer<K, V> {

	void accept(K k, V v) throws IOException;

}
