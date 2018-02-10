package me.kali.curso.autenticador;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComandoLogin implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;

			if (args.length == 0) {

				p.sendMessage(""); 
				p.sendMessage("§c§lLOGIN §c➵ Você precisa digitar sua senha!");
				p.sendMessage("");

			} else { 
 
				String senha = args[0];

				if (Autenticador.estaLogado(p)) {

					p.sendMessage("");
					p.sendMessage("§c§lLOGIN §c➵ Você já está logado!");
					p.sendMessage("");

				} else {

					if (Autenticador.estaRegistrado(p)) {

						String senhasalvada = Autenticador.getsenha(p);

						if (senhasalvada.equals(senha)) {

							p.playSound(p.getLocation(), Sound.LEVEL_UP, 2, 1);
							Autenticador.logados.add(p);
							if (Autenticador.inventarios.containsKey(p)) {
								p.getInventory().setContents(Autenticador.inventarios.get(p));
								p.sendMessage("");
								p.sendMessage("§c§lLOGIN §c➵ Você se logou com êxito!");
								p.sendMessage("");
							}

						} else {

							p.kickPlayer("           §c§lLOGIN §c➵ Você foi kickado! Sua senha está errada!");

						}

					}

				}

			}
		}
		return true;

	}
}