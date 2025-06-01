package de.dertoaster.kerkercraft.common.services.interfaces;

import de.dertoaster.kerkercraft.common.network.IMessage;
import de.dertoaster.kerkercraft.common.network.IMessageHandler;
import de.dertoaster.kerkercraft.common.network.NetworkDirection;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import java.util.Optional;


public interface NetworkService {
	
	default public <T extends CustomPacketPayload> void sendToPlayer(T packet, ServerPlayer target) {
		PacketDistributor.sendToPlayer(target, packet);
	}
	
	default public <T extends CustomPacketPayload> void sendToAllPlayers(T packet) {
		PacketDistributor.sendToAllPlayers(packet);
	}

	default public <T extends CustomPacketPayload> void sendToServer(T packet) {
		PacketDistributor.sendToServer(packet);
	}

	default <MSG> void registerClientToServer(final PayloadRegistrar registrar, Class<? extends IMessage<MSG>> clsMessage, Class<? extends IMessageHandler<MSG>> clsHandler) {
		NetworkMethods.registerClientToServer(clsMessage, clsHandler, this);
	}

	default <MSG> void registerServerToClient(final PayloadRegistrar registrar, Class<? extends IMessage<MSG>> clsMessage, Class<? extends IMessageHandler<MSG>> clsHandler) {
		NetworkMethods.registerServerToClient(clsMessage, clsHandler, this);
	}

	default <MSG> void register(final PayloadRegistrar registrar, Class<? extends IMessage<MSG>> clsMessage, Class<? extends IMessageHandler<MSG>> clsHandler) {
		NetworkMethods.register(clsMessage, clsHandler, this);
	}

	default <MSG> void register(final PayloadRegistrar registrar, Class<? extends IMessage<MSG>> clsMessage, Class<? extends IMessageHandler<MSG>> clsHandler, final Optional<NetworkDirection> networkDirection) {
		NetworkMethods.register(clsMessage, clsHandler, networkDirection, this);
	}

	default <MSG> void register(final PayloadRegistrar registrar, IMessage<MSG> message, IMessageHandler<MSG> handler) {
		NetworkMethods.register(message, handler, this);
	}

	default <MSG> void register(final PayloadRegistrar registrar, IMessage<MSG> message, IMessageHandler<MSG> handler, final Optional<NetworkDirection> networkDirection) {
		NetworkMethods.register(message, handler, networkDirection, this);
	}

}
