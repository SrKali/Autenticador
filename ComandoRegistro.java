package me.kali.curso.autenticador;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kali.curso.apis.Extra;
import me.kali.curso.apis.Mine;
import me.kali.curso.apis.Extra.KeyType;

public class ComandoRegistro implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;

			if (args.length == 0) {

				p.sendMessage("");
				p.sendMessage("§c§lREGISTRO §c➵  Para se registrar, digite /register <Senha>.");
				p.sendMessage("");
			} else {

				String senha = args[0];

				if (Autenticador.estaLogado(p)) {

					p.sendMessage("");
					p.sendMessage("§c§lREGISTRO §c➵ Você já se registrou e se logou!");
					p.sendMessage("");

				} else {

					if (Autenticador.estaRegistrado(p)) {

						p.sendMessage("");
						p.sendMessage("§c§lREGISTRO §c➵ Esta conta já está registrada!");
						p.sendMessage("");
				
				} else {
					String pin = Extra.newKey(KeyType.NUMERIC, 5);
					Autenticador.config.set("pins." +p.getName().toLowerCase(), pin);
					
					p.sendMessage("");
					p.sendMessage("§eNosso sistema de autenticação gerou um §fPIN§e para você utilizar caso esqueça sua senha! §fPIN: §e§l" +pin +"§e.");
					p.sendMessage("");
					
					Autenticador.config.set("contas." + p.getName().toLowerCase(), senha);
					Autenticador.config.saveConfig();
					Autenticador.logados.add(p);
					
					Mine.sendActionBar("§aVocê foi registrado e logado com sucesso!");
					
					}
				}

			}

		}

		return true;
	}

}
