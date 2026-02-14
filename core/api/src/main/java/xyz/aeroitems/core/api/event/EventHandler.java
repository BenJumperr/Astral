package xyz.aeroitems.core.api.event;

import java.lang.reflect.Method;

public record EventHandler(Object listener, Method method) {

    public void invoke(Event event) {
        try {
            method.invoke(listener, event);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
