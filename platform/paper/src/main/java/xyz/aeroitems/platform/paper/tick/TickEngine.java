package xyz.aeroitems.platform.paper.tick;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public final class TickEngine {

    private final Plugin plugin;
    private BukkitTask task;

    private final Map<TickPhase, CopyOnWriteArrayList<Runnable>> pipelines =
            new EnumMap<>(TickPhase.class);

    public TickEngine(Plugin plugin) {
        this.plugin = plugin;

        for (TickPhase phase : TickPhase.values()) {
            pipelines.put(phase, new CopyOnWriteArrayList<>());
        }
    }

    public void start() {
        this.task = Bukkit.getScheduler().runTaskTimer(plugin, this::tick, 1L, 1L);
    }

    public void stop() {
        if (task != null) task.cancel();
    }

    public void register(TickPhase phase, Runnable runnable) {
        pipelines.get(phase).add(runnable);
    }

    private void tick() {
        runPhase(TickPhase.PRE);
        runPhase(TickPhase.MAIN);
        runPhase(TickPhase.POST);
    }

    private void runPhase(TickPhase phase) {
        for (Runnable r : pipelines.get(phase)) {
            try {
                r.run();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}
