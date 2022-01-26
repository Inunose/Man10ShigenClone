package com.inunose.man10shigenclone;

import io.papermc.paper.event.block.BlockBreakBlockEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Random;

public final class Man10ShigenClone extends JavaPlugin implements @NotNull Listener {

    ArrayList<SpecialItem> items = new ArrayList<>();


    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this, this);

        items.add(new SpecialItem("§b特別なダイアモンド",Material.DIAMOND,2));
        items.add(new SpecialItem("§a特別なエメラルド",Material.EMERALD,1));
        items.add(new SpecialItem("§6特別な金",Material.GOLD_INGOT,3));
        items.add(new SpecialItem("§6特別な石炭",Material.COAL,5));
        items.add(new SpecialItem("§6特別な鉄",Material.IRON_INGOT,4));
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {

        if(e.getBlock().getType() != Material.STONE) return;

        int i = new Random().nextInt(10);
        if (i != 0) return;
        e.setDropItems(false);

        SpecialItem dropItem = pickGem();
        if(dropItem == null)return;

        Block b = e.getBlock();
        b.getWorld().dropItemNaturally(b.getLocation(), dropItem.createItem());
    }

    public SpecialItem pickGem() {
        int weaghtTotal = 0;
        for(SpecialItem item: items){
            weaghtTotal += item.weight;
        }

        int currentTotal = 0;
        int r = new Random().nextInt(weaghtTotal)+1;
        for(SpecialItem item: items){
            currentTotal += item.weight;
            if(r <= currentTotal) return item;
        }
        return null;
    }

}