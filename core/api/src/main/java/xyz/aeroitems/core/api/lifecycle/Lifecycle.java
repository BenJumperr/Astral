package xyz.aeroitems.core.api.lifecycle;

public interface Lifecycle {

    void transition(LifecyclePhase phase);

    LifecyclePhase currentPhase();
}
