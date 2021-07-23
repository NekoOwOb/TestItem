package me.nekoowo.testitem.item;

import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;

public interface AttributeModify {

    static AttributeModifier getAtkDmgModifier(double atkDmg, EquipmentSlot slot) {
        return null;
    }

    static AttributeModifier getAtkSpdModifier(double atkSpd, EquipmentSlot slot) {
        return null;
    }

    static AttributeModifier getMoveSpdModifier(double moveSpd, EquipmentSlot slot) {
        return null;
    }

}
