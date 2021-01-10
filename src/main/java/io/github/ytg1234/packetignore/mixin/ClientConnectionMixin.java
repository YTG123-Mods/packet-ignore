package io.github.ytg1234.packetignore.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.network.ClientConnection;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.c2s.handshake.HandshakeC2SPacket;
import net.minecraft.network.packet.c2s.login.LoginHelloC2SPacket;
import net.minecraft.network.packet.c2s.login.LoginKeyC2SPacket;
import net.minecraft.network.packet.c2s.login.LoginQueryResponseC2SPacket;
import net.minecraft.network.packet.s2c.login.LoginCompressionS2CPacket;
import net.minecraft.network.packet.s2c.login.LoginDisconnectS2CPacket;
import net.minecraft.network.packet.s2c.login.LoginQueryRequestS2CPacket;
import net.minecraft.network.packet.s2c.login.LoginSuccessS2CPacket;

@Mixin(ClientConnection.class)
public abstract class ClientConnectionMixin {
    @Shadow @Final private NetworkSide side;
    @Unique
    private Random random = new Random();

    @Redirect(method = "channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/Packet;)V",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/network/ClientConnection;handlePacket(Lnet/minecraft/network/Packet;Lnet/minecraft/network/listener/PacketListener;)V"))
    private void stuff(Packet<?> packet, PacketListener listener) {
        if (packet instanceof HandshakeC2SPacket ||
            packet instanceof LoginHelloC2SPacket ||
            packet instanceof LoginKeyC2SPacket ||
            packet instanceof LoginQueryResponseC2SPacket) {
            handlePacket(packet, listener);
            System.out.println("Handshake or login received");
            return;
        }
        if (side == NetworkSide.CLIENTBOUND) {
            handlePacket(packet, listener);
            return;
        }
        if (random.nextBoolean()) handlePacket(packet, listener);
    }

    @Shadow
    private static <T extends PacketListener> void handlePacket(Packet<T> packet, PacketListener listener) {
    }
}
