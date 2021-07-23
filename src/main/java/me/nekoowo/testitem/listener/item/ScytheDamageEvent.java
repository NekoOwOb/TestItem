package me.nekoowo.testitem.listener.item;

import me.nekoowo.testitem.TestItem;
import me.nekoowo.testitem.item.scythe.Scythe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ScytheDamageEvent implements Listener {

    TestItem plugin;

    public ScytheDamageEvent(TestItem plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void ScytheAttack(EntityDamageByEntityEvent e) {
        Player p;
        LivingEntity l;
        if (e.getDamager() instanceof Player && e.getEntity() instanceof LivingEntity) {
            p = (Player) e.getDamager();
            l = (LivingEntity) e.getEntity();

            ItemStack itemMain = p.getInventory().getItemInMainHand();
            if (itemMain.getType().equals(Material.AIR))
                return;
            ItemMeta mainMeta = itemMain.getItemMeta();
            String type = "";
            if (mainMeta.getPersistentDataContainer().has(plugin.getTypeKey(),
                    PersistentDataType.STRING)) {
                type = mainMeta.getPersistentDataContainer().get(plugin.getTypeKey(),
                        PersistentDataType.STRING);
            }
            if (type.equals(Scythe.getScytheKey())) {
                double totalAttackDamage = 0.0;

                for (AttributeModifier am : mainMeta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE)) {
                    totalAttackDamage += am.getAmount();
                }

                if (l.getEquipment().getChestplate().equals(Material.AIR)) {

                }
            }
        }
    }
}
