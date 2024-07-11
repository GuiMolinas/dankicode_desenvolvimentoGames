package oo;

public class Player {

	public int vidaInicial, tempo;
	
	public Player(int vidaInicial, int tempo) {
		System.out.println(vidaInicial + tempo);
	}
	
	public void nascer() {
		System.out.println("Nasceu...");
	}
	
	protected void teste() {
		System.out.println("Testando private...");
	}
}
