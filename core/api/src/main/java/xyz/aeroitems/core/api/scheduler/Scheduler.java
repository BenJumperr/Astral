package xyz.aeroitems.core.api.scheduler;

import java.time.Duration;

public interface Scheduler {
    Task runAsync(Runnable task);
    Task runLaterAsync(Runnable task, Duration delay);
    Task runRepeatingAsync(Runnable task, Duration interval);
}
