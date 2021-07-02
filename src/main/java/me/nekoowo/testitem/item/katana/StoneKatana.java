package me.nekoowo.testitem.item.katana;

import me.nekoowo.testitem.TestItem;
import me.nekoowo.testitem.item.ExtendedItem;
import me.nekoowo.testitem.utils.Utils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class StoneKatana extends Katana implements ExtendedItem {

    private TestItem plugin;
    protected final double attackDamage = 6.0, attackSpeed = 1.2, moveSpeed = 0.0;
    protected final String displayName = Utils.chat("&f石武士刀");
    protected NamespacedKey itemKey;

    ItemStack stoneKatana;

    public StoneKatana(TestItem plugin) {
        this.plugin = plugin;
    }

    @Override
    public ItemStack getItem() {
        stoneKatana = new ItemStack(this.getMaterial());
        return stoneKatana;
    }

    @Override
    public Material getMaterial() {
        return Material.STONE_SWORD;
    }

    @Override
    public double getAttackDamage() {
        return attackDamage;
    }

    @Override
    public double getAttackSpeed() {
        return attackSpeed;
    }

    @Override
    public double getMoveSpeed() {
        return moveSpeed;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    public NamespacedKey getItemKey() {
        itemKey = new NamespacedKey(plugin,  "stone_katana");
        return itemKey;
    }

    @Override
    public ShapedRecipe getRecipe(ItemStack item) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.getPersistentDataContainer().set(plugin.getTypeKey(), PersistentDataType.STRING, getTypeKey());
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, getAtkDmgModifier(getAttackDamage(), getSlot()));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, getAtkSpdModifier(getAttackSpeed(), getSlot()));
        itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, getMoveSpdModifier(getMoveSpeed(), getSlot()));
        itemMeta.setDisplayName(getDisplayName());
        item.setItemMeta(itemMeta);
        plugin.addKey(getItemKey());
        ShapedRecipe shapedRecipe = new ShapedRecipe(getItemKey(), item);
        shapedRecipe.shape(getRecipeShape());
        shapedRecipe.setIngredient(getStickKey(), Material.STICK);
        shapedRecipe.setIngredient(getMaterialKey(), new RecipeChoice.MaterialChoice(Tag.ITEMS_STONE_TOOL_MATERIALS));
        return shapedRecipe;
    }
}
