package me.notaeris.cobra.util;

import org.bukkit.ChatColor;

public class CC {

    /**
     * Translate a string
     *
     * @param string the string
     * @return the translated string
     */
    public static String translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
