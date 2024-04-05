package com.bread.mixin;

import com.bread.EasyBedrockBreaker;
import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(ClientCommonNetworkHandler.class)
public class ClientCommonNetworkHandlerMixin {

    @Inject(method = "sendPacket", at = @At("HEAD"), cancellable = true)
    private void delayPackets(Packet<?> packet, CallbackInfo ci) {
        if (EasyBedrockBreaker.isDelayingPackets() && Arrays.stream(EasyBedrockBreaker.blockedPackets).anyMatch(c -> c.isInstance(packet))) {
            EasyBedrockBreaker.delayPacket(packet);
            ci.cancel();
        }
    }

}
