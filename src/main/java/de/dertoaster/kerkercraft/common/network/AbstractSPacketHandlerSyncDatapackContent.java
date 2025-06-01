package de.dertoaster.kerkercraft.common.network;

import de.dertoaster.kerkercraft.common.ClientOnlyMethods;
import net.minecraft.network.protocol.game.ServerPacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import javax.annotation.Nullable;
import java.util.Map;

public abstract class AbstractSPacketHandlerSyncDatapackContent<C extends Object, P extends AbstractSPacketSyncDatapackContent<C, ?>> implements IMessageHandler<P> {

	public IMessageHandler<P> cast() {
		return (IMessageHandler<P>)this;
	}
	
	@Override
	public final void handlePacket(P packet, IPayloadContext context) {
		context.enqueueWork(() -> {
			Player sender = null;
			Level world = null;
			if(context.connection().getPacketListener() instanceof ServerPacketListener) {
				sender = context.player();
				if(sender != null) {
					world = sender.level();
				}
			}
			//if(context.get().getNetworkManager().getPacketListener() instanceof ClientPacketListener) {
			//Otherwise it must be client?
			else {
				sender = ClientOnlyMethods.getClientPlayer();
				world = ClientOnlyMethods.getWorld();
			}
			
			
			this.execHandlePacket(packet, context, world, sender);
		});
	}
	
	/*
	 * Params:
	 * packet: The packet
	 * context: Network context
	 * world: Optional, set when player is not null or the packet is received clientside, then it is the currently opened world
	 * player: Either the sender of the packet or the local player. Is null for packets recepted during login
	 */
	protected void execHandlePacket(P packet, IPayloadContext context, @Nullable Level world, @Nullable Player player) {
		for (Map.Entry<ResourceLocation, C> entry : packet.getData().entrySet()) {
			packet.consumer().accept(entry.getKey(), entry.getValue());
		}
	}

}
