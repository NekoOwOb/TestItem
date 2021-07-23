//package me.nekoowo.testitem.item.katana;
//
//import me.nekoowo.testitem.TestItem;
//import me.nekoowo.testitem.item.ExtendedItem;
//import me.nekoowo.testitem.utils.Utils;
//import org.bukkit.Material;
//import org.bukkit.NamespacedKey;
//import org.bukkit.Tag;
//import org.bukkit.attribute.Attribute;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.RecipeChoice;
//import org.bukkit.inventory.ShapedRecipe;
//import org.bukkit.inventory.meta.ItemMeta;
//import org.bukkit.persistence.PersistentDataType;
//
//public class WoodenKatana extends Katana implements ExtendedItem {
//
//    private TestItem plugin;
//
//    protected final double attackDamage = 4.0, attackSpeed = 1.2, moveSpeed = 0.0;
//    protected final String displayName = Utils.chat("&f木武士刀");
//    protected NamespacedKey itemKey;
//
//    ItemStack woodenKatana;
//
//    public WoodenKatana(TestItem plugin) {
//        super(plugin);
//        this.plugin = plugin;
//    }
//
//    @Override
//    public ItemStack getItem() {
//        woodenKatana = new ItemStack(this.getMaterial());
//        return woodenKatana;
//    }
//
//    @Override
//    public Material getMaterial() {
//        return Material.WOODEN_SWORD;
//    }
//
//    @Override
//    public String getDisplayName() {
//        return displayName;
//    }
//
//    @Override
//    public double getAttackDamage() {
//        return attackDamage;
//    }
//
//    @Override
//    public double getAttackSpeed() {
//        return attackSpeed;
//    }
//
//    @Override
//    public double getMoveSpeed() {
//        return moveSpeed;
//    }
//
//    public NamespacedKey getItemKey() {
//        itemKey = new NamespacedKey(plugin,  "wooden_katana");
//        return itemKey;
//    }
//
//    @Override
//    public ShapedRecipe getRecipe() {
//        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
//        ItemMeta itemMeta = item.getItemMeta();
//        itemMeta.getPersistentDataContainer().set(plugin.getTypeKey(), PersistentDataType.STRING, getTypeKey());
//        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, getAtkDmgModifier(getAttackDamage(), getSlot()));
//        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, getAtkSpdModifier(getAttackSpeed(), getSlot()));
//        itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, getMoveSpdModifier(getMoveSpeed(), getSlot()));
//        itemMeta.setDisplayName(getDisplayName());
//        itemMeta.setLocalizedName("wooden_katana");
//        item.setItemMeta(itemMeta);
//        plugin.addKey(getItemKey());
//        ShapedRecipe shapedRecipe = new ShapedRecipe(getItemKey(), item);
//        shapedRecipe.shape(getRecipeShape());
//        shapedRecipe.setIngredient(getStickKey(), Material.STICK);
//        shapedRecipe.setIngredient(getMaterialKey(), new RecipeChoice.MaterialChoice(Tag.PLANKS));
//        return shapedRecipe;
//    }
//
//}
