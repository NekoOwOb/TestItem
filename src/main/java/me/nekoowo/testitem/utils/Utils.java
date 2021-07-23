package me.nekoowo.testitem.utils;

import me.nekoowo.testitem.TestItem;
import org.bukkit.ChatColor;

public class Utils {

    public static String chat(String s) { return ChatColor.translateAlternateColorCodes('&', s); }

    public static String getItemDName(TestItem plugin, String path) {
        return chat(plugin.getConfig().getString("items." + path + ".display_name"));
    }

    public static double getAttribute(TestItem plugin, String item, String attribute) {
        return plugin.getConfig().getDouble("items." + item + ".attributes." + attribute);
    }
}
