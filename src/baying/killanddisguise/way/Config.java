package baying.killanddisguise.way;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import baying.killanddisguise.main.KillandDisguise;

public class Config 
{
	private FileConfiguration config;
	private File file;
	public Config(String name)
	{
		File data=KillandDisguise.plugin.getDataFolder();
		this.file=new File(data,name);
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		if(!file.exists())
		{
			try{file.createNewFile();}
			catch(IOException e){}
		}
		config=YamlConfiguration.loadConfiguration(file);
	}
	public File getFile()
	{
		return file;
	}
	public FileConfiguration getConfig()
	{
		return config;
	}
	public void set(String arg0,Object arg1)
	{
		config.set(arg0, arg1);
		try{config.save(file);}
		catch(IOException e){}
	}
	public void set(Object arg0,Object arg1)
	{
		config.set(arg0.toString(), arg1);
		try{config.save(file);}
		catch(IOException e){}
	}
}
