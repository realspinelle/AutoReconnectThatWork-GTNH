package com.realspinelle.autoreconnectthatwork;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.multiplayer.ServerData;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ReconnectTicker {

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent e) {
        if (e.phase != TickEvent.Phase.END) return;
        if (!ReconnectState.pending) return;
        if (System.currentTimeMillis() < ReconnectState.reconnectAtMs) return;
        ReconnectState.pending = false;

        Minecraft mc = Minecraft.getMinecraft();
        if (!(mc.currentScreen instanceof GuiDisconnected)) return;
        mc.loadWorld(null);

        ARTW.LOG.info("Reconnecting now...");

        ServerData data = new ServerData("Reconnecting", ReconnectState.host + ":" + ReconnectState.port);
        FMLClientHandler.instance()
            .connectToServer(new GuiMainMenu(), data);
    }
}
