package me.nekoowo.testitem.item;

import me.nekoowo.testitem.TestItem;
import me.nekoowo.testitem.item.katana.Katana;
import me.nekoowo.testitem.item.sickle.Sickle;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.RecipeChoice;

import java.util.List;

public class ItemBuildBuilder implements ItemBuild {
    private TestItem plugin;
    private String displayName, localizedName, itemKeyName;
    private EquipmentSlot slot;
    private Material itemMaterial;
    private RecipeChoice.MaterialChoice materialChoice;
    private List<Double> attributes;

    @Override
    public ItemBuildBuilder setPlugin(TestItem plugin) {
        this.plugin = plugin;
        return this;
    }

    @Override
    public ItemBuildBuilder setItemKeyName(String itemKeyName) {
        this.itemKeyName = itemKeyName;

        return this;
    }

    @Override
    public ItemBuildBuilder setNames(String displayName, String localizedName) {
        this.displayName = displayName;
        this.localizedName = localizedName;
        return this;
    }

    @Override
    public ItemBuildBuilder setSlot(EquipmentSlot slot) {
        this.slot = slot;
        return this;
    }

    @Override
    public ItemBuildBuilder setMaterialChoice(RecipeChoice.MaterialChoice materialChoice) {
        this.materialChoice = materialChoice;
        return this;
    }

    @Override
    public ItemBuildBuilder setItemMaterial(Material itemMaterial) {
        this.itemMaterial = itemMaterial;
        return this;
    }

    @Override
    public ItemBuildBuilder setAttributes(List<Double> attributes) {
        this.attributes = attributes;
        return this;
    }

    public ExtraItem build(String itemKey) {
        if (itemKey.equals(Katana.getKatanaKey().toLowerCase())) {
            return new Katana(plugin, itemMaterial, itemKeyName, slot, displayName, localizedName, attributes, materialChoice);
        }
        if (itemKey.equals(Sickle.getSickleKey().toLowerCase()))
            return new Sickle(plugin, itemMaterial, itemKeyName, slot, displayName, localizedName, attributes, materialChoice);
        return null;
    }

}
