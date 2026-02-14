package xyz.aeroitems.core.common.event;

import xyz.aeroitems.core.api.event.*;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public final class DefaultEventBus implements EventBus {

    private final Map<Class<?>, List<EventHandler>> handlers = new ConcurrentHashMap<>();

    @Override
    public void register(Object listener) {
        for (Method method : listener.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Subscribe.class)) continue;

            Class<?>[] params = method.getParameterTypes();
            if (params.length != 1 || !Event.class.isAssignableFrom(params[0])) continue;

            method.setAccessible(true);
            handlers.computeIfAbsent(params[0], k -> new CopyOnWriteArrayList<>())
                    .add(new EventHandler(listener, method));
        }
    }

    @Override
    public void unregister(Object listener) {
        handlers.values().forEach(list ->
                list.removeIf(handler -> handler.listener() == listener)
        );
    }

    @Override
    public <T extends Event> void post(T event) {
        Class<?> type = event.getClass();
        List<EventHandler> list = handlers.get(type);
        if (list == null) return;

        for (EventHandler handler : list) {
            handler.invoke(event);
        }
    }
}
