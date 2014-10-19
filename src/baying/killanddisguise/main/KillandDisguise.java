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
		getLogger().info("最好的Minecraft服务器出租商");
		getLogger().info("上帝的左手yeah");
		getLogger().info("链接：http://chinacraft.taobao.com");
		getLogger().info("插件作者:来自Easy小组的baying");
		getLogger().info("MCBBSID:q88724653");
		getLogger().info("插件版本:1.0");
		getLogger().info("插件加载完成");
	}
	@Override
	public void onDisable()
	{
		getLogger().info("插件卸载完成");
	}
}
