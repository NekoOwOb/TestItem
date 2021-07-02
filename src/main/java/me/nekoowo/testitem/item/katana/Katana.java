package me.nekoowo.testitem.item.katana;

import me.nekoowo.testitem.item.ExtraItem;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

public abstract class Katana extends ExtraItem {

    protected final char materialKey = 'M', stickKey = 'S';
    protected final String[] recipeShape = new String[] {"  M", " M ", "S  "};
    protected final EquipmentSlot slot = EquipmentSlot.HAND;
    protected final String typeKey = "Katana";

    public String[] getRecipeShape() {
        return recipeShape;
    }

    @Override
    public char getMaterialKey() {
        return materialKey;
    }

    @Override
    public char getStickKey() {
        return stickKey;
    }

    @Override
    public EquipmentSlot getSlot() {
        return slot;
    }

    @Override
    public Material getMaterial() {
        return null;
    }

    @Override
    public String getTypeKey() {
        return typeKey;
    }



}
