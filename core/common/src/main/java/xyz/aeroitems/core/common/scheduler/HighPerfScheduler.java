package xyz.aeroitems.core.common.scheduler;

import xyz.aeroitems.core.api.scheduler.Scheduler;
import xyz.aeroitems.core.api.scheduler.Task;

import java.time.Duration;
import java.util.concurrent.*;

public final class HighPerfScheduler implements Scheduler {

    private final ScheduledExecutorService scheduler = ExecutorPool.scheduler();

    @Override
    public Task runAsync(Runnable task) {
        Future<?> future = CompletableFuture.runAsync(task, ExecutorPool.async());
        return new TaskImpl(future);
    }

    @Override
    public Task runLaterAsync(Runnable task, Duration delay) {
        ScheduledFuture<?> future = scheduler.schedule(task,
                delay.toMillis(), TimeUnit.MILLISECONDS);
        return new TaskImpl(future);
    }

    @Override
    public Task runRepeatingAsync(Runnable task, Duration interval) {
        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(task,
                interval.toMillis(), interval.toMillis(), TimeUnit.MILLISECONDS);
        return new TaskImpl(future);
    }
}
