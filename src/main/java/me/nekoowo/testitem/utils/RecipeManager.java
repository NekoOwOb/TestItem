package me.nekoowo.testitem.utils;

import me.nekoowo.testitem.TestItem;
import me.nekoowo.testitem.item.ExtraItem;
import me.nekoowo.testitem.item.katana.StoneKatana;
import me.nekoowo.testitem.item.katana.WoodenKatana;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class RecipeManager {

    TestItem plugin;

    private List<ExtraItem> extraItemList = new ArrayList<>();

    public RecipeManager(TestItem plugin) {
        this.plugin = plugin;
    }

    public void addRecipe() {
        initialization();
        for (ExtraItem i : extraItemList) {
            Bukkit.addRecipe(i.getRecipe(i.getItem()));
        }
    }

    public void initialization() {
        WoodenKatana woodenKatana = new WoodenKatana(plugin);
        extraItemList.add(woodenKatana);
        StoneKatana stoneKatana = new StoneKatana(plugin);
        extraItemList.add(stoneKatana);
    }

}
