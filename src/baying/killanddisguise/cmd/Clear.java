package baying.killanddisguise.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pgDev.bukkit.DisguiseCraft.DisguiseCraft;
import pgDev.bukkit.DisguiseCraft.api.DisguiseCraftAPI;
import pgDev.bukkit.DisguiseCraft.disguise.Disguise;
import pgDev.bukkit.DisguiseCraft.disguise.DisguiseType;
import baying.killanddisguise.main.KillandDisguise;
import baying.killanddisguise.way.Config;

public class Clear implements CommandExecutor
{
	@SuppressWarnings("unused")
	private KillandDisguise plugin;
	public Clear(KillandDisguise plugin)
	{
		this.plugin = plugin;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("kidclear"))
		{
			if(args.length == 0)
			{
		        sender.sendMessage("��c�������������");
		        return true;
			}
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if(target == null)
			{
				sender.sendMessage("��c����Ҳ�����");
				return true;
			}
			if(target != null)
			{
				Config set = new Config("Set/"+target.getName()+"set.byg");
				DisguiseCraftAPI dc = DisguiseCraft.getAPI();
				dc.changePlayerDisguise(target, new Disguise(dc.newEntityID(), target.getName(),DisguiseType.Player));
				set.set("mob", "none");
				sender.sendMessage("��c�ѽ������ҵı���");
				return true;
			}
		}
		return false;
	}
}
