package me.nekoowo.testitem.item;

import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public abstract class ExtraItem extends ItemStack implements ExtendedItem, AttributeModify {

    public ItemStack itemStack;

    public ItemStack getItem() {
        return itemStack;
    }

    @Override
    public AttributeModifier getAtkDmgModifier(double atkDmg, EquipmentSlot slot) {
        return new AttributeModifier(UUID.randomUUID(), "Attack Damage", atkDmg,
                AttributeModifier.Operation.ADD_NUMBER, slot);
    }

    @Override
    public AttributeModifier getAtkSpdModifier(double atkSpd, EquipmentSlot slot) {
        return new AttributeModifier(UUID.randomUUID(), "Attack Damage", (atkSpd - 4),
                AttributeModifier.Operation.ADD_NUMBER, slot);
    }

    @Override
    public AttributeModifier getMoveSpdModifier(double moveSpd, EquipmentSlot slot){
        return new AttributeModifier(UUID.randomUUID(), "Attack Damage", moveSpd,
                AttributeModifier.Operation.ADD_NUMBER, slot);
    }

}
