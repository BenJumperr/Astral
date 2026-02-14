package xyz.aeroitems.core.common.lifecycle;

import xyz.aeroitems.core.api.lifecycle.Lifecycle;
import xyz.aeroitems.core.api.lifecycle.LifecyclePhase;

import java.util.concurrent.atomic.AtomicReference;

public final class LifecycleManager implements Lifecycle {

    private final AtomicReference<LifecyclePhase> phase = new AtomicReference<>(LifecyclePhase.BOOTSTRAP);

    @Override
    public void transition(LifecyclePhase newPhase) {
        phase.set(newPhase);
    }

    @Override
    public LifecyclePhase currentPhase() {
        return phase.get();
    }
}
