package xyz.aeroitems.core.common.scheduler;

import xyz.aeroitems.core.api.scheduler.AsyncExecutor;

import java.util.concurrent.*;

public class ExecutorPool {

    private static final ExecutorService EXECUTOR = Executors.newVirtualThreadPerTaskExecutor();

    public static AsyncExecutor async() {
        return EXECUTOR::execute;
    }

    public static ScheduledExecutorService scheduler() {
        return Holder.SCHEDULER;
    }

    private static final class Holder {
        private static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(
                Math.max(2, Runtime.getRuntime().availableProcessors() / 2)
        );
    }
}
