package me.notaeris.cobra;

import me.notaeris.cobra.command.*;
import me.notaeris.cobra.listener.ChatListener;
import me.notaeris.cobra.listener.FreezeListener;
import me.notaeris.cobra.listener.PlayerListener;
import me.notaeris.cobra.listener.StaffModeListener;
import me.notaeris.cobra.util.Cooldown;
import me.notaeris.cobra.util.command.CommandFramework;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class CobraPlugin extends JavaPlugin {

    private CommandFramework framework;
    private CobraAPI api;
    private Cooldown cooldown;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        this.framework = new CommandFramework(this);
        this.api = new CobraAPI();
        this.cooldown = new Cooldown(null, null, 0);
        this.registerCommands();
        this.registerListeners();
    }

    public static CobraPlugin get() { return getPlugin(CobraPlugin.class); }

    private void registerCommands() {
        Arrays.asList(
                new SocialCommands(),
                new GamemodeCommand(),
                new ChatCommands(),
                new TeleportCommands(),
                new FlyCommand(),
                new TimeCommands(),
                new VanishCommand(),
                new StaffChatCommand(),
                new MessageCommands(),
                new TogglePmCommand(),
                new SoundsCommand(),
                new HealCommand(),
                new FeedCommand(),
                new ListCommand(),
                new RequestCommand(),
                new ReportCommand(),
                new ClearCommand(),
                new StaffModeCommand(),
                new InvseeCommand(),
                new FreezeCommand(),
                new SpawnCommands()
        ).forEach(command -> this.framework.registerCommands(command));
    }

    private void registerListeners() {
        Arrays.asList(
                new PlayerListener(),
                new ChatListener(),
                new StaffModeListener(),
                new FreezeListener()
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    public CobraAPI getAPI() {
        return this.api;
    }
}
