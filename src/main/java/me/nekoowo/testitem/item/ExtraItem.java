package me.nekoowo.testitem.item;

import me.nekoowo.testitem.TestItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.UUID;

public abstract class ExtraItem extends ItemStack implements AttributeModify {
    protected TestItem plugin;
    protected ItemStack extraItem;
    protected NamespacedKey itemKey;

    protected String displayName, localizedName, itemKeyName;
    protected EquipmentSlot slot;
    protected Material itemMaterial;
    protected RecipeChoice.MaterialChoice materialChoice;
    protected List<Double> attributes;

    protected final char materialKey = 'M', stickKey = 'S';
    protected final String[] recipeShape = new String[] {"  M", " M ", "S  "};
    protected final String typeKey = "ExtraItem";

    public ExtraItem(TestItem plugin, Material itemMaterial, String itemKeyName, EquipmentSlot slot, String displayName, String localizedName, List<Double> attributes, RecipeChoice.MaterialChoice materialChoice) {
        this.plugin = plugin;
        this.itemMaterial = itemMaterial;
        extraItem = new ItemStack(itemMaterial);
        this.itemKeyName = itemKeyName;
        itemKey = new NamespacedKey(plugin, itemKeyName);
        this.slot = slot;
        this.displayName = displayName;
        this.localizedName = localizedName;
        this.attributes = attributes;
        this.materialChoice = materialChoice;
    }

    public List<Double> getAttributes() {
        return attributes;
    }

    public double getArmor() {
        return getAttributes().get(0);
    }

    public double getArmorToughness() {
        return getAttributes().get(1);
    }

    public double getAttackDamage() {
        return getAttributes().get(2);
    }

    public double getAttackKnockback() {
        return getAttributes().get(3);
    }

    public double getAttackSpeed() {
        return getAttributes().get(4);
    }

    public double getFlyingSpeed() {
        return getAttributes().get(5);
    }

    public double getFollowRange() {
        return getAttributes().get(6);
    }

    public double getKnockbackResistance() {
        return getAttributes().get(7);
    }

    public double getLuck() {
        return getAttributes().get(8);
    }

    public double getMaxHealth() {
        return getAttributes().get(9);
    }

    public double getMovementSpeed() {
        return getAttributes().get(10);
    }

    public EquipmentSlot getSlot() {
        return slot;
    }

    public Material getItemMaterial() {
        return itemMaterial;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public RecipeChoice.MaterialChoice getMaterialChoice() {
        return materialChoice;
    }

    public String getTypeKey() {
        return typeKey;
    }

    public NamespacedKey getItemKey() {
        return itemKey;
    }

    public ItemStack getItem() {
        return extraItem;
    }

    public RecipeChoice.MaterialChoice getExactBaseChoice() {
        return new RecipeChoice.MaterialChoice(Material.DIAMOND_SWORD);
    }

    public RecipeChoice.MaterialChoice getExactAdditionalChoice() {
        return new RecipeChoice.MaterialChoice(Material.NETHERITE_INGOT);
    }

    public String[] getRecipeShape() {
        return recipeShape;
    }

    public char getMaterialKey() {
        return materialKey;
    }

    public char getStickKey() {
        return stickKey;
    }

    public void initializeItem() {
        ItemMeta itemMeta = getItem().getItemMeta();
        itemMeta.getPersistentDataContainer().set(plugin.getTypeKey(), PersistentDataType.STRING, getTypeKey());
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, getArmorModifier(getArmor(), getSlot()));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, getArmToughModifier(getArmorToughness(), getSlot()));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, getAtkDmgModifier(getAttackDamage(), getSlot()));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_KNOCKBACK, getAtkKnockModifier(getAttackKnockback(), getSlot()));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, getAtkSpdModifier(getAttackSpeed(), getSlot()));
        itemMeta.addAttributeModifier(Attribute.GENERIC_FLYING_SPEED, getFlySpdModifier(getFlyingSpeed(), getSlot()));
        itemMeta.addAttributeModifier(Attribute.GENERIC_FOLLOW_RANGE, getFolRangeModifier(getFollowRange(), getSlot()));
        itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, getKnockResistModifier(getKnockbackResistance(), getSlot()));
        itemMeta.addAttributeModifier(Attribute.GENERIC_LUCK, getLuckModifier(getLuck(), getSlot()));
        itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, getMaxHealthModifier(getMaxHealth(), getSlot()));
        itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, getMoveSpdModifier(getMovementSpeed(), getSlot()));
        itemMeta.setDisplayName(getDisplayName());
        itemMeta.setLocalizedName(getLocalizedName());
        getItem().setItemMeta(itemMeta);
//        plugin.addKey(itemKey);
    }

    public Recipe getRecipe() {
        if (getItem().getType().equals(Material.NETHERITE_SWORD) || getItem().getType().equals(Material.NETHERITE_HOE)) {
            return new SmithingRecipe(getItemKey(), getItem(), getExactBaseChoice(), getExactAdditionalChoice());
        }

        ShapedRecipe shapedRecipe = new ShapedRecipe(getItemKey(), getItem());
        shapedRecipe.shape(getRecipeShape());
        shapedRecipe.setIngredient(getStickKey(), Material.STICK);
        shapedRecipe.setIngredient(getMaterialKey(), getMaterialChoice());
        return shapedRecipe;
    }

    // Attribute Modifiers for each attribute

    public static AttributeModifier getArmorModifier(double armor, EquipmentSlot slot) {
        return new AttributeModifier(UUID.randomUUID(), "Armor", armor,
                AttributeModifier.Operation.ADD_NUMBER, slot);
    }

    public static AttributeModifier getArmToughModifier(double armTough, EquipmentSlot slot) {
        return new AttributeModifier(UUID.randomUUID(), "Armor Toughness", armTough,
                AttributeModifier.Operation.ADD_NUMBER, slot);
    }

    public static AttributeModifier getAtkDmgModifier(double atkDmg, EquipmentSlot slot) {
        return new AttributeModifier(UUID.randomUUID(), "Attack Damage", atkDmg,
                AttributeModifier.Operation.ADD_NUMBER, slot);
    }

    public static AttributeModifier getAtkKnockModifier(double atkKnock, EquipmentSlot slot) {
        return new AttributeModifier(UUID.randomUUID(), "Attack Knockback", atkKnock,
                AttributeModifier.Operation.ADD_NUMBER, slot);
    }

    public static AttributeModifier getAtkSpdModifier(double atkSpd, EquipmentSlot slot) {
        return new AttributeModifier(UUID.randomUUID(), "Attack Speed", (atkSpd - 4),
                AttributeModifier.Operation.ADD_NUMBER, slot);
    }

    public static AttributeModifier getFlySpdModifier(double flySpd, EquipmentSlot slot) {
        return new AttributeModifier(UUID.randomUUID(), "Flying Speed", flySpd,
                AttributeModifier.Operation.ADD_NUMBER, slot);
    }

    public static AttributeModifier getFolRangeModifier(double folRange, EquipmentSlot slot){
        return new AttributeModifier(UUID.randomUUID(), "Follow Range", folRange,
                AttributeModifier.Operation.ADD_NUMBER, slot);
    }

    public static AttributeModifier getKnockResistModifier(double knockResist, EquipmentSlot slot){
        return new AttributeModifier(UUID.randomUUID(), "Knockback Resistance", knockResist,
                AttributeModifier.Operation.ADD_NUMBER, slot);
    }

    public static AttributeModifier getLuckModifier(double luck, EquipmentSlot slot){
        return new AttributeModifier(UUID.randomUUID(), "Luck", luck,
                AttributeModifier.Operation.ADD_NUMBER, slot);
    }

    public static AttributeModifier getMaxHealthModifier(double maxHealth, EquipmentSlot slot){
        return new AttributeModifier(UUID.randomUUID(), "Max Health", maxHealth,
                AttributeModifier.Operation.ADD_NUMBER, slot);
    }

    public static AttributeModifier getMoveSpdModifier(double moveSpd, EquipmentSlot slot){
        return new AttributeModifier(UUID.randomUUID(), "Attack Damage", moveSpd,
                AttributeModifier.Operation.ADD_NUMBER, slot);
    }

}
