package net._1necat.effectoninv;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.java.JavaPlugin;

public class Effectoninv extends JavaPlugin {

    @Override
    public void onEnable() {
        // プラグインが有効化された際の処理
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    // プレイヤーのインベントリの最初のスロットを取得
                    ItemStack itemInSlot = player.getInventory().getItem(9);
                    if  (itemInSlot != null) {
                        ItemMeta meta1 = itemInSlot.getItemMeta();
                        if (meta1 != null) {
                            String itemName1 = meta1.getDisplayName();
                            if (itemInSlot.getType() == Material.CLOCK && meta1.hasDisplayName() && ChatColor.stripColor(itemName1).equals("OverClock") &&
                                    itemName1.contains(ChatColor.GOLD.toString())) {
                                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
                            } else {
                                checkAndRemoveSpeedEffect(player);
                            }
                        } else {
                            checkAndRemoveSpeedEffect(player);
                        }
                    } else {
                        checkAndRemoveSpeedEffect(player);
                    }
                    // プレイヤーのインベントリの2番目のスロットを取得
                    ItemStack itemInSlot2 = player.getInventory().getItem(10);
                    if  (itemInSlot2 != null) {
                        ItemMeta meta2 = itemInSlot2.getItemMeta();
                        if (meta2 != null) {
                            String itemName2 = meta2.getDisplayName();
                            if (itemInSlot2.getType() == Material.STICK && meta2.hasDisplayName() && itemName2.equals(ChatColor.YELLOW + "" + ChatColor.BOLD + "ミニほうき")) {
                                // 耐性3の効果を付与
                                player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, Integer.MAX_VALUE, 2, false, false));
                            } else {
                                checkAndRemoveResistanceEffect(player);
                            }
                        } else {
                            checkAndRemoveResistanceEffect(player);
                        }
                    } else {
                        checkAndRemoveResistanceEffect(player);
                    }
                    // プレイヤーのインベントリの3番目のスロットを取得
                    ItemStack itemInSlot3 = player.getInventory().getItem(11);
                    if  (itemInSlot3 != null) {
                        ItemMeta meta3 = itemInSlot3.getItemMeta();
                        if (meta3 != null) {
                            String itemName3 = meta3.getDisplayName();
                            if (itemInSlot3.getType() == Material.IRON_NUGGET && meta3.hasDisplayName() && itemName3.equals(ChatColor.LIGHT_PURPLE + "ミニ魔女")) {
                                // 攻撃力上昇3の効果を付与
                                player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, Integer.MAX_VALUE, 2, false, false));
                            } else {
                                checkAndRemoveStrengthEffect(player);
                            }
                        } else {
                            checkAndRemoveStrengthEffect(player);
                        }
                    } else {
                        checkAndRemoveStrengthEffect(player);
                    }
                }
            }
        }.runTaskTimer(this, 0L, 2L); // 1/10秒ごとにチェック
    }

    public void checkAndRemoveSpeedEffect(Player player) {
        PotionEffect speedEffect = player.getPotionEffect(PotionEffectType.SPEED);
        if (speedEffect != null) {
            // エフェクトの持続時間を取得（単位はティック、20ティック＝1秒）
            int durationTicks = speedEffect.getDuration();
            // 30分以上かを確認（30分 = 30 * 60 * 20ティック）
            int thirtyMinutesInTicks = 30 * 60 * 20;
            if (durationTicks >= thirtyMinutesInTicks) {
                player.removePotionEffect(PotionEffectType.SPEED);
            }
        }
    }

    public void checkAndRemoveResistanceEffect(Player player) {
        PotionEffect resistanceEffect = player.getPotionEffect(PotionEffectType.RESISTANCE);
        if (resistanceEffect != null) {
            // エフェクトの持続時間を取得（単位はティック、20ティック＝1秒）
            int durationTicks = resistanceEffect.getDuration();
            // 30分以上かを確認（30分 = 30 * 60 * 20ティック）
            int thirtyMinutesInTicks = 30 * 60 * 20;
            if (durationTicks >= thirtyMinutesInTicks) {
                player.removePotionEffect(PotionEffectType.RESISTANCE);
            }
        }
    }

    public void checkAndRemoveStrengthEffect(Player player) {
        PotionEffect StrengthEffect = player.getPotionEffect(PotionEffectType.STRENGTH);
        if (StrengthEffect != null) {
            // エフェクトの持続時間を取得（単位はティック、20ティック＝1秒）
            int durationTicks = StrengthEffect.getDuration();
            // 30分以上かを確認（30分 = 30 * 60 * 20ティック）
            int thirtyMinutesInTicks = 30 * 60 * 20;
            if (durationTicks >= thirtyMinutesInTicks) {
                player.removePotionEffect(PotionEffectType.STRENGTH);
            }
        }
    }

    @Override
    public void onDisable() {
        // プラグインが無効化された際の処理
        for (Player player : Bukkit.getOnlinePlayers()) {
            // プレイヤーの速度上昇効果を解除
            checkAndRemoveSpeedEffect(player);
            checkAndRemoveResistanceEffect(player);
            checkAndRemoveStrengthEffect(player);
        }
    }
}
