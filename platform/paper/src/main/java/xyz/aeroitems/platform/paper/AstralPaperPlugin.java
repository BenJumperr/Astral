package xyz.aeroitems.platform.paper;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.aeroitems.core.common.AstralCoreImpl;
import xyz.aeroitems.platform.paper.bootstrap.PaperBootstrap;

public final class AstralPaperPlugin extends JavaPlugin {

    private AstralCoreImpl core;
    private PaperBootstrap bootstrap;

    @Override
    public void onLoad() {
        this.core = new AstralCoreImpl();
        this.bootstrap = new PaperBootstrap(this, core);
        bootstrap.load();
    }

    @Override
    public void onEnable() {
        bootstrap.enable();
    }

    @Override
    public void onDisable() {
        bootstrap.disable();
    }
}
