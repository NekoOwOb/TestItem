package me.nekoowo.testitem.listener;

import me.nekoowo.testitem.TestItem;
import me.nekoowo.testitem.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityGetDamagedEvent implements Listener {

    TestItem plugin;

    public EntityGetDamagedEvent(TestItem plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void getDamageValue(EntityDamageByEntityEvent even) {
        double damageValue = even.getDamage();
        String name = even.getEntity().getName();
        if (even.getDamager() instanceof Player) {
            Player player = (Player) even.getDamager();
            player.sendMessage(Utils.chat("You deal " + damageValue + " to " + name));
        }
    }
}
