package baying.killanddisguise.listener.joinandleave;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import pgDev.bukkit.DisguiseCraft.DisguiseCraft;
import pgDev.bukkit.DisguiseCraft.api.DisguiseCraftAPI;
import pgDev.bukkit.DisguiseCraft.disguise.Disguise;
import pgDev.bukkit.DisguiseCraft.disguise.DisguiseType;
import baying.killanddisguise.main.KillandDisguise;
import baying.killanddisguise.way.Config;

public class JoinandLeave implements Listener
{
	private KillandDisguise plugin;
	public JoinandLeave(KillandDisguise plugin)
	{
		this.plugin = plugin;
	}
	@EventHandler
	public void inJoin(PlayerJoinEvent e)
	{
		FileConfiguration config = plugin.getConfig();
		if((config.getBoolean("enable")))
		{
			String  name = e.getPlayer().getName();
			Config set = new Config("Set/"+name+"set.byg");
			if((set.getConfig().getBoolean("enable") == false))
			{
				set.set("enable", true);
				set.set("mob", "none");
				plugin.getLogger().info(name+"设置完成");
			}
			if((!config.getBoolean("leave")))
			{
				if(!set.getConfig().getString("mob").equals("none"))
				{
					Player p = e.getPlayer();
					String mob = set.getConfig().getString("mob");
					if((p.hasPermission("kiduse."+mob))||(p.hasPermission("kiduse.*")))
					{
						DisguiseCraftAPI dc = DisguiseCraft.getAPI();
						Disguise type = new Disguise(dc.newEntityID(), DisguiseType.fromString(mob));
						dc.changePlayerDisguise(p, type);
					}
				}
			}
		}
	}
	@EventHandler
	public void inLeave(PlayerQuitEvent e)
	{
		FileConfiguration config = plugin.getConfig();
		if((config.getBoolean("enable")))
		{
			Player p = e.getPlayer();
			String  name = p.getName();
			Config set = new Config("Set/"+name+"set.byg");
			DisguiseCraftAPI dc = DisguiseCraft.getAPI();
			dc.changePlayerDisguise(p, new Disguise(dc.newEntityID(), name,DisguiseType.Player));
			if((config.getBoolean("leave")))
			{
				set.set("mob", "none");
				plugin.getLogger().info(name+"退出了游戏，他的设置已完成");
			}
		}
	}
}
