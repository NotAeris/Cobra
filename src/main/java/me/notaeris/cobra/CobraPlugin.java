package me.notaeris.cobra;

import me.notaeris.cobra.command.*;
import me.notaeris.cobra.listener.ChatListener;
import me.notaeris.cobra.listener.PlayerListener;
import me.notaeris.cobra.util.MongoDB;
import me.notaeris.cobra.util.command.CommandFramework;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class CobraPlugin extends JavaPlugin {

    protected MongoDB mongoDB;
    protected CommandFramework framework;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.mongoDB = new MongoDB();
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
                new ReportCommand(),
                new ClearCommand()
        ).forEach(command -> this.framework.registerCommands(command));
    }

    private void registerListeners() {
        Arrays.asList(
                new PlayerListener(),
                new ChatListener()
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }
}
