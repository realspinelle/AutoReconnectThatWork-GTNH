package com.realspinelle.autoreconnectthatwork;

public final class ReconnectState {

    private ReconnectState() {}

    public static volatile boolean pending = false;
    public static volatile long reconnectAtMs = 0L;

    public static volatile String host = "";
    public static volatile int port = 25565;
}
