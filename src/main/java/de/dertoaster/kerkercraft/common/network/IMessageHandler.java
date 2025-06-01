package de.dertoaster.kerkercraft.common.network;

import net.neoforged.neoforge.network.handling.IPayloadContext;


public interface IMessageHandler<T extends Object> {

	public void handlePacket(T packet, IPayloadContext context);

}
