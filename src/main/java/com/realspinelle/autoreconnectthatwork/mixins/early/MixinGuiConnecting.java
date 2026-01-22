package com.realspinelle.autoreconnectthatwork.mixins.early;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.realspinelle.autoreconnectthatwork.ReconnectState;

@Mixin(GuiConnecting.class)
public class MixinGuiConnecting {

    @Inject(
        method = "<init>(Lnet/minecraft/client/gui/GuiScreen;" + "Lnet/minecraft/client/Minecraft;"
            + "Lnet/minecraft/client/multiplayer/ServerData;)V",
        at = @At("RETURN"),
        require = 1)
    private void artw$storeServer(GuiScreen prev, Minecraft mc, ServerData data, CallbackInfo ci) {
        if (data == null || data.serverIP == null || data.serverIP.isEmpty()) return;
        ServerAddress addr = ServerAddress.func_78860_a(data.serverIP);
        if (addr == null) return;
        ReconnectState.host = addr.getIP();
        ReconnectState.port = addr.getPort();
    }
}
