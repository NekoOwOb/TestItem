package me.nekoowo.testitem.listener.item;

import me.nekoowo.testitem.TestItem;
import me.nekoowo.testitem.item.scythe.Scythe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class ScytheDamageEvent implements Listener {

    TestItem plugin;
    Map<Enchantment, Integer> enchantments = new HashMap<>();

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
            enchantments = mainMeta.getEnchants();
            String type = "";
            if (mainMeta.getPersistentDataContainer().has(plugin.getTypeKey(),
                    PersistentDataType.STRING)) {
                type = mainMeta.getPersistentDataContainer().get(plugin.getTypeKey(),
                        PersistentDataType.STRING);
            }
            if (type.equals(Scythe.getScytheKey())) {
                double totalAttackDamage = 0.0;
                double penetration = 0.0;

                for (AttributeModifier am : mainMeta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE)) {
                    totalAttackDamage += am.getAmount();
                }

                if (enchantments.containsKey(Enchantment.DAMAGE_ALL)) {
                    penetration += Scythe.getDamageAllValue(enchantments.get(Enchantment.DAMAGE_ALL));
                }

                if (enchantments.containsKey(Enchantment.DAMAGE_UNDEAD)) {
                    if (l.getCategory().equals(EntityCategory.UNDEAD)) {
                        penetration += Scythe.getDamageUndeadValue(enchantments.get(Enchantment.DAMAGE_UNDEAD));
                    }
                }

                if (enchantments.containsKey(Enchantment.DAMAGE_ARTHROPODS)) {
                    if (l.getCategory().equals(EntityCategory.ARTHROPOD)) {
                        penetration += Scythe.getDamageArthropodsValue(enchantments.get(Enchantment.DAMAGE_ARTHROPODS));
                    }
                }

//                plugin.getLogger().log(Level.INFO, "armor = " + p.getAttribute(Attribute.GENERIC_ARMOR).getValue());
                ItemStack chestArmor = l.getEquipment().getChestplate();
                if (chestArmor == null) {
                    penetration += 4;
                } else {
                    if (chestArmor.getType().equals(Material.NETHERITE_CHESTPLATE) || chestArmor.getType().equals(Material.DIAMOND_CHESTPLATE)) {
                        penetration += 0;
                    } else if (chestArmor.getType().equals(Material.IRON_CHESTPLATE)) {
                        penetration += 1;
                    } else if (chestArmor.getType().equals(Material.CHAINMAIL_CHESTPLATE) || chestArmor.getType().equals(Material.GOLDEN_CHESTPLATE)) {
                        penetration += 2;
                    } else if (chestArmor.getType().equals(Material.LEATHER_CHESTPLATE)) {
                        penetration += 3;
                    } else {
                        penetration += 4;
                    }
                }

                //https://www.spigotmc.org/threads/customizing-the-enchantment-table.202201/


                double armorPoint = l.getAttribute(Attribute.GENERIC_ARMOR).getValue();
                double toughness = l.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getValue();
                plugin.getLogger().log(Level.INFO, "armorPt = " + armorPoint + ", pen = " + penetration);
                armorPoint -= penetration;
                if (armorPoint < 0) {
                    plugin.getLogger().log(Level.INFO, "atk = " + totalAttackDamage);
                    totalAttackDamage += Math.abs(armorPoint);
                    plugin.getLogger().log(Level.INFO, "atk with pen = " + totalAttackDamage + "(armorPt = " + armorPoint);
                    armorPoint = 0;
                }
                e.setDamage(damageAfterArmor(totalAttackDamage, armorPoint, toughness));

            }
        }
    }

    private double damageAfterArmor(double damage, double armor, double toughness) {
        return damage * (1 - (Math.min(20, Math.max(armor / 5, armor - ((4 * damage) / (toughness + 8)))) / 25));
    }
}
