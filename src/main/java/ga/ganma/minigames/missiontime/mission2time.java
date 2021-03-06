package ga.ganma.minigames.missiontime;

import ga.ganma.minigames.Eventget;
import ga.ganma.minigames.Minigames;
import ga.ganma.minigames.mission;
import ga.ganma.minigames.tosoCommand;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class mission2time extends BukkitRunnable {
    @Override
    public void run() {
        if (Minigames.gametime <= mission.mission2t) {
            mission.ismission = false;
            this.cancel();
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                p.sendTitle(ChatColor.RED + "ミッション失敗", "発光が30秒間ついてしまった！", 20, 100, 20);
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_DEATH, 1, 2);
                if (Minigames.Runner.getEntries().contains(p.getName())) {
                    PotionEffect potion = new PotionEffect(PotionEffectType.GLOWING, 600, 1);
                    p.addPotionEffect(potion);
                }
            }
            mission.missiontf.put("mission2", false);
            Eventget.missionS = null;
            tosoCommand.world.getBlockAt(mission.blockL).setType(Material.AIR);
        }
        else if (!mission.ismission) {
            this.cancel();
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                p.sendTitle(ChatColor.BLUE + "ミッション成功！", mission.CLEARp.getName() + "の活躍により発光は阻止された...", 20, 100, 20);
                p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 2);
            }
            mission.missiontf.put("mission2", true);
            Eventget.missionS = null;
            this.cancel();
        }
    }
}
