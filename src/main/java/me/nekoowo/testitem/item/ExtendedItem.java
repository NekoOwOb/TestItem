package me.nekoowo.testitem.item;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public interface ExtendedItem {

    Material getMaterial();

    double getAttackDamage();

    double getAttackSpeed();

    double getMoveSpeed();

    char getMaterialKey();

    char getStickKey();

    String getDisplayName();

    EquipmentSlot getSlot();

    String getTypeKey();

    ShapedRecipe getRecipe(ItemStack item);
}
