package xyz.aeroitems.core.common;

import xyz.aeroitems.core.api.AstralCore;
import xyz.aeroitems.core.api.event.EventBus;
import xyz.aeroitems.core.api.lifecycle.Lifecycle;
import xyz.aeroitems.core.common.event.DefaultEventBus;
import xyz.aeroitems.core.common.lifecycle.LifecycleManager;

public final class AstralCoreImpl implements AstralCore {

    private final EventBus eventBus = new DefaultEventBus();
    private final Lifecycle lifecycle = new LifecycleManager();

    @Override
    public EventBus eventBus() {
        return eventBus;
    }

    @Override
    public Lifecycle lifecycle() {
        return lifecycle;
    }
}
