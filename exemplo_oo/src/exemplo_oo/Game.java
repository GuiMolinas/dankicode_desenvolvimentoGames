package exemplo_oo;

public class Game {

	private Player player;
	private Inimigo inimigo;
	
	public Game() {
		player = new Player();
		if(player == null) {
			System.out.println("O player não foi inicializado.");
		}
		
		if(player instanceof Player) {
			System.out.println("Player referenciado e inicializado");
		}
		inimigo = new Inimigo();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Inimigo getInimigo() {
		return inimigo;
	}
	
	public static void main(String [] args) {
		Game game = new Game();
		Player player = game.getPlayer();
		player.atacarInimigo(game.getInimigo());
		System.out.println(game.getInimigo().life);
	}
}
