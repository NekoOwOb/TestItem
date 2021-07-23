package me.nekoowo.testitem.listener;

import me.nekoowo.testitem.TestItem;
import me.nekoowo.testitem.item.ExtraItem;
import me.nekoowo.testitem.item.katana.Katana;
import me.nekoowo.testitem.item.scythe.Scythe;
import me.nekoowo.testitem.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class SmithingEvent implements Listener {

    TestItem plugin;

    public SmithingEvent(TestItem plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onItemSmithing(PrepareSmithingEvent e) {
        Inventory inventory = e.getInventory();
        ItemStack baseItem = inventory.getItem(0);
        ItemStack resultItem = e.getResult();
        if (baseItem != null && resultItem != null) {
            if (!baseItem.getType().equals(Material.AIR) && !resultItem.getType().equals(Material.AIR)) {
                ItemMeta resultMeta = resultItem.getItemMeta();
                ItemMeta baseMeta = baseItem.getItemMeta();

                NamespacedKey key = plugin.getTypeKey();
                PersistentDataContainer container = baseMeta.getPersistentDataContainer();
                String type = "";
                if (!container.has(key, PersistentDataType.STRING)) {
                    return;
                }
                type = container.get(key, PersistentDataType.STRING);

                if (type.equals(Katana.getKatanaKey())) {
                    if (baseMeta.getDisplayName().equals(Utils.getItemDName(plugin, "katana.diamond_katana"))) {
                        resultMeta.setDisplayName(Utils.getItemDName(plugin, "katana.netherite_katana"));
                    }
                    resultMeta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
                    resultMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, ExtraItem.getAtkDmgModifier(
                            Utils.getAttribute(plugin, "katana.netherite_katana", "attack_damage"),
                            EquipmentSlot.HAND));
                    resultItem.setItemMeta(resultMeta);
                }

                if (type.equals(Scythe.getScytheKey())) {
                    if (baseMeta.getDisplayName().equals(Utils.getItemDName(plugin, "scythe.diamond_scythe"))) {
                        resultMeta.setDisplayName(Utils.getItemDName(plugin, "scythe.netherite_scythe"));
                    }
                    resultMeta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
                    resultMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, ExtraItem.getAtkDmgModifier(
                            Utils.getAttribute(plugin, "scythe.netherite_scythe", "attack_damage"),
                            EquipmentSlot.HAND));
                    resultItem.setItemMeta(resultMeta);
                }

            }

        }
    }

}
