package me.nekoowo.testitem.item;

import me.nekoowo.testitem.TestItem;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;

import java.util.List;

public interface ItemBuild {
    ItemBuildBuilder setPlugin(TestItem plugin);

    ItemBuildBuilder setItemKeyName(String itemKeyName);

    ItemBuildBuilder setNames(String displayName, String localizedName);

    ItemBuildBuilder setSlot(EquipmentSlot slot);

    ItemBuildBuilder setMaterialChoice(RecipeChoice.MaterialChoice materialChoice);

    ItemBuildBuilder setItemMaterial(Material itemMaterial);

    ItemBuildBuilder setAttributes(List<Double> attributes);

}
