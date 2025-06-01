package de.dertoaster.kerkercraft.common.network;

import de.dertoaster.kerkercraft.common.ClientOnlyMethods;
import net.minecraft.network.protocol.game.ServerPacketListener;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public abstract class AbstractSPacketHandlerCodecWrappingPacket<T extends Object, P extends AbstractSPacketCodecWrappingPacket<T, ?>> implements IMessageHandler<P> {

	public IMessageHandler<P> cast() {
		return (IMessageHandler<P>)this;
	}
	
	@Override
	public void handlePacket(P packet, IPayloadContext context) {
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

	protected abstract void execHandlePacket(P packet, IPayloadContext context, Level world, Player sender);
}
