package me.notaeris.cobra;

import me.notaeris.cobra.command.*;
import me.notaeris.cobra.listener.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import me.notaeris.cobra.listener.PlayerListener;
import me.notaeris.cobra.util.command.CommandFramework;

import java.util.Arrays;

public class CobraPlugin extends JavaPlugin {

    protected CommandFramework framework;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.framework = new CommandFramework(this);
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
                new MessageCommand(),
                new ReplyCommand(),
                new TogglePmCommand(),
                new SoundsCommand(),
                new HealCommand(),
                new FeedCommand(),
                new ListCommand(),
                new RequestCommand(),
                new ReportCommand()
        ).forEach(command -> this.framework.registerCommands(command));
    }

    private void registerListeners() {
        Arrays.asList(
                new PlayerListener(),
                new ChatListener()
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }
}
