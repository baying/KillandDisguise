package baying.killanddisguise.listener.kill;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import pgDev.bukkit.DisguiseCraft.DisguiseCraft;
import pgDev.bukkit.DisguiseCraft.api.DisguiseCraftAPI;
import pgDev.bukkit.DisguiseCraft.disguise.Disguise;
import pgDev.bukkit.DisguiseCraft.disguise.DisguiseType;
import baying.killanddisguise.main.KillandDisguise;
import baying.killanddisguise.way.Config;

public class Kill implements Listener
{
	private KillandDisguise plugin;
	public Kill(KillandDisguise plugin)
	{
		this.plugin = plugin;
	}
	@EventHandler
	public void entitydead(EntityDeathEvent e)
	{
		FileConfiguration config = plugin.getConfig();
		if((config.getBoolean("enable")))
		{
			if(e.getEntityType() != EntityType.PLAYER&&e.getEntityType() != EntityType.WITHER_SKULL)
			{
				String type = e.getEntityType().toString();
				Player killer = e.getEntity().getKiller();
				if(killer != null)
				{
					if((killer.hasPermission("kiduse."+type.replace("ENDER_DRAGON", "enderdragon")))||(killer.hasPermission("kiduse.*")))
					{
						String name = killer.getName();
						Config set = new Config("Set/"+name+"set.byg");
						set.set("mob", type);
						DisguiseCraftAPI dc = DisguiseCraft.getAPI();
						Disguise mob = new Disguise(dc.newEntityID(), DisguiseType.fromString(type.replace("ENDER_DRAGON", "enderdragon")));
						dc.changePlayerDisguise(killer, mob);
						killer.sendMessage("§6<"+name+">§c你杀死了"+type+"，所以变成了"+type);
					}
				}
			}
		}
	}
	@EventHandler
	public void playerdead(PlayerDeathEvent e)
	{
		Player p = e.getEntity();
		Player killer = p.getKiller();
		if(killer != null)
		{
			if((killer.hasPermission("kiduse.player"))||(killer.hasPermission("kiduse.*")))
			{
			String name = p.getName();
			DisguiseCraftAPI dc = DisguiseCraft.getAPI();
			Disguise mob = new Disguise(dc.newEntityID(), name,DisguiseType.Player);
			dc.changePlayerDisguise(killer, mob);
			killer.sendMessage("§6<"+name+">§c你杀死了"+name+"，所以变成了"+name);
			}
		}
	}
}
