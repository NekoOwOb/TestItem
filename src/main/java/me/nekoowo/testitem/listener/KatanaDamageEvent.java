package me.nekoowo.testitem.listener;

import me.nekoowo.testitem.TestItem;
import me.nekoowo.testitem.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.UUID;

public class KatanaDamageEvent implements Listener {

    //TODO: Attack Speed = 0.8( hit/sec ), Recovery Time = 1.25sec ( 1/0.8 = 1.25 )

//    double attackSpeed = 0.2D;
    final double COOLDOWN_DAMAGE = 0.5D;

    HashMap<UUID, Double> normalAttackDamage = new HashMap<>();

    TestItem plugin;

    public KatanaDamageEvent(TestItem plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void KatanaAttack(EntityDamageByEntityEvent e) {
        Player p;
        if (e.getDamager() instanceof Player) {
            p = (Player) e.getDamager();
            UUID playerUUID = p.getUniqueId();
            float cooldown = p.getAttackCooldown();
            boolean isNormalAttack = e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK);
            boolean isSweepAttack = e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK);
            ItemStack itemInMain = p.getInventory().getItemInMainHand();
            ItemStack itemInOff = p.getInventory().getItemInOffHand();
            if (itemInMain.getType().equals(Material.AIR)) {
                return;
            }
            ItemMeta metaInMain = itemInMain.getItemMeta();
            NamespacedKey key = plugin.getTypeKey();
            PersistentDataContainer container = metaInMain.getPersistentDataContainer();
            String type = "";
            if (container.has(key, PersistentDataType.STRING)) {
                type = container.get(key, PersistentDataType.STRING);
            }
            if (type.equals("Katana")) {
                double totalAttackDamage = 0.0;

                for (AttributeModifier am : metaInMain.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE)) {
                    totalAttackDamage += am.getAmount();
                }

                double damage = totalAttackDamage;
                if (!itemInOff.getType().equals(Material.AIR)) {
                    damage /= 2;
                }

                if (isNormalAttack) {
                    if (cooldown >= 0.848 && cooldown < 1.0) {
                        damage /= 4;
                        p.sendMessage(Utils.chat("Your sweep attack is still in cooldown!"));
                        e.setDamage(damage);
                        normalAttackDamage.put(playerUUID, damage);
                    } else if (p.getAttackCooldown() < 0.848) {
                        p.sendMessage(Utils.chat("Your attack is still in cooldown!"));
                        e.setDamage(COOLDOWN_DAMAGE);
                        normalAttackDamage.put(playerUUID, COOLDOWN_DAMAGE);
                    } else {
                        e.setDamage(damage);
                        normalAttackDamage.put(playerUUID, damage);
                    }
                }

                if (isSweepAttack) {
                    e.setDamage(normalAttackDamage.get(playerUUID));
                }

            }
        }
    }

}
