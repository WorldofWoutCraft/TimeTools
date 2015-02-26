package com.woutwoot.timetools;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author woutwoot
 *         Created by on 26/02/2015 - 18:10.
 */
public class Main extends JavaPlugin {

    @Override
    public void onEnable(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new TimeTask(), 20L, 20L);
        this.getCommand("tt").setExecutor(this);
    }

    @Override
    public void onDisable(){
        Bukkit.getScheduler().cancelTasks(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("tt")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                ItemStack i = new ItemStack(Material.DIAMOND_AXE);
                List<String> loreList = new ArrayList<>();
                loreList.add("Time left: 00:01:18");
                ItemMeta im = i.getItemMeta();
                im.setLore(loreList);
                i.setItemMeta(im);
                p.getInventory().addItem(i);
            }
            return true;
        }
        return false;
    }
}
