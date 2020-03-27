package com.toke1597.nightv.Effecter;


import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import com.toke1597.nightv.Recipe.Recipe;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Effecter {

    private final Plugin plugin;

    public Effecter(Plugin plugin) {
        this.plugin = plugin;
    }

    public Recipe recipe = new Recipe(null);

    public void addPotionEffect(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 2147483647, 0, false, false));
    }

    public void removePotionEffect(Player p) {
        p.removePotionEffect(PotionEffectType.NIGHT_VISION);
    }


    public void armorChangeEvent(PlayerArmorChangeEvent e) {
        ItemStack oldItem = e.getOldItem();
        ItemStack newItem = e.getNewItem();
        Player p = e.getPlayer();
        //period of Battery
        long period1 = 60 * 20L * 10;


        if (newItem.getItemMeta() != null && newItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "[레전더리] 야간투시경")
                && p.getInventory().containsAtLeast(recipe.Battery(1), 1)) {
            //delay 마다 반복
            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                @Override
                public void run() {
                    if (!p.getInventory().containsAtLeast(recipe.Battery(1), 1)) {
                        p.sendMessage(ChatColor.RED + "배터리가 부족합니다.");
                        removePotionEffect(p);
                        return;
                    }
                    p.getInventory().removeItemAnySlot(recipe.Battery(1));
                    p.sendMessage("배터리 하나를 소모했습니다.");
                    addPotionEffect(p);
                }
            }, 0L, period1);
        } else if (newItem.getItemMeta() != null && newItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "[레전더리] 야간투시경")
                && !p.getInventory().containsAtLeast(recipe.Battery(1), 1)) {
            p.sendMessage(ChatColor.RED + "배터리가 부족합니다.");
            removePotionEffect(p);
        }

        if (oldItem.getItemMeta() != null && oldItem.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "[레전더리] 야간투시경")) {
            removePotionEffect(p);
        }

    }
}
