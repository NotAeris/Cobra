package me.notaeris.cobra.util;

import org.bukkit.ChatColor;

public class CC {

    /**
     * translates a string
     *
     * @param string to be translated
     * @return the translated string
     */
    public static String translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
