package com.toke1597.nightv.Commands;

import com.toke1597.nightv.Recipe.Recipe;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {

    public Recipe recipe = new Recipe(null);

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("night")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                p.getInventory().addItem(recipe.NightVisionHelmet());
                return true;
            }
        }
        if (label.equalsIgnoreCase("battery")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                p.getInventory().addItem(recipe.Battery(5));
                return true;
            }
        }

        return false;
    }
}
