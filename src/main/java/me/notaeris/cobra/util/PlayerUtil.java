package me.notaeris.cobra.util;

import me.notaeris.cobra.CobraPlugin;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerUtil {

    public static List<Player> getOnlinePlayers() { return new ArrayList<>(CobraPlugin.get().getServer().getOnlinePlayers()); }
}
