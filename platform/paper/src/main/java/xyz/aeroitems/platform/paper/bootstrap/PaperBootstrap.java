package xyz.aeroitems.platform.paper.bootstrap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import xyz.aeroitems.core.api.lifecycle.LifecyclePhase;
import xyz.aeroitems.core.common.AstralCoreImpl;
import xyz.aeroitems.platform.paper.chunk.ChunkPipeline;
import xyz.aeroitems.platform.paper.entity.EntityControlEngine;
import xyz.aeroitems.platform.paper.tick.TickEngine;

public final class PaperBootstrap {

    private final Plugin plugin;
    private final AstralCoreImpl core;

    private TickEngine tickEngine;
    private EntityControlEngine entityEngine;
    private ChunkPipeline chunkPipeline;

    public PaperBootstrap(Plugin plugin, AstralCoreImpl core) {
        this.plugin = plugin;
        this.core = core;
    }

    public void load() {
        core.lifecycle().transition(LifecyclePhase.LOAD);
    }

    public void enable() {
        core.lifecycle().transition(LifecyclePhase.ENABLE);

        this.tickEngine = new TickEngine(plugin);
        this.entityEngine = new EntityControlEngine();
        this.chunkPipeline = new ChunkPipeline();

        tickEngine.start();

        Bukkit.getLogger().info("[Astral] Paper platform started.");
    }

    public void disable() {
        core.lifecycle().transition(LifecyclePhase.DISABLE);

        if (tickEngine != null) tickEngine.stop();

        core.lifecycle().transition(LifecyclePhase.SHUTDOWN);
    }
}
