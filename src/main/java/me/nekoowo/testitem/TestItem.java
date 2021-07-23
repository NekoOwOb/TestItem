package me.nekoowo.testitem;

import me.nekoowo.testitem.listener.EntityGetDamagedEvent;
import me.nekoowo.testitem.listener.JoinEvent;
import me.nekoowo.testitem.listener.item.KatanaDamageEvent;
import me.nekoowo.testitem.listener.SmithingEvent;
import me.nekoowo.testitem.listener.item.ScytheDamageEvent;
import me.nekoowo.testitem.utils.RecipeManager;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class TestItem extends JavaPlugin {

    public List<NamespacedKey> keys = new ArrayList<>();
    public NamespacedKey typeKey = new NamespacedKey(this, "type-key");;

    RecipeManager recipeManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        new JoinEvent(this);
        new KatanaDamageEvent(this);
        new ScytheDamageEvent(this);
        new EntityGetDamagedEvent(this);
        new SmithingEvent(this);
        recipeManager = new RecipeManager(this);
        recipeManager.addRecipe();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public NamespacedKey getTypeKey() {
        return typeKey;
    }

    public List<NamespacedKey> getKeys() {
        return keys;
    }

    public void addKey(NamespacedKey key) {
        keys.add(key);
    }
}
