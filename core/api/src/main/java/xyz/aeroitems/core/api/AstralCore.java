package xyz.aeroitems.core.api;


import xyz.aeroitems.core.api.event.EventBus;
import xyz.aeroitems.core.api.lifecycle.Lifecycle;

public interface AstralCore {
    EventBus eventBus();
    Lifecycle lifecycle();
}
