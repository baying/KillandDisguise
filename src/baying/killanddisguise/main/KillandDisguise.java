package baying.killanddisguise.main;

import org.bukkit.plugin.java.JavaPlugin;

import baying.killanddisguise.cmd.Clear;
import baying.killanddisguise.listener.joinandleave.JoinandLeave;
import baying.killanddisguise.listener.kill.Kill;
import pgDev.bukkit.DisguiseCraft.DisguiseCraft;
import pgDev.bukkit.DisguiseCraft.api.DisguiseCraftAPI;

public class KillandDisguise extends JavaPlugin
{
	public static KillandDisguise plugin;
	DisguiseCraftAPI dcAPI;
	public void setupDisguiseCraft()
	{
		dcAPI = DisguiseCraft.getAPI();
	}
	@Override
	public void onEnable()
	{
		plugin = this;
		setupDisguiseCraft();
		getServer().getPluginManager().registerEvents(new JoinandLeave(plugin), this);
		getServer().getPluginManager().registerEvents(new Kill(plugin), this);
		getCommand("kidclear").setExecutor(new Clear(plugin));
		reloadConfig();
		saveDefaultConfig();
		getLogger().info("��õ�Minecraft������������");
		getLogger().info("�ϵ۵�����yeah");
		getLogger().info("���ӣ�http://chinacraft.taobao.com");
		getLogger().info("�������:����EasyС���baying");
		getLogger().info("MCBBSID:q88724653");
		getLogger().info("����汾:1.0");
		getLogger().info("����������");
	}
	@Override
	public void onDisable()
	{
		getLogger().info("���ж�����");
	}
}
