package me.nekoowo.testitem.item;

import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;

public interface AttributeModify {

    AttributeModifier getAtkDmgModifier(double atkDmg, EquipmentSlot slot);

    AttributeModifier getAtkSpdModifier(double atkSpd, EquipmentSlot slot);

    AttributeModifier getMoveSpdModifier(double moveSpd, EquipmentSlot slot);

}
