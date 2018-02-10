package me.kali.curso.autenticador;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComandoEmail implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;

			if(args.length == 0) {
				
				p.sendMessage("");
				p.sendMessage("msg de ajuda");
				p.sendMessage("");
			
			} else {
				
				if(!(Autenticador.config.contains("emails." +p.getName().toLowerCase()))) {
					
					Autenticador.config.set("emails." +p.getName().toLowerCase(), args[0]);
					
					p.sendMessage("");
					p.sendMessage("§aO seu email foi registrado com sucesso!");
					p.sendMessage("");
				
				}
				
			}
		}
		
		return true;
	}

}
