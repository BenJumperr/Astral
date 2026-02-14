package xyz.aeroitems.core.network.redis;

public interface StreamListener {
    void onMessage(String stream, byte[] payload);
}
