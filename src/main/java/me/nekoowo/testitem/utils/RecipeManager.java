package me.nekoowo.testitem.utils;

import me.nekoowo.testitem.TestItem;
import me.nekoowo.testitem.item.ExtraItem;
import me.nekoowo.testitem.item.ItemBuildBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.RecipeChoice;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class RecipeManager {

    TestItem plugin;
    FileConfiguration config;

    private List<ExtraItem> extraItemList = new ArrayList<>();

    public RecipeManager(TestItem plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
    }

    public void addRecipe() {
        getExtraItems();
        for (ExtraItem i : extraItemList) {
            Bukkit.addRecipe(i.getRecipe());
        }
    }

    public void getExtraItems() {
        int count = 0;
        String id, item, display_name, choice, slot;
        List<Double> attributes = new ArrayList<>();
        ConfigurationSection section = config.getConfigurationSection("items");
        for (String key : section.getKeys(false)) {
            String itemKey = "items." + key;
            plugin.getLogger().log(Level.INFO, "loading custom item: [" + key + "]");

            ConfigurationSection subSection = config.getConfigurationSection(itemKey);
            for (String subKey : subSection.getKeys(false)) {
                attributes.clear();
                subKey = itemKey + "." + subKey;
                id = config.getString(subKey + ".id");
                item = config.getString(subKey + ".item");
                display_name = config.getString(subKey + ".display_name");
                choice = config.getString(subKey + ".choice", "BEDROCK");
                slot = config.getString(subKey + ".slot");

                String attributeKey = subKey + ".attributes";
                ConfigurationSection attributeSection = config.getConfigurationSection(attributeKey);
                for (String attribute : attributeSection.getKeys(false)) {
                    attributes.add(config.getDouble(attributeKey + "." + attribute));
                }

                ItemBuildBuilder itemBuildBuilder = new ItemBuildBuilder();
                itemBuildBuilder.setPlugin(plugin)
                        .setItemMaterial(Material.valueOf(item))
                        .setItemKeyName(id)
                        .setNames(Utils.chat(display_name), id)
                        .setSlot(EquipmentSlot.valueOf(slot))
                        .setAttributes(attributes)
                        .setMaterialChoice(materialChoice(choice));
                extraItemList.add(itemBuildBuilder.build(key));
                count++;
                plugin.getLogger().log(Level.INFO, "loaded item: " + id);
            }
            plugin.getLogger().log(Level.INFO, "=============================");
        }
        plugin.getLogger().log(Level.INFO, "loaded " + count + " items!");
    }

//    public void initialization() {
//        ItemBuildBuilder itemBuildBuilder = new ItemBuildBuilder();
//        itemBuildBuilder.setPlugin(plugin)
//                .setItemMaterial(Material.WOODEN_SWORD)
//                .setItemKeyName("wooden_katana")
//                .setNames(Utils.chat("&f木武士刀"), "wooden_katana")
//                .setSlot(EquipmentSlot.HAND)
//                .setWeaponAttribute(4.0, 1.2, 0)
//                .setMaterialChoice(new RecipeChoice.MaterialChoice(Tag.PLANKS));
//        Katana woodenKatana = itemBuildBuilder.build();
//        extraItemList.add(woodenKatana);
//        itemBuildBuilder.setItemMaterial(Material.STONE_SWORD)
//                .setItemKeyName("stone_katana")
//                .setNames(Utils.chat("&f石武士刀"), "stone_katana")
//                .setWeaponAttribute(5.0, 1.2, 0)
//                .setMaterialChoice(new RecipeChoice.MaterialChoice(Tag.ITEMS_STONE_TOOL_MATERIALS));
//        Katana stoneKatana = itemBuildBuilder.build();
//        extraItemList.add(stoneKatana);
//        itemBuildBuilder.setItemMaterial(Material.GOLDEN_SWORD)
//                .setItemKeyName("golden_katana")
//                .setNames(Utils.chat("&f金武士刀"), "golden_katana")
//                .setMaterialChoice(new RecipeChoice.MaterialChoice(Material.GOLD_INGOT));
//        Katana goldenKatana = itemBuildBuilder.build();
//        extraItemList.add(goldenKatana);
//        itemBuildBuilder.setItemMaterial(Material.IRON_SWORD)
//                .setItemKeyName("iron_katana")
//                .setNames(Utils.chat("&f鐵武士刀"), "iron_katana")
//                .setWeaponAttribute(6.0, 1.2, 0)
//                .setMaterialChoice(new RecipeChoice.MaterialChoice(Material.IRON_INGOT));
//        Katana ironKatana = itemBuildBuilder.build();
//        extraItemList.add(ironKatana);
//        itemBuildBuilder.setItemMaterial(Material.DIAMOND_SWORD)
//                .setItemKeyName("diamond_katana")
//                .setNames(Utils.chat("&f鑽石武士刀"), "diamond_katana")
//                .setWeaponAttribute(7.0, 1.2, 0)
//                .setMaterialChoice(new RecipeChoice.MaterialChoice(Material.DIAMOND));
//        Katana diamondKatana = itemBuildBuilder.build();
//        extraItemList.add(diamondKatana);
//        itemBuildBuilder.setItemMaterial(Material.NETHERITE_SWORD)
//                .setItemKeyName("netherite_katana")
//                .setNames(Utils.chat("&f獄髓武士刀"), "netherite_katana")
//                .setWeaponAttribute(8.0, 1.2, 0)
//                .setMaterialChoice(new RecipeChoice.MaterialChoice(Material.NETHERITE_INGOT));
//        Katana netheriteKatana = itemBuildBuilder.build();
//        extraItemList.add(netheriteKatana);
//    }

    private RecipeChoice.MaterialChoice materialChoice(String choice) {
        switch (choice) {
            case "PLANKS":
                return new RecipeChoice.MaterialChoice(Tag.PLANKS);

            case "STONES":
                return new RecipeChoice.MaterialChoice(Tag.ITEMS_STONE_TOOL_MATERIALS);

            case "GOLD":
                return new RecipeChoice.MaterialChoice(Material.GOLD_INGOT);

            case "IRON":
                return new RecipeChoice.MaterialChoice(Material.IRON_INGOT);

            case "DIAMOND":
                return new RecipeChoice.MaterialChoice(Material.DIAMOND);

            case "NETHERITE":
                return new RecipeChoice.MaterialChoice(Material.NETHERITE_INGOT);

            default:
                return new RecipeChoice.MaterialChoice(Material.BEDROCK);
        }
    }

}
