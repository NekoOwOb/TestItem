package me.nekoowo.testitem.listener;

import me.nekoowo.testitem.TestItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    TestItem plugin;

    public JoinEvent(TestItem plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
//        String s = getConfig().getString("ResourcePack");
//        if (s == "true")
//            player.setResourcePack(getConfig().getString("PackLink"));
        player.discoverRecipes(plugin.keys);
    }

}
