package me.kali.curso.autenticador;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComandoRecuperarSenha implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0) {
				
				p.sendMessage("");
				p.sendMessage("§cPara recuperar sua senha digite /recuperarsenha <pin>.");
				p.sendMessage("");
				
			} else {
				String pin = Autenticador.config.getString("pins." +p.getName().toLowerCase());
				if(pin == null) {
					
					p.sendMessage("");
					p.sendMessage("§cVocê não possui um pin pois nunca se autenticou em nosso servidor.");
					p.sendMessage("");

					return true;
				} if(pin.equals(args[0])) {
					
					String senha = Autenticador.config.getString("contas." +p.getName().toLowerCase());

					p.sendMessage("");
					p.sendMessage("§aLegal! O pin digitado está correto! Sua senha é: §7" +senha +"§a.");
				
				} else { 
					
				p.sendMessage("");
				p.sendMessage("§cO pin digitado está incorreto.");
				p.sendMessage("");
				
				}
			}
		}
		
		return true;
	}

}
