package xyz.aeroitems.core.api.event;

public interface EventBus {
    void register(Object listener);
    void unregister(Object listener);
    <T extends Event> void post(T event);
}
