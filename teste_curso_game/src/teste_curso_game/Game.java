package teste_curso_game;

import java.util.ArrayList;

public class Game {
	
	public static void main(String[] args) {
		
		Player player = new Player();
		ArrayList<Inimigo> inimigos = new ArrayList<Inimigo>();
		ElfInimigo elf = new ElfInimigo(10);
		
		inimigos.add(new ElfInimigo(100));
		inimigos.add(new ElfInimigo(100));
		inimigos.add(new ElfInimigo(100));
		
		for(int i = 0; i < inimigos.size(); i++) {
			Inimigo inimigoLocal = inimigos.get(i);
			
			if(i == 1){
				inimigoLocal.tomarDano();
				System.out.println(inimigoLocal.vida);
			}
			
			else {
				System.out.println(inimigoLocal.vida);
			}
		}
		
		
		
		
		
		
	}
	
}
