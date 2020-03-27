package com.toke1597.nightv.Recipe;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class Recipe {

    //접근 제어
    private final Plugin plugin;

    public Recipe(Plugin plugin) {
        this.plugin = plugin;
    }

    //야간투시경
    public ItemStack NightVisionHelmet() {
        ItemStack nvh = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta nvhm = nvh.getItemMeta();


        nvhm.setDisplayName(ChatColor.GREEN + "[레전더리] 야간투시경");
        nvhm.setLore(Arrays.asList(ChatColor.WHITE + "야간 활동의 도움을 준다.", ChatColor.RED + "작동을 위해 배터리가 필요하다.",
                ChatColor.GOLD + "배터리 작동시간: " + ChatColor.RED + 10 + ChatColor.GOLD + "분"));
        nvhm.addEnchant(Enchantment.OXYGEN, 1, false);
        nvhm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        nvhm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        nvh.setItemMeta(nvhm);

        return nvh;
    }

    //배터리
    public ItemStack Battery(int amount) {
        ItemStack B = new ItemStack(Material.REDSTONE_BLOCK, amount);
        ItemMeta BM = B.getItemMeta();

        BM.setDisplayName(ChatColor.BLUE + "[레어] 배터리");
        BM.setLore(Arrays.asList(ChatColor.WHITE + "야간투시경의 배터리이다."));

        B.setItemMeta(BM);

        return B;
    }

    //배터리 레시피
    public ShapedRecipe BatteryRecipe() {
        NamespacedKey key = new NamespacedKey(plugin, "night_vision_battery");
        ShapedRecipe Battery = new ShapedRecipe(key, Battery(4));

        Battery.shape("121",
                      "222",
                      "121");
        Battery.setIngredient('1', Material.REDSTONE_BLOCK)
                .setIngredient('2', Material.REDSTONE);

        return Battery;
    }

    //야간투시경 레시피
    public ShapedRecipe NightVisionRecipe(){
        NamespacedKey key = new NamespacedKey(plugin, "night_vision_helmet");
        ShapedRecipe NightVH = new ShapedRecipe(key, NightVisionHelmet());

        NightVH.shape("***", "$@$", "%^%")
                .setIngredient('*', Material.REDSTONE_BLOCK)
                .setIngredient('$', Material.GLOWSTONE)
                .setIngredient('@', Material.DIAMOND_HELMET)
                .setIngredient('%', Material.DIAMOND)
                .setIngredient('^', Material.GLASS_PANE);

        return NightVH;
    }
}
