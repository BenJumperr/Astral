package xyz.aeroitems.platform.paper.entity;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;


public final class EntityControlEngine {

    public void tick() {
        for (var world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                control(entity);
            }
        }
    }

    private void control(Entity entity) {
        // - AI prediction
        // - Dynamic Tick Scaling
        // - Path Throttling
    }
}
