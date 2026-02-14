package xyz.aeroitems.core.api.scheduler;

public interface Task {
    void cancel();
    boolean isCancelled();
}
