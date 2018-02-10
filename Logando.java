package me.kali.curso.autenticador;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.kali.curso.apis.Mine;

public class Logando extends BukkitRunnable {
private Player p;
	@Override
	public void run() {
	Mine.sendActionBar(p, "§eVocê tem §e" +id + "para se autenticar ou será expulso do servidor de login!");	id --;
	}
	public Player getP() {
		return p;
	}
	public void setP(Player p) {
		this.p = p;
	}
	

	int id = 30;
	
}
