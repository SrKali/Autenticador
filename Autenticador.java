package me.kali.curso.autenticador;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.kali.curso.Main;
import me.kali.curso.apis.ConfigAPI;
import me.kali.curso.apis.Mine;

public class Autenticador implements Listener {
	@EventHandler
	public void evento(PlayerCommandPreprocessEvent e) {

		Player p = e.getPlayer();

		if (!(logados.contains(p))) {

			e.setCancelled(true);

			if (e.getMessage().startsWith("/login")) {

				e.setCancelled(false);

			}

			if (e.getMessage().startsWith("/register")) {

				e.setCancelled(false);

			}

			if (e.getMessage().startsWith("/recuperarsenha")) {

				e.setCancelled(false);

			}
		}

		if (e.isCancelled()) {

			p.sendMessage("");
			p.sendMessage("§cPara utilizar comandos, você deve se autenticar.");
			p.sendMessage("");

		}

	}

	@EventHandler
	public void evento(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (!(logados.contains(p))) {
			e.setCancelled(true);

			p.sendMessage("");
			p.sendMessage("§cVocê não pode quebrar blocos antes de se autenticar!");
			p.sendMessage("");
		}
	}

	@EventHandler
	public void evento(AsyncPlayerChatEvent e) {

		Player p = e.getPlayer();

		if (!(logados.contains(p))) {

			e.setCancelled(true);

			p.sendMessage("");
			p.sendMessage("§cPara enviar mensagens no chat você deve estar autenticado.");
			p.sendMessage("");

		}

	}

	@EventHandler
	public void evento(PlayerMoveEvent e) {

		Player p = e.getPlayer();

		if (!(logados.contains(p))) {
			if (!(e.getFrom().getBlock().equals(e.getTo().getBlock()))) {

				p.teleport(e.getFrom());
				
			}
		}

	}

	public static HashMap<Player, ItemStack[]> inventarios = new HashMap<>();

	@EventHandler(priority = EventPriority.HIGHEST)
	public void evento(PlayerJoinEvent e) {

		Player p = e.getPlayer();
		if (!(estaRegistrado(p))) {

			Mine.sendTitle(p, "§5§lONSLY", "§5Registre-se utilizando /register.", 20, 20 * 10, 20);

		} else if (!(logados.contains(p))) {

			Mine.sendTitle(p, "§5§lONSLY", "§5Logue-se utilizando /login.", 20, 20 * 10, 20);

		}
		inventarios.put(p, p.getInventory().getContents());
		p.getInventory().clear();
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 2, 1);
		new BukkitRunnable() {

			@Override
			public void run() {
				if (!(logados.contains(p))) {
					p.kickPlayer(
							"§cVocê foi expulso do servidor de autenticação pois demorou 60 segundos para se autenticar!");
				}
			}
		}.runTaskLater(Main.getPlugin(Main.class), 20 * 60);
	}

	@EventHandler
	public void evento(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (inventarios.containsKey(p)) {
			p.getInventory().setContents(inventarios.get(p));
		}
	}

	public static ConfigAPI config;
	public static ArrayList<Player> logados = new ArrayList<>();

	public static boolean estaLogado(Player p) {
		return logados.contains(p);
	}

	public static String getsenha(Player p) {
		return config.getString("contas." + p.getName().toLowerCase());
	}

	public static boolean estaRegistrado(Player p) {
		return config.contains("contas." + p.getName().toLowerCase());
	}
	@EventHandler(priority = EventPriority.LOW)
	public void evento(PlayerInteractEvent e) {
		if (logados.contains(e.getPlayer())) {
			return;
		}
		e.setCancelled(true);
	}
}
