package me.nekoowo.testitem.item;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public interface ExtendedItem {

    char getMaterialKey();

    char getStickKey();

    Material getMaterial();

    EquipmentSlot getSlot();

    String getTypeKey();

    double getAttackDamage();

    double getAttackSpeed();

    double getMoveSpeed();

    String getDisplayName();

    String getLocalizedName();

    void setWeaponAttri(double atkDmg, double atkSpd, double moveSpeed);

    void setNames(String displayName, String localizedName);

    ShapedRecipe getRecipe();
}
