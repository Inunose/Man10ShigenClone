package com.inunose.man10shigenclone;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpecialItem {
    String name;
    Material type;
    int weight;

    public SpecialItem(String name, Material type,int weight ){
        this.name = name;
        this.type = type;
        this.weight = weight;
    }

    public ItemStack createItem(){
        ItemStack item = new ItemStack(type);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        item.setItemMeta(itemMeta);
        return item;
    }


}
