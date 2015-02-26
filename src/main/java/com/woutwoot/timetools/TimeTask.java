package com.woutwoot.timetools;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.ParseException;
import java.util.List;

/**
 * @author woutwoot
 *         Created by on 26/02/2015 - 18:28.
 */
public class TimeTask implements Runnable {

    @Override
    public void run() {
        for(Player p : Bukkit.getOnlinePlayers()){
            if(isTimeTool(p.getItemInHand())){
                try {
                    updateTool(p.getItemInHand());
                } catch (ParseException e) {}
            }
        }
    }

    private void updateTool(ItemStack itemInHand) throws ParseException {
        for(String line : itemInHand.getItemMeta().getLore()){
            if(line.contains("Time left: ")){
                String strTime = line.split(": ")[1];
                JustTime time = JustTime.parse(strTime);
                if(time.isOver()){
                    int maxDamage = itemInHand.getType().getMaxDurability();
                    itemInHand.setDurability((short) (maxDamage));
                    ItemMeta im = itemInHand.getItemMeta();
                    List<String> lores = im.getLore();
                    for(String s : lores){
                        if(s.contains("Time left: ")){
                            lores.set(lores.indexOf(s), "Time's up!");
                        }
                    }
                    im.setLore(lores);
                    itemInHand.setItemMeta(im);
                }else{
                    time.decrease();
                    updateLore(itemInHand, time);
                }
            }
        }
    }

    private void updateLore(ItemStack i, JustTime t){
        ItemMeta im = i.getItemMeta();
        List<String> lores = im.getLore();
        for(String s : lores){
            if(s.contains("Time left: ")){
                lores.set(lores.indexOf(s), "Time left: " + t.getString());
            }
        }
        im.setLore(lores);
        i.setItemMeta(im);
    }

    private boolean isTimeTool(ItemStack itemInHand) {
        if(itemInHand != null && itemInHand.getItemMeta().getLore() != null){
            for(String line : itemInHand.getItemMeta().getLore()){
                if(line.contains("Time left:")){
                    return true;
                }
            }
        }
        return false;
    }

}
