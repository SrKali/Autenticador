package me.kali.curso.autenticador;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComandoTrocarSenha implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length <= 1) {

				p.sendMessage("");
				p.sendMessage(
						"§c§lALTERAR SENHA §c➵ O uso correto do comando é /trocarsenha <senha-antiga> <senha-nova>.");
				p.sendMessage("");

			} else {
				String senhaantiga = args[0];
				String senhanova = args[1];
				if (Autenticador.estaRegistrado(p)) {
					if (Autenticador.estaLogado(p)) {
						if (Autenticador.getsenha(p).equals(senhaantiga)) {
							if (senhanova.equals(senhaantiga)) {
								p.sendMessage("");
								p.sendMessage("§c§lALTERAR SENHA §c➵ A nova senha escolhida, já é a atual.");
								p.sendMessage("");
								return true;

							}
							Autenticador.config.set("contas." + p.getName().toLowerCase(), senhanova);
							Autenticador.config.saveConfig();
							p.sendMessage("");
							p.sendMessage(
									"§aSua senha foi trocada com sucesso! Sua nova senha: §e" + senhanova + "§a.");
							p.sendMessage("");

						} else {

							p.sendMessage("");
							p.sendMessage("§cA senha digitada está incorreta!");
							p.sendMessage("");

						}
					} else {

						p.sendMessage("");
						p.sendMessage("§cVocê não está logado para trocar a sua senha!");
						p.sendMessage("");

					}
				} else {

					p.sendMessage("");
					p.sendMessage("§cVocê não está resgistrado para trocar sua senha!");
					p.sendMessage("");

				}

			}
		}

		return true;
	}

}
