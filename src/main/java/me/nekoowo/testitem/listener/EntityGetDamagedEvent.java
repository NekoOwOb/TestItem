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
    public void getDamageValue(EntityDamageByEntityEvent e) {
        double damageValue = e.getDamage();
        String name = e.getEntity().getName();
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            p.sendMessage(Utils.chat("You deal " + damageValue + " to " + name));
        }
    }
}
