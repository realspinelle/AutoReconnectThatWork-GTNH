package com.realspinelle.autoreconnectthatwork.mixins.early;

import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.IChatComponent;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.realspinelle.autoreconnectthatwork.ReconnectState;

@Mixin(GuiDisconnected.class)
public class MixinGuiDisconnected {

    @Inject(
        method = "<init>(Lnet/minecraft/client/gui/GuiScreen;Ljava/lang/String;Lnet/minecraft/util/IChatComponent;)V",
        at = @At("RETURN"),
        require = 1)
    private void artw$disconnected(GuiScreen parent, String reasonKey, IChatComponent message, CallbackInfo ci) {
        if (!ReconnectState.pending) {
            ReconnectState.pending = true;
            ReconnectState.reconnectAtMs = System.currentTimeMillis() + 10_000L;
        }
    }
}
