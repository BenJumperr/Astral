package xyz.aeroitems.core.common.scheduler;

import xyz.aeroitems.core.api.scheduler.Task;

import java.util.concurrent.Future;

public final class TaskImpl implements Task {

    private final Future<?> future;

    public TaskImpl(Future<?> future) {
        this.future = future;
    }

    @Override
    public void cancel() {
        future.cancel(false);
    }

    @Override
    public boolean isCancelled() {
        return future.isCancelled();
    }
}
