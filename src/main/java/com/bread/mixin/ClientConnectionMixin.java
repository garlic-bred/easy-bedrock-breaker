package com.bread.mixin;

import com.bread.EasyBedrockBreaker;
import net.minecraft.network.ClientConnection;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {

    @Inject(method = "disconnect", at = @At("HEAD"))
    private void clearPackets(Text disconnectReason, CallbackInfo ci) {
        EasyBedrockBreaker.clearPackets();
    }

}
