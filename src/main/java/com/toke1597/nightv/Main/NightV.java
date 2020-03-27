package com.toke1597.nightv.Main;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import com.toke1597.nightv.Commands.Command;
import com.toke1597.nightv.Effecter.Effecter;
import com.toke1597.nightv.Recipe.Recipe;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class NightV extends JavaPlugin implements Listener {

    public Recipe recipe = new Recipe(this);
    public Effecter effecter = new Effecter(this);

    public void onEnable() {
        // Plugin startup logic
        getLogger().info(ChatColor.GREEN + "야간투시경 작동!");
        Bukkit.getPluginManager().registerEvents(this, this);
        getCommand("night").setExecutor(new Command());
        getCommand("battery").setExecutor(new Command());

        Bukkit.addRecipe(recipe.NightVisionRecipe());
        Bukkit.addRecipe(recipe.BatteryRecipe());
    }

    @EventHandler
    public void armorChangeEvent(PlayerArmorChangeEvent e){
        effecter.armorChangeEvent(e);
    }

}
