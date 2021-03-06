package me.nekoowo.testitem.item.katana;

import me.nekoowo.testitem.TestItem;
import me.nekoowo.testitem.item.ExtraItem;
import org.bukkit.Material;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class Katana extends ExtraItem {

    private ItemStack katana;

    protected final String[] recipeShape = new String[] {"  M", " M ", "S  "};
    protected final String typeKey = "Katana";
    protected final static String katanaKey = "Katana";

    public Katana(TestItem plugin, Material itemMaterial, String itemKeyName, EquipmentSlot slot, String displayName, String localizedName, List<Double> attributes, RecipeChoice.MaterialChoice materialChoice) {
        super(plugin, itemMaterial, itemKeyName, slot, displayName, localizedName, attributes, materialChoice);
        katana = new ItemStack(itemMaterial);
        initializeItem();
    }

    public String[] getRecipeShape() {
        return recipeShape;
    }

    @Override
    public String getTypeKey() {
        return typeKey;
    }

    @Override
    public ItemStack getItem() {
        return katana;
    }

    @Override
    public void initializeItem() {
        super.initializeItem();
        ItemMeta itemMeta = getItem().getItemMeta();
        itemMeta.getPersistentDataContainer().set(plugin.getTypeKey(), PersistentDataType.STRING, getTypeKey());
        getItem().setItemMeta(itemMeta);
        plugin.addKey(itemKey);
    }

    @Override
    public Recipe getRecipe() {
        if (getItem().getType().equals(Material.NETHERITE_SWORD)) {
            return new SmithingRecipe(getItemKey(), getItem(), getExactBaseChoice(), getExactAdditionalChoice());
        }

        ShapedRecipe shapedRecipe = new ShapedRecipe(getItemKey(), getItem());
        shapedRecipe.shape(getRecipeShape());
        shapedRecipe.setIngredient(getStickKey(), Material.STICK);
        shapedRecipe.setIngredient(getMaterialKey(), materialChoice);
        return shapedRecipe;
    }

    public static String getKatanaKey() {
        return katanaKey;
    }

    // Enchantment

    public static double getDamageAllValue(Integer level) {
        if (level == 1)
            return 0.4;
        else if (level > 1)
            return 0.4 + (level - 1) * 0.2;
        else
            return 0;
    }

    public static double getDamageUndeadValue(Integer level) {
        if (level >= 1)
            return level;
        else
            return 0;
    }

    public static double getDamageArthropodsValue(Integer level) {
        if (level >= 1)
            return level;
        else
            return 0;
    }

    public static double setDamageBySweepingValue(Double damage, Integer level) {
        return 1 + damage * (1 + (level / (level + 2.25)));
    }

    public static int getFireAspectTicks(Integer level) {
        return 80 * level;
    }

    public static int getKnockbackDistance(Integer level) {
        return 3 * (level - 1);
    }

}
